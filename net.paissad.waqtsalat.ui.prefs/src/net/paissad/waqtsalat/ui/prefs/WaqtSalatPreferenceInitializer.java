package net.paissad.waqtsalat.ui.prefs;

import java.util.Map.Entry;
import java.util.TimeZone;

import net.paissad.waqtsalat.core.api.AdjustingMethod;
import net.paissad.waqtsalat.core.api.CalculationMethod;
import net.paissad.waqtsalat.core.api.JuristicMethod;
import net.paissad.waqtsalat.core.api.PrayName;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Class used to initialize default preference values.
 */
public class WaqtSalatPreferenceInitializer extends AbstractPreferenceInitializer {

    @Override
    public void initializeDefaultPreferences() {

        IPreferenceStore store = WaqtSalatPreferencePlugin.getDefault().getPreferenceStore();

        store.setDefault(WaqtSalatPreferenceConstants.P_CALCULATION_METHOD, CalculationMethod.JAFARI.getLiteral());

        store.setDefault(WaqtSalatPreferenceConstants.P_JURISTIC_METHOD, JuristicMethod.SHAFII.getLiteral());

        store.setDefault(WaqtSalatPreferenceConstants.P_ADJUSTING_METHOD, AdjustingMethod.ANGLE_BASED.getLiteral());

        store.setDefault(WaqtSalatPreferenceConstants.P_OFFSETS, "0,0,0,0,0,0,0"); //$NON-NLS-1$

        store.setDefault(WaqtSalatPreferenceConstants.P_GET_TIMEZONE_FROM_COUNTRY, false);
        store.setDefault(WaqtSalatPreferenceConstants.P_USE_SYSTEM_TIMEZONE, true);
        store.setDefault(WaqtSalatPreferenceConstants.P_DAYLIGHT_SAVINGS, true);
        store.setDefault(WaqtSalatPreferenceConstants.P_TIMEZONE, TimeZone.getDefault().getID());

        store.setDefault(WaqtSalatPreferenceConstants.P_SOUND_MODE,
                WaqtSalatPreferenceConstants.AdhanValues.DEFAULT_ADHAN);

        store.setDefault(WaqtSalatPreferenceConstants.P_ENABLE_NOTIFICATIONS, true);

        store.setDefault(WaqtSalatPreferenceConstants.P_GET_LOCATION_FROM_IP_ADDRESS, false);

        store.setDefault(WaqtSalatPreferenceConstants.P_LOCATIONS_PROVIDER,
                "net.paissad.waqtsalat.locationsprovider.maxmindlocationsprovider"); //$NON-NLS-1$ TODO: really dirty ...

        store.setDefault(WaqtSalatPreferenceConstants.P_HIDE_SUNRISE, false);
        store.setDefault(WaqtSalatPreferenceConstants.P_HIDE_SUNSET, true);

        store.setDefault(WaqtSalatPreferenceConstants.P_AUTOMATIC_UPDATE_AT_MIDNIGHT, true);

        for (PrayName prayName : PrayName.values()) {
            Entry<String, String> entry = WaqtSalatPreferenceConstants.getNotificationsPrefConstant(prayName);
            store.setDefault(entry.getKey(), true);
        }
    }

}
