package net.paissad.waqtsalat.maxmindlocationsprovider.internal.util;

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

}
