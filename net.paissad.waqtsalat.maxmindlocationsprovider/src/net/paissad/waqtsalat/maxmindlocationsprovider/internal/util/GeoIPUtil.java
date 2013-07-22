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
import java.util.concurrent.TimeUnit;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.waqtsalat.locationsprovider.LocationsProviderFactory;
import net.paissad.waqtsalat.locationsprovider.api.City;
import net.paissad.waqtsalat.locationsprovider.api.Coordinates;
import net.paissad.waqtsalat.locationsprovider.api.Country;
import net.paissad.waqtsalat.maxmindlocationsprovider.MaxmindLocationsProviderPlugin;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;

public class GeoIPUtil {

    private static final ILogger logger                     = MaxmindLocationsProviderPlugin.getDefault().getLogger();

    // public static final String GEOLITE_CITY_DOWNLOAD_LINK =
    // "http://geolite.maxmind.com/download/geoip/database/GeoLiteCity_CSV/GeoLiteCity-latest.zip";
    public static final String   GEOLITE_CITY_DOWNLOAD_LINK = "http://paissad.net/server_services.php";

    private static final String  GEOLITE_CITY_CSV_PATH      = "/.settings/" + MaxmindLocationsProviderPlugin.PLUGIN_ID
                                                                    + "/database/GeoLiteCity.csv";

    private static final String  GEOLITE_CITY_CSV_PATH_TAG  = "___WORLDCITIES_CSV_ABSOLUTE_PATH___";

    private static final String  CSV_CHARSET                = "ISO-8859-15";

    private static final String  WORLDCITIES_TABLE_NAME     = "WORLDCITIES";

    private static final String  SQL_DROP_TABLE             = new StringBuilder().append("DROP TABLE IF EXISTS ")
                                                                    .append(WORLDCITIES_TABLE_NAME)
                                                                    .append(" CASCADE CONSTRAINTS;").toString();

    private static final String  SQL_CREATE_TABLE           = new StringBuilder()
                                                                    .append("CREATE TABLE IF NOT EXISTS ")
                                                                    .append(WORLDCITIES_TABLE_NAME)
                                                                    .append(" (")
                                                                    .append("country_code VARCHAR(2)   NOT NULL, ")
                                                                    .append("city         VARCHAR(40)  NOT NULL, ")
                                                                    .append("region       VARCHAR (3)  NOT NULL, ")
                                                                    .append("latitude     FLOAT        NOT NULL, ")
                                                                    .append("longitude    FLOAT        NOT NULL, ")
                                                                    .append("UNIQUE (country_code, city, latitude, longitude) ")
                                                                    .append(" ) AS SELECT COUNTRY, ACCENTCITY, REGION, LATITUDE, LONGITUDE ")
                                                                    .append("FROM CSVREAD ('")
                                                                    .append(GEOLITE_CITY_CSV_PATH_TAG)
                                                                    .append("', null, '").append(CSV_CHARSET)
                                                                    .append("') WHERE POPULATION IS NOT NULL;")
                                                                    .toString();

    private static final String  SQL_ALTER_TABLE            = new StringBuilder()
                                                                    .append("ALTER TABLE ")
                                                                    .append(WORLDCITIES_TABLE_NAME)
                                                                    .append(" ADD country_name VARCHAR(40) BEFORE city;")
                                                                    .toString();

    private static final String  SQL_UPDATE_COUNTRY_CODE    = new StringBuilder().append("UPDATE ")
                                                                    .append(WORLDCITIES_TABLE_NAME)
                                                                    .append(" SET country_code = UPPER(country_code);")
                                                                    .toString();

    private static final String  SQL_UPDATE_COUNTRY_NAME    = new StringBuilder()
                                                                    .append("UPDATE ")
                                                                    .append(WORLDCITIES_TABLE_NAME)
                                                                    .append(" SET country_name = ? WHERE country_code LIKE ?;")
                                                                    .toString();

    // =================================================================================================================

    private GeoIPUtil() {
    }

    /**
     * @return The file representing the H2 database. The file may not exist.
     */
    public static File getGeoLiteCityCSVFile() {
        return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(GEOLITE_CITY_CSV_PATH)).getLocation().toFile();
    }

    public static void downloadCSVDatabase() throws IOException {

        URL url = new URL(GEOLITE_CITY_DOWNLOAD_LINK);
        URLConnection urlConnection = url.openConnection();
        urlConnection.setConnectTimeout((int) TimeUnit.MINUTES.toMillis(1));
        urlConnection.setAllowUserInteraction(false);
        urlConnection.connect();
        int contentLength = urlConnection.getContentLength();
        byte[] data = new byte[8192];
        int bytesRead;
        InputStream in = null;
        OutputStream out = null;
        int currentLength = 0;
        try {
            File file = getGeoLiteCityCSVFile();
            in = urlConnection.getInputStream();
            out = new BufferedOutputStream(new FileOutputStream(file), data.length);
            while ((bytesRead = in.read(data, 0, data.length)) > 0) {
                out.write(data, 0, bytesRead);
                currentLength += bytesRead;
            }
            if (currentLength != contentLength) {
                throw new IOException("Expected to download " + contentLength + " bytes, but got only " + currentLength);
            }
            out.flush();
        } finally {
            for (Closeable closeable : new Closeable[] { in, out }) {
                try {
                    closeable.close();
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * Creates the database/table if it did not exist yet.
     * 
     * @throws IOException
     * @throws SQLException
     */
    public void createTable() throws IOException, SQLException {

        long startTime = System.currentTimeMillis();

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getDBConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            stmt.execute(SQL_DROP_TABLE);

            String h2DatabasePath = null;
            try {
                getGeoLiteCityCSVFile().getCanonicalPath();
            } catch (IOException e) {
                h2DatabasePath = getGeoLiteCityCSVFile().getAbsolutePath();
            }

            final String sqlCreateTable = SQL_CREATE_TABLE.replaceAll(GEOLITE_CITY_CSV_PATH_TAG, h2DatabasePath);
            stmt.execute(sqlCreateTable);

            logger.info("The table '" + WORLDCITIES_TABLE_NAME + "' is created successfully.");

            stmt.execute(SQL_ALTER_TABLE);
            logger.info("The table '" + WORLDCITIES_TABLE_NAME + "' is altered successfully.");

            stmt.execute(SQL_UPDATE_COUNTRY_CODE);
            logger.info("Finished putting country codes in uppercase.");

            conn.commit();

            long endTime = System.currentTimeMillis();
            logger.info("Database '" + WORLDCITIES_TABLE_NAME + "' created in " + (int) (endTime - startTime) / 1000
                    + " seconds.");

        } catch (SQLException sqle) {
            String errMsg = "An error occured while creating the table (" + WORLDCITIES_TABLE_NAME + "), "
                    + sqle.getErrorCode() + " : " + sqle.getMessage();
            logger.error(errMsg, sqle);
            if (conn != null && !conn.isClosed()) {
                conn.rollback();
            }
            throw new SQLException(errMsg, sqle);

        } finally {
            CommonUtils.closeQuietly(conn);
            CommonUtils.closeQuietly(stmt);
        }
    }

    /**
     * Update the country names entries of the database using the default {@link Locale}.
     * 
     * @throws SQLException
     */
    public void updateTableCountryName() throws SQLException {
        updateTableCountryName(Locale.getDefault());
    }

    /**
     * Update the country names entries of the database using the specified {@link Locale}.
     * 
     * @param aLocale The <code>Locale</code> to use.
     * @throws SQLException
     * 
     */
    public void updateTableCountryName(Locale aLocale) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getDBConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(SQL_UPDATE_COUNTRY_NAME);
            Map<String, String> countries = CommonUtils.getCountries(aLocale);
            Iterator<Entry<String, String>> it = countries.entrySet().iterator();
            while (it.hasNext()) {
                Entry<String, String> entry = it.next();
                String cc = entry.getKey(); // cc like Country Code
                pstmt.setString(1, countries.get(cc));
                pstmt.setString(2, cc);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            logger.info("Finished updating the country names into table '" + WORLDCITIES_TABLE_NAME
                    + "' using Locale '" + aLocale.getDisplayName() + "'.");

            conn.commit();

        } catch (SQLException sqle) {
            String errMsg = "Error while updating names of countries into the database, " + sqle.getErrorCode() + " : "
                    + sqle.getMessage();
            logger.error(errMsg, sqle);
            throw new SQLException(errMsg, sqle);

        } finally {
            CommonUtils.closeQuietly(conn);
            CommonUtils.closeQuietly(pstmt);
        }
    }

    /**
     * @param country The country.
     * @param city The city.
     * @return The {@link Coordinates} of the couple <i>city/country</i>, return <code>null</code> if the couple of
     *         city/country is not found into the database or when an error occurs.
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
            CommonUtils.closeQuietly(conn);
            CommonUtils.closeQuietly(stmt);
            CommonUtils.closeQuietly(rs);
        }
    }

    /**
     * Get the country ISO Code from a given country name.
     * 
     * @param countryName The full name of the country.
     * 
     * @return The String 2 letters representation of the country code in upper case.
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
            CommonUtils.closeQuietly(conn);
            CommonUtils.closeQuietly(stmt);
            CommonUtils.closeQuietly(rs);
        }
    }

    public static Collection<City> getAllCities() throws SQLException {

        // TODO: use a cache ...

        final Collection<City> cities = new ArrayList<City>();

        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;

        try {
            conn = getDBConnection();
            conn.setAutoCommit(false);
            String sql = "SELECT * FROM " + WORLDCITIES_TABLE_NAME;
            stmt = conn.createStatement();
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
                city.setCountry(country);
                city.setCoordinates(coords);

                cities.add(city);
            }

            return cities;

        } catch (SQLException sqle) {
            String errMsg = "Error while retrieving the list of cities : " + sqle.getErrorCode() + " : "
                    + sqle.getMessage();
            logger.error(errMsg, sqle);
            throw new SQLException(errMsg, sqle);

        } finally {
            CommonUtils.closeQuietly(conn);
            CommonUtils.closeQuietly(stmt);
            CommonUtils.closeQuietly(rs);
        }
    }

    public static Connection getDBConnection() throws SQLException {
        return DBUtil.newInstance(getGeoLiteCityCSVFile());
    }
}
