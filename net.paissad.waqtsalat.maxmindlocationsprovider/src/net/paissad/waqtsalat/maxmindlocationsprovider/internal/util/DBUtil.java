package net.paissad.waqtsalat.maxmindlocationsprovider.internal.util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.waqtsalat.maxmindlocationsprovider.MaxmindLocationsProviderPlugin;

class DBUtil {

    private static final ILogger logger   = MaxmindLocationsProviderPlugin.getDefault().getLogger();

    private static final String  PROTOCOL = "jdbc:h2:";
    private static final String  DBNAME   = "worldcitiespop";
    private static final String  SETTINGS = ";LARGE_TRANSACTIONS=true;OPTIMIZE_IN_SELECT=true;OPTIMIZE_OR=true";

    // static {
    // System.setProperty("h2.baseDir", new File(BASEDIR).getAbsolutePath());
    // }

    private DBUtil() {
    }

    /**
     * @param h2DBFile - The file representing the existing h2 database (or non existing yet, when the h2 database is to
     *        be created).
     * @return An instance of database connection.
     * @throws SQLException
     */
    public static Connection newInstance(final File h2DBFile) throws SQLException {
        try {
            String absPath = null;
            try {
                absPath = h2DBFile.getCanonicalPath();
            } catch (IOException e) {
                absPath = h2DBFile.getAbsolutePath();
            }
            return DriverManager.getConnection(PROTOCOL + absPath + "/" + DBNAME + SETTINGS, "", "");

        } catch (SQLException sqle) {
            String errMsg = "Error while getting an instance of database connection.\n";
            logger.error(errMsg, sqle);
            throw new SQLException(errMsg, sqle);
        }
    }

}
