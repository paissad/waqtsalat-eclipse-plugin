package net.paissad.waqtsalat.ui.helpers;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.TimeZone;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.waqtsalat.core.api.AdjustingMethod;
import net.paissad.waqtsalat.core.api.CalculationMethod;
import net.paissad.waqtsalat.core.api.JuristicMethod;
import net.paissad.waqtsalat.core.api.PrayName;
import net.paissad.waqtsalat.core.api.TimeFormat;
import net.paissad.waqtsalat.locationsprovider.LocationsProviderPlugin;
import net.paissad.waqtsalat.locationsprovider.LocationsProviderPlugin.LocationsProviderExtension;
import net.paissad.waqtsalat.locationsprovider.api.City;
import net.paissad.waqtsalat.locationsprovider.api.IGeolocationProvider;
import net.paissad.waqtsalat.ui.WaqtSalatUIPlugin;
import net.paissad.waqtsalat.ui.beans.DummyCityWrapper;
import net.paissad.waqtsalat.ui.beans.PrayConfig;
import net.paissad.waqtsalat.ui.beans.TimeZoneWrapper;
import net.paissad.waqtsalat.ui.prefs.WaqtSalatPreferenceConstants;
import net.paissad.waqtsalat.ui.prefs.WaqtSalatPreferenceConstants.AdhanValues;
import net.paissad.waqtsalat.ui.prefs.WaqtSalatPreferencePlugin;
import net.paissad.waqtsalat.ui.prefs.WaqtSalatPreferenceStore;

public class PreferenceHelper {

    private static final ILogger logger                  = WaqtSalatUIPlugin.getPlugin().getLogger();

    private static boolean       alreadyWarnedCityNotSet = false;

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
            City city = getCityFromPreference();
            if (city == null) {
                useSystemTimeZone = true;
                if (!alreadyWarnedCityNotSet) {
                    logger.warn("No city is set, so it is not possible to retrieve the timezone from the city/country. Going to use the system default timezone. But as soon as a city is set, and the timzone setting is still set to 'getTimeZoneFromCity', the system timezone will not be used anylonger."); //$NON-NLS-1$
                    alreadyWarnedCityNotSet = true;
                }
            } else {
                String countryCode = city.getCountry().getCode();
                Collection<TimeZone> possibleTimezones = TimeZoneWrapper.getTimezonesFromCountryCode(countryCode);
                if (!possibleTimezones.isEmpty()) {
                    result = possibleTimezones.iterator().next();
                }
            }

        } else if (useSystemTimeZone) {
            result = TimeZone.getDefault();

        } else {
            String prefTimezoneID = getPrefStore().getString(WaqtSalatPreferenceConstants.P_TIMEZONE);
            result = TimeZone.getTimeZone(prefTimezoneID);
        }

        return result == null ? TimeZone.getDefault() : result;
    }

    public static void saveCity(City city) {
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

    /**
     * @return The GeoLocation provider implementation coming from the current Locations Provider specified in the
     *         preference settings. Returns <code>null</code> if no provider is found, or if the current provider does
     *         not support GeoLocation.
     */
    public static IGeolocationProvider getGeoLocationProvider() {
        IGeolocationProvider geolocationProvider = null;
        String providerID = getPrefStore().getString(WaqtSalatPreferenceConstants.P_LOCATIONS_PROVIDER);
        if (providerID != null) {
            LocationsProviderExtension providerExtension = LocationsProviderPlugin.getLocationsProviderManager().get(
                    providerID);
            if (providerExtension != null) {
                if (providerExtension.isGeolocationSupported()) {
                    return providerExtension.getGeolocationProvider();
                }
            }
        }
        return geolocationProvider;
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

    /**
     * @param prayName
     * @return The adhan file to use for sound alerts or <code>null</code> if none specified.
     */
    public static File getAdhanFile(final PrayName prayName) {

        String alertMode = getPrefStore().getString(WaqtSalatPreferenceConstants.P_SOUND_MODE);

        if (AdhanValues.NO_SOUND.equals(alertMode)) {
            return null;

        } else if (AdhanValues.DEFAULT_ADHAN.equals(alertMode)) {
            try {
                return WaqtSalatPreferenceConstants.getDefaultAdhanFile();
            } catch (Exception e) {
                logger.error("Error while retrieving default adhan file : " + e.getMessage(), e);
                return null;
            }

        } else if (AdhanValues.CUSTOM_ADHAN.equals(alertMode)) {
            String prefName = WaqtSalatPreferenceConstants.getSoundPrefConstantForPrayname(prayName).getKey();
            String filePath = getPrefStore().getString(prefName);
            return new File(filePath);

        } else {
            String errMsg = "The stored sound alert mode '" + alertMode + "' is not supported.";
            logger.error(errMsg);
            throw new IllegalArgumentException(errMsg);
        }
    }

}
