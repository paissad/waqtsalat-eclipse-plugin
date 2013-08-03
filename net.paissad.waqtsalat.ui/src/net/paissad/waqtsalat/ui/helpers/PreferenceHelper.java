package net.paissad.waqtsalat.ui.helpers;

import java.io.IOException;
import java.util.Collection;
import java.util.TimeZone;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.waqtsalat.core.api.AdjustingMethod;
import net.paissad.waqtsalat.core.api.CalculationMethod;
import net.paissad.waqtsalat.core.api.JuristicMethod;
import net.paissad.waqtsalat.core.api.TimeFormat;
import net.paissad.waqtsalat.locationsprovider.api.City;
import net.paissad.waqtsalat.ui.WaqtSalatUIPlugin;
import net.paissad.waqtsalat.ui.beans.DummyCityWrapper;
import net.paissad.waqtsalat.ui.beans.PrayConfig;
import net.paissad.waqtsalat.ui.beans.TimeZoneWrapper;
import net.paissad.waqtsalat.ui.prefs.WaqtSalatPreferenceConstants;
import net.paissad.waqtsalat.ui.prefs.WaqtSalatPreferencePlugin;
import net.paissad.waqtsalat.ui.prefs.WaqtSalatPreferenceStore;

public class PreferenceHelper {

    private static final ILogger logger = WaqtSalatUIPlugin.getPlugin().getLogger();

    private PreferenceHelper() {
    }

    /**
     * @return The TimeZone "computed" from preference settings. If the TimeZone could be retrieved correctly, the
     *         system TimeZone is resulted.
     */
    public static TimeZone getTimezoneFromPreference() {

        TimeZone result = null;

        boolean getTimezoneFromCountry = getPrefStore().getBoolean(
                WaqtSalatPreferenceConstants.P_GET_TIMEZONE_FROM_COUNTRY);
        boolean useSystemTimeZone = getPrefStore().getBoolean(WaqtSalatPreferenceConstants.P_USE_SYSTEM_TIMEZONE);

        if (getTimezoneFromCountry) {
            String countryCode = getCityFromPreference().getCountry().getCode();
            Collection<TimeZone> possibleTimezones = TimeZoneWrapper.getTimezonesFromCountryCode(countryCode);
            if (!possibleTimezones.isEmpty()) {
                result = possibleTimezones.iterator().next();
            }

        } else if (useSystemTimeZone) {
            result = TimeZone.getDefault();

        } else {
            String prefTimezoneID = getPrefStore().getString(WaqtSalatPreferenceConstants.P_TIMEZONE);
            result = TimeZone.getTimeZone(prefTimezoneID);
        }

        return result == null ? TimeZone.getDefault() : result;
    }

    public static void saveCityPreference(City city) {
        // We use the city wrapper because it is hard, really hard to save the city EObject
        // instance which is not contained into a resource.
        // Using the wrapper which a serializable object, but not an EObject helps as a
        // workaround.
        DummyCityWrapper cityWrapper = new DummyCityWrapper(city);
        getPrefStore().setValue(WaqtSalatPreferenceConstants.P_CURRENT_CITY, cityWrapper);
        try {
            getPrefStore().save();
        } catch (IOException e) {
            logger.error("Error while saving the city preference : " + e.getMessage(), e); //$NON-NLS-1$
        }
    }

    public static City getCityFromPreference() {
        Object obj = getPrefStore().getObject(WaqtSalatPreferenceConstants.P_CURRENT_CITY);
        if (obj == null) {
            return null;

        } else if (!(obj instanceof DummyCityWrapper)) {
            throw new IllegalArgumentException(
                    "The stored city into prefererence is not an instance of DummyCityWrapper"); //$NON-NLS-1$

        } else {
            return ((DummyCityWrapper) obj).unwrap();
        }
    }

    public static WaqtSalatPreferenceStore getPrefStore() {
        return WaqtSalatPreferencePlugin.getDefault().getPreferenceStore();
    }

    public static boolean getHideSunrise() {
        return getPrefStore().getBoolean(WaqtSalatPreferenceConstants.P_HIDE_SUNRISE);
    }

    public static boolean getHideSunset() {
        return getPrefStore().getBoolean(WaqtSalatPreferenceConstants.P_HIDE_SUNSET);
    }

    public static boolean getAutomaticUpdateAtMidnight() {
        return getPrefStore().getBoolean(WaqtSalatPreferenceConstants.P_AUTOMATIC_UPDATE_AT_MIDNIGHT);
    }

    public static PrayConfig getPrayConfig() {

        final PrayConfig cfg = new PrayConfig();

        cfg.setTimeZone(PreferenceHelper.getTimezoneFromPreference());

        cfg.setTimeFormat(TimeFormat.TIME_24);

        CalculationMethod calculationMethod = CalculationMethod.valueOf(PreferenceHelper.getPrefStore().getString(
                WaqtSalatPreferenceConstants.P_CALCULATION_METHOD));
        cfg.setCalculationMethod(calculationMethod);

        JuristicMethod asrJuristicMethod = JuristicMethod.valueOf(PreferenceHelper.getPrefStore().getString(
                WaqtSalatPreferenceConstants.P_JURISTIC_METHOD));
        cfg.setAsrJuristicMethod(asrJuristicMethod);

        AdjustingMethod adjustingMethod = AdjustingMethod.valueOf(PreferenceHelper.getPrefStore().getString(
                WaqtSalatPreferenceConstants.P_ADJUSTING_METHOD));
        cfg.setAdjustingMethod(adjustingMethod);

        String[] offsetsAsStrings = PreferenceHelper.getPrefStore().getString(WaqtSalatPreferenceConstants.P_OFFSETS)
                .split("\\s*,\\s*"); //$NON-NLS-1$
        int[] offsets = new int[offsetsAsStrings.length];
        for (int i = 0; i < offsets.length; i++) {
            offsets[i] = Integer.parseInt(offsetsAsStrings[i]);
        }
        cfg.setOffsets(offsets);

        return cfg;
    }

}
