package net.paissad.waqtsalat.maxmindlocationsprovider.internal.util;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.waqtsalat.locationsprovider.LocationsProviderFactory;
import net.paissad.waqtsalat.locationsprovider.api.City;
import net.paissad.waqtsalat.locationsprovider.api.Coordinates;
import net.paissad.waqtsalat.locationsprovider.api.Country;
import net.paissad.waqtsalat.maxmindlocationsprovider.MaxmindLocationsProviderPlugin;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.h2.util.JdbcUtils;

public class GeoIPUtil {

    private static final ILogger     logger                        = MaxmindLocationsProviderPlugin.getDefault()
                                                                           .getLogger();

    private static final IJobManager JOB_MANAGER                   = Job.getJobManager();

    public static final String       GEOLITE_CITY_DOWNLOAD_LINK    = "http://paissad.net/WaqtSalat-GeoLiteCity.zip";

    private static final String      __GEOLITE_CITY_CSV_PATH_TAG__ = "___WORLDCITIES_CSV_ABSOLUTE_PATH___";

    private static final String      CSV_CHARSET                   = "ISO-8859-15";

    private static final String      WORLDCITIES_TABLE_NAME        = "WORLDCITIES";

    private static final String      SQL_CREATE_TABLE              = new StringBuilder()
                                                                           .append("CREATE TABLE IF NOT EXISTS ")
                                                                           .append(WORLDCITIES_TABLE_NAME)
                                                                           .append(" AS SELECT * ")
                                                                           .append("FROM CSVREAD ('")
                                                                           .append(__GEOLITE_CITY_CSV_PATH_TAG__)
                                                                           .append("', null, '").append(CSV_CHARSET)
                                                                           .append("');").toString();

    private static final String      SQL_UPDATE_COUNTRY_NAME       = new StringBuilder()
                                                                           .append("UPDATE ")
                                                                           .append(WORLDCITIES_TABLE_NAME)
                                                                           .append(" SET country_name = ? WHERE country_code LIKE ?;")
                                                                           .toString();

    //@formatter:off
    /*

    private static final File        CITIES_CACHE_FILE           = MaxmindLocationsProviderPlugin.getDefault()
                                                                         .getStateLocation()
                                                                         .append("/cache/cities.xmi").toFile();      //$NON-NLS-1$

    private static CacheStatus       currentCacheStatus          = CacheStatus.NONE;

    private static enum CacheStatus {
        NONE, IN_PROGRESS, DONE;
    }
    //*/
    //@formatter:on

    // =================================================================================================================

    private GeoIPUtil() {
    }

    private static File getGeoliteCityDatabaseDir() {
        return MaxmindLocationsProviderPlugin.getDefault().getStateLocation().append("/database").toFile(); //$NON-NLS-1$
    }

    /**
     * @return The file representing the H2 database. The file may not exist.
     */
    public static File getGeoLiteCityCSVFile() {
        return new File(getGeoliteCityDatabaseDir(), "GeoLiteCity-Location.csv"); //$NON-NLS-1$
    }

    /**
     * Download the Maxmind CSV database file using a {@link Job}.
     * 
     * @param join
     *        - If <code>true</code> then wait for the job to finish.
     * @throws IOException
     */
    public static void downloadCSVDatabase(boolean join) throws IOException {
        downloadCSVDatabase(GEOLITE_CITY_DOWNLOAD_LINK, getGeoliteCityDatabaseDir(), join);
    }

    /**
     * @param csvDownloadLink
     * @param destDir
     * @param join
     *        - Whether or not to wait for job to finish.
     * @throws IOException
     */
    private static void downloadCSVDatabase(final String csvDownloadLink, final File destDir, boolean join)
            throws IOException {

        final BoolWrapper downloaded = new BoolWrapper();
        downloaded.setBool(false);

        Job job = new Job("Downloading Maxmind CSV database file") { //$NON-NLS-1$

            @Override
            protected IStatus run(IProgressMonitor monitor) {
                if (monitor == null) {
                    monitor = JOB_MANAGER.createProgressGroup();
                }
                try {
                    URL url = new URL(csvDownloadLink);
                    URLConnection urlConnection = url.openConnection();
                    urlConnection.setConnectTimeout((int) TimeUnit.MINUTES.toMillis(1));
                    urlConnection.setAllowUserInteraction(false);
                    urlConnection.connect();
                    int contentLength = urlConnection.getContentLength();
                    monitor.beginTask("Downloading file", contentLength + 1); //$NON-NLS-1$
                    byte[] data = new byte[8192];
                    int bytesRead;
                    InputStream in = null;
                    OutputStream out = null;
                    int currentLength = 0;
                    try {
                        File zippedCSVFile = new File(destDir, "WaqtSalat-GeoLiteCity-Location.zip"); //$NON-NLS-1$
                        if (!zippedCSVFile.getParentFile().exists()) {
                            if (!zippedCSVFile.getParentFile().mkdirs()) {
                                throw new IOException("Unable to create the parent directory of the CSV database file."); //$NON-NLS-1$
                            }
                        }
                        in = urlConnection.getInputStream();
                        out = new BufferedOutputStream(new FileOutputStream(zippedCSVFile), data.length);
                        while ((bytesRead = in.read(data, 0, data.length)) > 0) {
                            out.write(data, 0, bytesRead);
                            currentLength += bytesRead;
                            monitor.worked(bytesRead);
                        }
                        if (currentLength != contentLength) {
                            throw new IOException("Expected to download " + contentLength + " bytes, but got only " //$NON-NLS-1$ //$NON-NLS-2$
                                    + currentLength);
                        }
                        out.close();
                        out.flush();

                        monitor.setTaskName("Unzipping file"); //$NON-NLS-1$
                        ZipFile zipFile = new ZipFile(zippedCSVFile);
                        Enumeration<? extends ZipEntry> entries = zipFile.entries();
                        while (entries.hasMoreElements()) {
                            ZipEntry entry = entries.nextElement();
                            String name = entry.getName().replaceAll(".*\\/", ""); //$NON-NLS-1$
                            File entryFile = new File(getGeoliteCityDatabaseDir(), name);
                            if (entry.isDirectory()) {
                                continue;
                            }
                            in = zipFile.getInputStream(entry);
                            out = new BufferedOutputStream(new FileOutputStream(entryFile), 8192);
                            while ((bytesRead = in.read(data, 0, data.length)) > 0) {
                                out.write(data, 0, bytesRead);
                            }
                            out.close();
                            out.flush();
                        }
                        monitor.worked(1);

                    } finally {
                        for (Closeable closeable : new Closeable[] { in, out }) {
                            try {
                                closeable.close();
                            } catch (Exception e) {
                            }
                        }
                    }

                    downloaded.setBool(true);
                    return new Status(IStatus.INFO, MaxmindLocationsProviderPlugin.PLUGIN_ID,
                            "Maxmind CSV database file downloaded successfully."); //$NON-NLS-1$

                } catch (IOException ioe) {
                    return new Status(IStatus.ERROR, MaxmindLocationsProviderPlugin.PLUGIN_ID,
                            "Error while downloading Maxmind CSV database file : " + ioe.getMessage(), ioe); //$NON-NLS-1$
                } finally {
                    monitor.done();
                }
            }
        };
        job.schedule();
        if (join) {
            try {
                job.join();
            } catch (InterruptedException e) {
            }
        }
        if (!downloaded.getBool()) {
            throw new IOException("Unable do download MaxMind CSV database file");
        }
    }

    private static class BoolWrapper {
        private boolean bool;

        public boolean getBool() {
            return bool;
        }

        public void setBool(boolean bool) {
            this.bool = bool;
        }
    }

    /**
     * Creates the database/table if it did not exist yet.
     * 
     * @throws IOException
     * @throws SQLException
     */
    private static void createTable() throws IOException, SQLException {

        long startTime = System.currentTimeMillis();

        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        try {
            conn = getDBConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String h2DatabasePath = null;
            try {
                h2DatabasePath = getGeoLiteCityCSVFile().getCanonicalPath();
            } catch (IOException e) {
                h2DatabasePath = getGeoLiteCityCSVFile().getAbsolutePath();
            }

            final String sqlCreateTable = SQL_CREATE_TABLE.replaceAll(__GEOLITE_CITY_CSV_PATH_TAG__, h2DatabasePath);
            stmt.execute(sqlCreateTable);

            conn.commit();

            long endTime = System.currentTimeMillis();
            logger.info("The table '" + WORLDCITIES_TABLE_NAME + "' created successfully in "
                    + (int) (endTime - startTime) / 1000 + " seconds.");

        } catch (SQLException sqle) {
            String errMsg = "An error occured while creating the table (" + WORLDCITIES_TABLE_NAME + "), "
                    + sqle.getErrorCode() + " : " + sqle.getMessage();
            logger.error(errMsg, sqle);
            if (conn != null && !conn.isClosed()) {
                conn.rollback();
            }
            throw new SQLException(errMsg, sqle);

        } finally {
            JdbcUtils.closeSilently(conn);
            JdbcUtils.closeSilently(stmt);
            JdbcUtils.closeSilently(pstmt);
            ;
        }
    }

    /**
     * Update the country names entries of the database using the specified
     * {@link Locale}.
     * 
     * @param aLocale
     *        The <code>Locale</code> to use.
     * @throws SQLException
     * 
     */
    public static void updateTableCountryName(Locale aLocale) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            long timeStart = System.currentTimeMillis();
            conn = getDBConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(SQL_UPDATE_COUNTRY_NAME);
            Map<String, String> countries = CommonUtils.getCountries(aLocale);
            int count = 0;
            for (Iterator<Entry<String, String>> iter = countries.entrySet().iterator(); iter.hasNext();) {
                Entry<String, String> entry = iter.next();
                pstmt.setString(1, entry.getValue()); // country_name
                pstmt.setString(2, entry.getKey()); // country_code
                pstmt.addBatch();
                if (++count % 10000 == 0) {
                    pstmt.executeBatch();
                    pstmt.clearBatch();
                }
            }
            pstmt.executeBatch();
            conn.commit();

            long timeStop = System.currentTimeMillis();
            logger.info("Finished updating the country names into table '" + WORLDCITIES_TABLE_NAME
                    + "' using Locale '" + aLocale.getDisplayName() + "' in " + (int) (timeStop - timeStart) / 1000
                    + " seconds.");

        } catch (SQLException sqle) {
            String errMsg = "Error while updating names of countries into the database, " + sqle.getErrorCode() + " : "
                    + sqle.getMessage();
            logger.error(errMsg, sqle);
            throw new SQLException(errMsg, sqle);

        } finally {
            JdbcUtils.closeSilently(conn);
            JdbcUtils.closeSilently(pstmt);
        }
    }

    /**
     * @param country
     *        The country.
     * @param city
     *        The city.
     * @return The {@link Coordinates} of the couple <i>city/country</i>, return
     *         <code>null</code> if the couple of city/country is not found into
     *         the database or when an error occurs.
     * @throws SQLException
     * 
     */
    public static Coordinates getCoordinatesFromCountryAndCity(String country, String city) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getDBConnection();
            Coordinates coord = null;
            String sql = "SELECT latitude, longitude FROM " + WORLDCITIES_TABLE_NAME + " WHERE country_name LIKE '"
                    + country + "' AND city LIKE '" + city + "';";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) { // We only select the 1st row !
                coord = LocationsProviderFactory.eINSTANCE.createCoordinates();
                coord.setLatitude(rs.getFloat("latitude"));
                coord.setLongitude(rs.getFloat("longitude"));
            }
            return coord;

        } catch (SQLException sqle) {
            String errMsg = "Error while retrieving the coordinates of (" + country + ", " + city
                    + ") from the database, " + sqle.getErrorCode() + " : " + sqle.getMessage();
            logger.error(errMsg, sqle);
            throw new SQLException(errMsg, sqle);

        } finally {
            JdbcUtils.closeSilently(conn);
            JdbcUtils.closeSilently(stmt);
            JdbcUtils.closeSilently(rs);
        }
    }

    /**
     * Get the country ISO Code from a given country name.
     * 
     * @param countryName
     *        The full name of the country.
     * 
     * @return The String 2 letters representation of the country code in upper
     *         case.
     * @throws SQLException
     */
    public static String getCountryCodeFromCountryName(String countryName) throws SQLException {
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            conn = getDBConnection();
            conn.setAutoCommit(false);

            String cc = null; // cc like the abbreviation of country code
            if (countryName != null) {
                String sql = "SELECT country_code FROM " + WORLDCITIES_TABLE_NAME + " WHERE country_name LIKE '"
                        + countryName + "';";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) { // We only select the 1st row ...
                    cc = rs.getString(1);
                }
            }
            return cc;

        } catch (SQLException sqle) {
            String errMsg = "Error while getting country code for (" + countryName + ") from the database, "
                    + sqle.getErrorCode() + " : " + sqle.getMessage();
            logger.error(errMsg, sqle);
            throw new SQLException(errMsg, sqle);

        } finally {
            JdbcUtils.closeSilently(conn);
            JdbcUtils.closeSilently(stmt);
            JdbcUtils.closeSilently(rs);
        }
    }

    public static Collection<City> getAllCities() throws SQLException {

        final Collection<City> cities = new ArrayList<City>();

        if (MaxmindLocationsProviderPlugin.isH2DatabaseAlreadyCreated()) {

            Connection conn = null;
            ResultSet rs = null;
            Statement stmt = null;

            try {
                conn = getDBConnection();
                conn.setAutoCommit(false);
                String sql = "SELECT * FROM " + WORLDCITIES_TABLE_NAME;
                stmt = conn.createStatement();
                stmt.setFetchSize(100);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {

                    final Coordinates coords = LocationsProviderFactory.eINSTANCE.createCoordinates();
                    coords.setLatitude(rs.getDouble("latitude"));
                    coords.setLongitude(rs.getDouble("longitude"));

                    final Country country = LocationsProviderFactory.eINSTANCE.createCountry();
                    country.setName(rs.getString("country_name"));
                    country.setCode(rs.getString("country_code"));

                    final City city = LocationsProviderFactory.eINSTANCE.createCity();
                    city.setName(rs.getString("city"));
                    city.setRegion(rs.getString("region"));
                    city.setPostalCode(rs.getString("postal_code"));
                    city.setCountry(country);
                    city.setCoordinates(coords);

                    cities.add(city);
                }

            } catch (SQLException sqle) {
                String errMsg = "Error while retrieving the list of cities : " + sqle.getErrorCode() + " : "
                        + sqle.getMessage();
                logger.error(errMsg, sqle);
                throw new SQLException(errMsg, sqle);

            } finally {
                JdbcUtils.closeSilently(conn);
                JdbcUtils.closeSilently(stmt);
                JdbcUtils.closeSilently(rs);
            }
        } else {
            logger.warn("Maxmind H2 database containing cities is not yet created, returning empty list ...");
        }

        return cities;
    }

    //@formatter:off
    /*
    private static void cacheCities(final Collection<City> cities) {
        try {
            if (!CITIES_CACHE_FILE.getParentFile().exists()) {
                if (!CITIES_CACHE_FILE.getParentFile().mkdirs()) {
                    throw new IOException("Unable to create the the directory : " + CITIES_CACHE_FILE.getParent());
                }
            }
            if (CITIES_CACHE_FILE.exists()) {
                if (!CITIES_CACHE_FILE.delete()) {
                    throw new IOException("Unable to delete the cities cache file before regenerating it.");
                }
            }
            currentCacheStatus = CacheStatus.IN_PROGRESS;
            final ResourceSet rSet = new ResourceSetImpl();
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(".", new XMIResourceFactoryImpl());
            URI fileURI = URI.createFileURI(CITIES_CACHE_FILE.getCanonicalPath());
            Resource resource = rSet.createResource(fileURI);

            for (Iterator<City> iterator = cities.iterator(); iterator.hasNext();) {
                resource.getContents().add((City) iterator.next());
            }
            resource.save(Collections.EMPTY_MAP);

            currentCacheStatus = CacheStatus.DONE;

        } catch (Exception e) {
            currentCacheStatus = CacheStatus.NONE;
            String errMsg = "Error while caching the cities : " + e.getMessage();
            logger.error(errMsg, e);
        }
    }
    //*/
    //@formatter:on

    private static Connection getDBConnection() throws SQLException {
        return DBUtil.newInstance(new File(getGeoliteCityDatabaseDir(), "h2"));
    }

    public static void createDatabaseAndPopulateCitiesTable() throws IOException, SQLException {
        if (!tableExistent(WORLDCITIES_TABLE_NAME)) {
            createTable();
            // updateTableCountryName(Locale.ENGLISH);
        }
    }

    private static boolean tableExistent(final String tableName) throws SQLException {
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = getDBConnection();
            DatabaseMetaData dbm = conn.getMetaData();
            rs = dbm.getTables(null, null, tableName, null);
            return rs.next();

        } catch (SQLException sqle) {
            String errMsg = "" + sqle.getErrorCode() + " - " + sqle.getMessage();
            logger.error(errMsg, sqle);
            throw new SQLException(sqle);

        } finally {
            JdbcUtils.closeSilently(conn);
            JdbcUtils.closeSilently(rs);
        }
    }
}
