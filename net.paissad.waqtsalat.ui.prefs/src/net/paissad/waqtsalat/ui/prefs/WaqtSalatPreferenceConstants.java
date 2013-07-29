package net.paissad.waqtsalat.ui.prefs;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.paissad.waqtsalat.core.api.PrayName;
import net.paissad.waqtsalat.ui.prefs.pages.AlertsPrefsPage;
import net.paissad.waqtsalat.ui.prefs.pages.MainPrefsPage;

/**
 * Constant definitions for plug-in preferences
 */
public class WaqtSalatPreferenceConstants {

    public static final String              P_LOCATIONS_PROVIDER   = "locationsProvider";          //$NON-NLS-1$

    public static final String              P_CALCULATION_METHOD   = "calculationMethod";          //$NON-NLS-1$

    public static final String              P_JURISTIC_METHOD      = "juristicMethod";             //$NON-NLS-1$

    public static final String              P_USE_SYSTEM_TIMEZONE  = "useSystemTimezone";          //$NON-NLS-1$

    public static final String              P_TIMEZONE             = "timezone";                   //$NON-NLS-1$

    public static final String              P_DAYLIGHT_SAVINGS     = "daylightSavings";            //$NON-NLS-1$

    public static final String              P_SOUND_MODE           = "soundMode";                  //$NON-NLS-1$

    public static final String              P_ENABLE_NOTIFICATIONS = "enableNotifications";        //$NON-NLS-1$

    public static final String              P_CURRENT_CITY         = "currentCity";                //$NON-NLS-1$

    /**
     * The key represent the name of the image, the value represents its path.
     * 
     * @see #ICONS
     */
    public static final Map<String, String> ICONS                  = new HashMap<String, String>();

    static {
        ICONS.put(IconsKeys.NOTIFICATION_1, IconsKeys.ICONS_DIR + "notification-1.png"); //$NON-NLS-1$
    }

    public interface IconsKeys {
        String ICONS_DIR      = "icons/";        //$NON-NLS-1$
        String NOTIFICATION_1 = "NOTIFICATION_1"; //$NON-NLS-1$
    }

    public static Entry<String, String> getSoundPrefConstantForPrayname(final PrayName prayName) {
        return new Entry<String, String>() {

            @Override
            public String setValue(String value) {
                return null;
            }

            @Override
            public String getValue() {
                return "sound_" + prayName.getLiteral(); //$NON-NLS-1$
            }

            @Override
            public String getKey() {
                return "P_SOUND_" + prayName.getLiteral(); //$NON-NLS-1$
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
                return "P_NOTIFICATION_" + prayName.getLiteral(); //$NON-NLS-1$
            }
        };
    }

    public interface AdhanValues {
        String NO_SOUND      = "None";        //$NON-NLS-1$
        String DEFAULT_ADHAN = "DefaultAdhan"; //$NON-NLS-1$
        String CUSTOM_ADHAN  = "CustomAdhan"; //$NON-NLS-1$
    }

    public interface NotificationValues {
    }

    /**
     * Contains the IDs of preference pages.
     */
    public interface PageID {
        String PREFS_MAIN_PAGE   = MainPrefsPage.PAGE_ID;
        String PREFS_ALERTS_PAGE = AlertsPrefsPage.PAGE_ID;
    }

}
