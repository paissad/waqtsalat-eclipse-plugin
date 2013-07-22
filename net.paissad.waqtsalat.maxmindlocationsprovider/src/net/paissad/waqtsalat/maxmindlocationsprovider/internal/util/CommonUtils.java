package net.paissad.waqtsalat.maxmindlocationsprovider.internal.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CommonUtils {

    private CommonUtils() {
    }

    /**
     * @param aLocale The {@link Locale} to use.
     * @return A {@link Map} containing all ISO country codes as keys and all countries names as values.
     */
    public static Map<String, String> getCountries(Locale aLocale) {

        Map<String, String> countries = new HashMap<String, String>();
        String[] allISOCountries = Locale.getISOCountries();
        String lang = aLocale.getLanguage();
        for (String country : allISOCountries) {
            String name = new Locale(lang, country).getDisplayCountry(aLocale);
            countries.put(country, name);
        }
        return countries;
    }

    /**
     * Close quietly a {@link Connection}, or a {@link Statement} or a {@link ResultSet}.
     * 
     * @param obj - The object to close.
     * 
     * @see #closeConnectionQuietly(Connection)
     * @see #closeStatementQuietly(Statement)
     * @see #closeResultsetQuietly(ResultSet)
     */
    public static void closeQuietly(Object obj) {
        if (obj instanceof Connection)
            closeConnectionQuietly((Connection) obj);
        else if (obj instanceof Statement)
            closeStatementQuietly((Statement) obj);
        else if (obj instanceof ResultSet) closeResultsetQuietly((ResultSet) obj);
    }

    public static void closeConnectionQuietly(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
            conn = null;
        }
    }

    public static void closeStatementQuietly(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            stmt = null;
        }
    }

    public static void closeResultsetQuietly(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
            }
            rs = null;
        }
    }

}
