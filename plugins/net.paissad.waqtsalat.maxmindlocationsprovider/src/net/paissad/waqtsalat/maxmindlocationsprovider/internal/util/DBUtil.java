package net.paissad.waqtsalat.maxmindlocationsprovider.internal.util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.waqtsalat.maxmindlocationsprovider.MaxmindLocationsProviderPlugin;

class DBUtil {

    private static final ILogger logger         = MaxmindLocationsProviderPlugin.getDefault().getLogger();

    private static final String  PROTOCOL       = "jdbc:h2:";
    private static final String  DBNAME         = "worldcities";
    private static final String  SETTINGS       = ";LARGE_TRANSACTIONS=true;OPTIMIZE_IN_SELECT=true;OPTIMIZE_OR=true";

    private static boolean       h2DriverLoaded = false;

    private DBUtil() {
    }

    /**
     * @param h2DatabaseDir - The directory containing the database.
     * @return An instance of database connection.
     * @throws SQLException
     */
    public static Connection newInstance(final File h2DatabaseDir) throws SQLException {
        try {
            if (!h2DriverLoaded) {
                try {
                    Class.forName("org.h2.Driver"); //$NON-NLS-1$
                    h2DriverLoaded = true;
                } catch (ClassNotFoundException e) {
                    logger.error("Error while loading H2 database driver : " + e.getMessage(), e); //$NON-NLS-1$
                }
            }
            String absPath = null;
            try {
                absPath = h2DatabaseDir.getCanonicalPath();
            } catch (IOException e) {
                absPath = h2DatabaseDir.getAbsolutePath();
            }
            return DriverManager.getConnection(PROTOCOL + absPath + "/" + DBNAME + SETTINGS, "", "");

        } catch (SQLException sqle) {
            String errMsg = "Error while getting an instance of database connection : " + sqle.getErrorCode() + " - "
                    + sqle.getMessage();
            logger.error(errMsg, sqle);
            throw new SQLException(errMsg, sqle);
        }
    }

}
