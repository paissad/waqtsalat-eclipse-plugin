package net.paissad.waqtsalat.maxmindlocationsprovider.internal.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.waqtsalat.locationsprovider.LocationsProviderFactory;
import net.paissad.waqtsalat.locationsprovider.api.City;
import net.paissad.waqtsalat.locationsprovider.api.Coordinates;
import net.paissad.waqtsalat.locationsprovider.api.Country;
import net.paissad.waqtsalat.maxmindlocationsprovider.MaxmindLocationsProviderPlugin;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.h2.util.IOUtils;
import org.h2.util.JdbcUtils;
import org.osgi.framework.Bundle;

public class GeoIPUtil {

    private static final ILogger logger                        = MaxmindLocationsProviderPlugin.getDefault()
                                                                       .getLogger();

    private static final String  __GEOLITE_CITY_CSV_PATH_TAG__ = "___WORLDCITIES_CSV_ABSOLUTE_PATH___"; //$NON-NLS-1$

    private static final String  CSV_CHARSET                   = "ISO-8859-15";                        //$NON-NLS-1$

    private static final String  WORLDCITIES_TABLE_NAME        = "WORLDCITIES";                        //$NON-NLS-1$

    private static final String  SQL_CREATE_TABLE              = new StringBuilder()
                                                                       .append("CREATE TABLE IF NOT EXISTS ") //$NON-NLS-1$
                                                                       .append(WORLDCITIES_TABLE_NAME)
                                                                       .append(" AS SELECT * ") //$NON-NLS-1$
                                                                       .append("FROM CSVREAD ('") //$NON-NLS-1$
                                                                       .append(__GEOLITE_CITY_CSV_PATH_TAG__)
                                                                       .append("', null, '").append(CSV_CHARSET) //$NON-NLS-1$
                                                                       .append("');").toString();      //$NON-NLS-1$

    private static final String  SQL_UPDATE_COUNTRY_NAME       = new StringBuilder().append("UPDATE ") //$NON-NLS-1$
                                                                       .append(WORLDCITIES_TABLE_NAME)
                                                                       .append(" SET country_name = ? WHERE country_code LIKE ?;") //$NON-NLS-1$
                                                                       .toString();

    // =================================================================================================================

    private GeoIPUtil() {
    }

    private static File getGeoliteCityDatabaseDir() {
        return MaxmindLocationsProviderPlugin.getDefault().getStateLocation().append("/database/").toFile(); //$NON-NLS-1$
    }

    /**
     * @return The customized CSV file containing the cities. The file may not
     *         exist.
     */
    public static File getGeoLiteCityCSVFile() {
        return new File(getGeoliteCityDatabaseDir(), "GeoLiteCity-Location.csv"); //$NON-NLS-1$
    }

    /**
     * @return <code>true</code> if unzipped successfully, <code>false</code>
     *         otherwise.
     */
    public static boolean unzipGeoLiteCityZipFile() {

        final BoolWrapper boolWrapper = new BoolWrapper();
        boolWrapper.setBool(false);

        Job job = new Job("Unzipping customized GeoLiteCity zip file") { //$NON-NLS-1$

            @Override
            protected IStatus run(IProgressMonitor monitor) {
                if (monitor == null) {
                    monitor = Job.getJobManager().createProgressGroup();
                }
                InputStream in = null;
                ZipInputStream zin = null;
                OutputStream out = null;

                try {
                    Bundle bundle = Platform.getBundle(MaxmindLocationsProviderPlugin.BUNDLE_SYMBOLIC_NAME);
                    URL url = bundle.getResource("resources/WaqtSalat-GeoLiteCity-Location.zip"); //$NON-NLS-1$
                    URL resolvedURL = FileLocator.resolve(url);

                    in = resolvedURL.openStream();
                    zin = new ZipInputStream(new BufferedInputStream(in, 8192));

                    ZipEntry entry = null;
                    while ((entry = zin.getNextEntry()) != null) {
                        String name = entry.getName().replaceAll(".*\\/", ""); //$NON-NLS-1$
                        File entryFile = new File(getGeoliteCityDatabaseDir(), name);
                        if (entry.isDirectory()) {
                            continue;
                        }
                        if (!entryFile.getParentFile().exists()) {
                            if (!entryFile.getParentFile().mkdirs()) {
                                throw new IOException("Unable to create the directory : " + entryFile.getParentFile()); //$NON-NLS-1$
                            }
                        }
                        out = new BufferedOutputStream(new FileOutputStream(entryFile), 8192);
                        int bytesRead;
                        byte[] data = new byte[8192];
                        while ((bytesRead = zin.read(data, 0, data.length)) > 0) {
                            out.write(data, 0, bytesRead);
                        }
                        zin.closeEntry();
                        out.flush();
                        out.close();
                    }
                    boolWrapper.setBool(true);
                    return new Status(Status.INFO, MaxmindLocationsProviderPlugin.PLUGIN_ID,
                            "Unzipped customized Maxmind GeoLiteCity file successfully."); //$NON-NLS-1$

                } catch (Exception e) {
                    String errMsg = "Error while unzipping customized Maxmind GeoLiteCity file."; //$NON-NLS-1$
                    logger.error(errMsg, e);
                    return new Status(Status.ERROR, MaxmindLocationsProviderPlugin.PLUGIN_ID, errMsg, e);

                } finally {
                    IOUtils.closeSilently(zin);
                    IOUtils.closeSilently(in);
                    IOUtils.closeSilently(out);
                }
            }
        };
        job.schedule();
        try {
            job.join();
        } catch (InterruptedException e) {
        }
        return boolWrapper.getBool();
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

            final String sqlCreateTable = SQL_CREATE_TABLE.replaceAll(__GEOLITE_CITY_CSV_PATH_TAG__,
                    Matcher.quoteReplacement(h2DatabasePath));
            stmt.execute(sqlCreateTable);

            conn.commit();

            long endTime = System.currentTimeMillis();
            logger.info("The table '" + WORLDCITIES_TABLE_NAME + "' is created successfully in " //$NON-NLS-1$ //$NON-NLS-2$
                    + (int) (endTime - startTime) / 1000 + " seconds."); //$NON-NLS-1$

        } catch (SQLException sqle) {
            String errMsg = "An error occured while creating the table (" + WORLDCITIES_TABLE_NAME + "), " //$NON-NLS-1$ //$NON-NLS-2$
                    + sqle.getErrorCode() + " : " + sqle.getMessage(); //$NON-NLS-1$
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
            logger.info("Finished updating the country names into table '" + WORLDCITIES_TABLE_NAME //$NON-NLS-1$
                    + "' using Locale '" + aLocale.getDisplayName() + "' in " + (int) (timeStop - timeStart) / 1000 //$NON-NLS-1$
                    + " seconds."); //$NON-NLS-1$

        } catch (SQLException sqle) {
            String errMsg = "Error while updating names of countries into the database, " + sqle.getErrorCode() + " : " //$NON-NLS-1$ //$NON-NLS-2$
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
            String sql = "SELECT latitude, longitude FROM " + WORLDCITIES_TABLE_NAME + " WHERE country_name LIKE '" //$NON-NLS-1$ //$NON-NLS-2$
                    + country + "' AND city LIKE '" + city + "';"; //$NON-NLS-1$
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) { // We only select the 1st row !
                coord = LocationsProviderFactory.eINSTANCE.createCoordinates();
                coord.setLatitude(rs.getFloat("latitude")); //$NON-NLS-1$
                coord.setLongitude(rs.getFloat("longitude")); //$NON-NLS-1$
            }
            return coord;

        } catch (SQLException sqle) {
            String errMsg = "Error while retrieving the coordinates of (" + country + ", " + city //$NON-NLS-1$ //$NON-NLS-2$
                    + ") from the database, " + sqle.getErrorCode() + " : " + sqle.getMessage(); //$NON-NLS-1$
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
                String sql = "SELECT country_code FROM " + WORLDCITIES_TABLE_NAME + " WHERE country_name LIKE '" //$NON-NLS-1$
                        + countryName + "';"; //$NON-NLS-1$
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) { // We only select the 1st row ...
                    cc = rs.getString(1);
                }
            }
            return cc;

        } catch (SQLException sqle) {
            String errMsg = "Error while getting country code for (" + countryName + ") from the database, " //$NON-NLS-1$ //$NON-NLS-2$
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
                String sql = "SELECT * FROM " + WORLDCITIES_TABLE_NAME; //$NON-NLS-1$
                stmt = conn.createStatement();
                stmt.setFetchSize(100);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {

                    final Coordinates coords = LocationsProviderFactory.eINSTANCE.createCoordinates();
                    coords.setLatitude(rs.getDouble("latitude")); //$NON-NLS-1$
                    coords.setLongitude(rs.getDouble("longitude")); //$NON-NLS-1$

                    final Country country = LocationsProviderFactory.eINSTANCE.createCountry();
                    country.setName(rs.getString("country_name")); //$NON-NLS-1$
                    country.setCode(rs.getString("country_code")); //$NON-NLS-1$

                    final City city = LocationsProviderFactory.eINSTANCE.createCity();
                    city.setName(rs.getString("city")); //$NON-NLS-1$
                    city.setRegion(rs.getString("region")); //$NON-NLS-1$
                    city.setPostalCode(rs.getString("postal_code")); //$NON-NLS-1$
                    city.setCountry(country);
                    city.setCoordinates(coords);

                    cities.add(city);
                }

            } catch (SQLException sqle) {
                String errMsg = "Error while retrieving the list of cities : " + sqle.getErrorCode() + " : " //$NON-NLS-1$ //$NON-NLS-2$
                        + sqle.getMessage();
                logger.error(errMsg, sqle);
                throw new SQLException(errMsg, sqle);

            } finally {
                JdbcUtils.closeSilently(conn);
                JdbcUtils.closeSilently(stmt);
                JdbcUtils.closeSilently(rs);
            }
        } else {
            logger.warn("Maxmind H2 database containing cities is not yet created, returning empty list ..."); //$NON-NLS-1$
        }

        return cities;
    }

    private static Connection getDBConnection() throws SQLException {
        return DBUtil.newInstance(new File(getGeoliteCityDatabaseDir(), "h2")); //$NON-NLS-1$
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
            String errMsg = "" + sqle.getErrorCode() + " - " + sqle.getMessage(); //$NON-NLS-1$ //$NON-NLS-2$
            logger.error(errMsg, sqle);
            throw new SQLException(sqle);

        } finally {
            JdbcUtils.closeSilently(conn);
            JdbcUtils.closeSilently(rs);
        }
    }
}
