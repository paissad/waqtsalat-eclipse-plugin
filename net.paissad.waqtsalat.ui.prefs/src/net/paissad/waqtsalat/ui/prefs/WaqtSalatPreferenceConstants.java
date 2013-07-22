package net.paissad.waqtsalat.ui.prefs;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.paissad.waqtsalat.core.api.PrayName;

/**
 * Constant definitions for plug-in preferences
 */
public class WaqtSalatPreferenceConstants {

    public static String                    P_LOCATIONS_PROVIDER   = "locationsProvider";

    public static String                    P_CALCULATION_METHOD   = "calculationMethod";

    public static String                    P_JURISTIC_METHOD      = "juristicMethod";

    public static String                    P_USE_SYSTEM_TIMEZONE  = "useSystemTimezone";

    public static String                    P_TIMEZONE             = "timezone";

    public static String                    P_DAYLIGHT_SAVINGS     = "daylightSavings";

    public static String                    P_SOUND_MODE           = "soundMode";

    public static final String              P_ENABLE_NOTIFICATIONS = "enableNotifications";

    /**
     * The key represent the name of the image, the value represents its path.
     * 
     * @see #ICONS
     */
    public static final Map<String, String> ICONS                  = new HashMap<String, String>();

    static {
        ICONS.put(IconsKeys.NOTIFICATION_1, IconsKeys.ICONS_DIR + "notification-1.png");
    }

    public interface IconsKeys {
        String ICONS_DIR      = "icons/";
        String NOTIFICATION_1 = "NOTIFICATION_1";
    }

    public static Entry<String, String> getSoundPrefConstantForPrayname(final PrayName prayName) {
        return new Entry<String, String>() {

            @Override
            public String setValue(String value) {
                return null;
            }

            @Override
            public String getValue() {
                return "sound_" + prayName.getLiteral();
            }

            @Override
            public String getKey() {
                return "P_SOUND_" + prayName.getLiteral();
            }
        };
    }

    public static Entry<String, String> getNotificationsPrefConstant(final PrayName prayName) {
        return new Entry<String, String>() {

            @Override
            public String setValue(String value) {
                return null;
            }

            @Override
            public String getValue() {
                return null;
            }

            @Override
            public String getKey() {
                return "P_NOTIFICATION_" + prayName.getLiteral();
            }
        };
    }

    public interface AdhanValues {
        String NO_SOUND      = "None";
        String DEFAULT_ADHAN = "DefaultAdhan";
        String CUSTOM_ADHAN  = "CustomAdhan";
    }

    public interface NotificationValues {
    }

}
