package net.paissad.waqtsalat.maxmindlocationsprovider.internal.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import net.paissad.waqtsalat.maxmindlocationsprovider.internal.util.CommonUtils;

import org.h2.util.JdbcUtils;
import org.osgi.framework.BundleException;

public class GeoliteCityTool {

    // private static final String GEOLITE_CITY_DOWNLOAD_LINK =
    // "http://geolite.maxmind.com/download/geoip/database/GeoLiteCity_CSV/GeoLiteCity-latest.zip";
    // FIXME: do not forget to update link !

    private static final String GEOLITE_CITY_DEFAULT_DOWNLOAD_LINK = "http://paissad.net/GeoLiteCity-latest.zip";

    private static final String PROTOCOL                           = "jdbc:h2:";
    private static final String DBNAME                             = "worldcities";
    private static final String SETTINGS                           = ";LARGE_TRANSACTIONS=true;OPTIMIZE_IN_SELECT=true;OPTIMIZE_OR=true";

    private static final String WORLDCITIES_TABLE_NAME             = "WORLDCITIES";

    private static final String GEOLITE_CITY_CSV_PATH_TAG          = "___WORLDCITIES_CSV_ABSOLUTE_PATH___";

    private static final String CSV_CHARSET                        = "ISO-8859-15";

    private static final String SQL_CREATE_TABLE                   = new StringBuilder()
                                                                           .append("CREATE TABLE IF NOT EXISTS ")
                                                                           .append(WORLDCITIES_TABLE_NAME)
                                                                           .append(" (")
                                                                           .append("country_code   VARCHAR(2)    NOT NULL ")
                                                                           .append(", country_name VARCHAR(60)   NOT NULL ")
                                                                           .append(", region       VARCHAR (3)   NOT NULL")
                                                                           .append(", city         VARCHAR(60)   NOT NULL")
                                                                           .append(", postal_code  VARCHAR (10)")
                                                                           .append(", latitude     FLOAT         NOT NULL")
                                                                           .append(", longitude    FLOAT         NOT NULL")
                                                                           // .append(", UNIQUE (country_code, city, latitude, longitude) ")
                                                                           .append(" ) AS SELECT DISTINCT COUNTRY, COUNTRY, REGION, CITY, POSTALCODE, LATITUDE, LONGITUDE ")
                                                                           .append("FROM CSVREAD ('")
                                                                           .append(GEOLITE_CITY_CSV_PATH_TAG)
                                                                           .append("', null, '")
                                                                           .append(CSV_CHARSET)
                                                                           .append("') WHERE CITY IS NOT NULL AND CITY <> '';")
                                                                           .toString();

    private static final String SQL_UPDATE_COUNTRY_CODE            = new StringBuilder()
                                                                           .append("UPDATE ")
                                                                           .append(WORLDCITIES_TABLE_NAME)
                                                                           .append(" SET country_code = UPPER(country_code);")
                                                                           .toString();

    private static final String SQL_UPDATE_COUNTRY_NAME            = new StringBuilder()
                                                                           .append("UPDATE ")
                                                                           .append(WORLDCITIES_TABLE_NAME)
                                                                           .append(" SET country_name = ? WHERE country_code LIKE ?;")
                                                                           .toString();

    private static final String __TAG_NEW_CSV_FILEPATH__           = "__TAG_NEW_CSV_FILEPATH";

    private static final String SQL_CREATE_NEW_CSV_FILE            = new StringBuilder("CALL CSVWRITE ('")
                                                                           .append(__TAG_NEW_CSV_FILEPATH__)
                                                                           .append("', 'SELECT * FROM ")
                                                                           .append(WORLDCITIES_TABLE_NAME)
                                                                           .append("');").toString();

    /**
     * Download the default GeoLiteCity CSV file and create a new "enhanced" one more appropriate for our needs.<br>
     * 
     * @param args
     * @throws IOException
     * @throws BundleException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void main(String[] args) throws IOException, BundleException, ClassNotFoundException, SQLException {

        long timeStart = System.currentTimeMillis();

        if (args.length != 1) {
            throw new IllegalArgumentException(
                    "You must specify the directory where to save the new generated GeoliteCity CSV file."); //$NON-NLS-1$
        }
        File destDir = new File(args[0]);
        if (!destDir.exists()) {
            if (!destDir.mkdirs()) {
                throw new IOException("Unable to create the directory  " + destDir);
            }
        } else if (destDir.isFile()) {
            throw new IllegalArgumentException(destDir + " exists but is not a directory.");
        }

        File workingDir = new File(destDir, UUID.randomUUID().toString());
        if (!workingDir.mkdirs()) {
            throw new IOException("Unable to create the working directory.");
        }

        System.out.println("The working directory is " + workingDir);

        downloadDefaultCSVFile(workingDir);

        for (File f : workingDir.listFiles()) {
            if (f.getName().endsWith(".csv")) {
                removeCopyrightLine(f);
            }
        }

        File databaseDir = new File(workingDir, "h2");
        File csvLocationFile = new File(workingDir, "GeoLiteCity-Location.csv");
        createTable(databaseDir, csvLocationFile);

        String prefix = new SimpleDateFormat("yyyyMMdd'-'HHmmss'_'").format(new Date());
        File newCSVFile = new File(workingDir, prefix + "GeoLiteCity-Location.csv");
        createNewCSVFile(newCSVFile, databaseDir);

        System.out.println("Zipping new CSV file");
        String zipFile = newCSVFile.getName().replaceAll("\\.csv", ".zip");
        zip(newCSVFile, new File(workingDir, zipFile));

        long timeStop = System.currentTimeMillis();

        System.out.println("All Processes finished in " + (int) (timeStop - timeStart) / 1000 + " seconds.");
    }

    private static void downloadDefaultCSVFile(final File destDir) throws IOException {

        try {
            System.out.println("Downloading GeoLiteCity CSV file to " + destDir);

            URL url = new URL(GEOLITE_CITY_DEFAULT_DOWNLOAD_LINK);
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
                File zippedCSVFile = new File(destDir, "GeoLiteCity-latest.zip"); //$NON-NLS-1$
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
                }
                if (currentLength != contentLength) {
                    throw new IOException("Expected to download " + contentLength + " bytes, but got only " //$NON-NLS-1$ //$NON-NLS-2$
                            + currentLength);
                }
                out.close();
                out.flush();
                System.out.println("Downloaded successfully.");

                System.out.println("Unzipping file"); //$NON-NLS-1$
                ZipFile zipFile = new ZipFile(zippedCSVFile);
                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    String name = entry.getName().replaceAll(".*\\/", ""); //$NON-NLS-1$
                    File entryFile = new File(destDir, name);
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
                System.out.println("Finished unzipping file.");

            } finally {
                for (Closeable closeable : new Closeable[] { in, out }) {
                    try {
                        closeable.close();
                    } catch (Exception e) {
                    }
                }
            }

        } catch (IOException ioe) {
            throw new IOException("Error while downloading GeoLiteCity CSV file : " + ioe.getMessage(), ioe); //$NON-NLS-1$

        } finally {
        }
    }

    private static void removeCopyrightLine(final File file) throws IOException {
        System.out.println("Removing copyright line from file " + file);

        File tempFile = null;
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            tempFile = File.createTempFile(file.getName() + "_", ".temp", file.getParentFile()); //$NON-NLS-1$ //$NON-NLS-2$

            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName(CSV_CHARSET)),
                    8192);

            String line = reader.readLine(); // read first line.
            if (line.toLowerCase(Locale.ENGLISH).trim().startsWith("copyright")) { //$NON-NLS-1$
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile),
                        Charset.forName(CSV_CHARSET)), 8192);

                int charsRead;
                char[] buf = new char[8192];
                while ((charsRead = reader.read(buf, 0, buf.length)) > 0) {
                    writer.write(buf, 0, charsRead);
                }
                writer.flush();
                if (!tempFile.renameTo(file)) {
                    throw new IOException("Unable to move file : " + tempFile + " to " + file);
                }
            }

        } catch (IOException e) {
            throw new IOException("Error while removing first line of file " + file + " : " + e.getMessage(), e); //$NON-NLS-1$

        } finally {
            for (Closeable closeable : new Closeable[] { reader, writer }) {
                try {
                    closeable.close();
                } catch (Exception e) {
                }
            }
            if (tempFile.exists()) tempFile.delete();
        }
    }

    private static void createTable(final File databaseDir, final File csvFile) throws SQLException, IOException,
            ClassNotFoundException {

        if (!databaseDir.exists()) {
            databaseDir.mkdirs();
        }

        long startTime = System.currentTimeMillis();

        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        try {
            conn = getDBConnection(databaseDir);
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            final String sqlCreateTable = SQL_CREATE_TABLE.replaceAll(GEOLITE_CITY_CSV_PATH_TAG,
                    csvFile.getCanonicalPath());
            stmt.execute(sqlCreateTable);

            System.out.println("The table '" + WORLDCITIES_TABLE_NAME + "' is created successfully.");

            stmt.execute(SQL_UPDATE_COUNTRY_CODE);
            System.out.println("Finished putting country codes in uppercase into the table '" + WORLDCITIES_TABLE_NAME
                    + "'");

            pstmt = conn.prepareStatement(SQL_UPDATE_COUNTRY_NAME);
            Map<String, String> countries = CommonUtils.getCountries(Locale.ENGLISH);
            int count = 0;
            for (Iterator<Entry<String, String>> iter = countries.entrySet().iterator(); iter.hasNext();) {
                Entry<String, String> entry = iter.next();
                pstmt.setString(1, entry.getValue()); // country_name
                pstmt.setString(2, entry.getKey()); // country_code
                pstmt.addBatch();
                if (++count % 100000 == 0) {
                    pstmt.executeBatch();
                    pstmt.clearBatch();
                }
            }
            pstmt.executeBatch();
            System.out.println("Updated countries names successfully into the table '" + WORLDCITIES_TABLE_NAME + "'");

            conn.commit();

            long endTime = System.currentTimeMillis();
            System.out.println("Database '" + WORLDCITIES_TABLE_NAME + "' created in " + (int) (endTime - startTime)
                    / 1000 + " seconds.");

        } catch (SQLException sqle) {
            String errMsg = "An error occured while creating the table (" + WORLDCITIES_TABLE_NAME + "), "
                    + sqle.getErrorCode() + " : " + sqle.getMessage();
            System.err.println(errMsg);
            if (conn != null && !conn.isClosed()) {
                conn.rollback();
            }
            throw new SQLException(errMsg, sqle);

        } finally {
            JdbcUtils.closeSilently(conn);
            JdbcUtils.closeSilently(stmt);
            JdbcUtils.closeSilently(pstmt);
        }
    }

    private static void createNewCSVFile(final File newCSVFile, final File databaseDir) throws SQLException,
            IOException, ClassNotFoundException {

        System.out.println("Creating new CSV file " + newCSVFile);

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getDBConnection(databaseDir);
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql = SQL_CREATE_NEW_CSV_FILE.replaceAll(__TAG_NEW_CSV_FILEPATH__, newCSVFile.getCanonicalPath());
            stmt.execute(sql);
            conn.commit();

        } finally {
            JdbcUtils.closeSilently(conn);
            JdbcUtils.closeSilently(stmt);
        }
    }

    private static void zip(File source, File zipFile) throws IOException {
        InputStream in = null;
        ZipOutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(source), 8192);
            out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile), 8192));

            ZipEntry zipEntry = new ZipEntry("GeoLiteCity-Location.csv"); //$NON-NLS-1$
            zipEntry.setTime(source.lastModified());
            out.putNextEntry(zipEntry);
            int bytesRead;
            byte[] data = new byte[8192];
            while ((bytesRead = in.read(data, 0, data.length)) > 0) {
                out.write(data, 0, bytesRead);
            }
            in.close();
            out.closeEntry();
            out.flush();

        } catch (IOException e) {
            throw new IOException("Error while zipping the file : " + e.getMessage(), e); //$NON-NLS-1$

        } finally {
            for (Closeable closeable : new Closeable[] { in, out }) {
                try {
                    closeable.close();
                } catch (Exception e) {
                }
            }
        }
    }

    private static Connection getDBConnection(final File dir) throws SQLException, IOException, ClassNotFoundException {
        Class.forName("org.h2.Driver"); //$NON-NLS-1$
        return DriverManager.getConnection(PROTOCOL + dir.getCanonicalPath() + "/" + DBNAME + SETTINGS, "", "");
    }
}
