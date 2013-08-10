package net.paissad.waqtsalat.ui.beans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Locale;
import java.util.TimeZone;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.waqtsalat.ui.WaqtSalatUIPlugin;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class TimeZoneWrapper {

    private static final ILogger               logger = WaqtSalatUIPlugin.getPlugin().getLogger();

    private final String                       countryCode;
    private final String                       timezoneID;
    private final float                        gmtOffset;
    private final float                        dstOffset;
    private final float                        rawOffset;

    /** Immutable collection of TimeZone wrappers. */
    private static Collection<TimeZoneWrapper> timezoneWrappers;

    static {
        try {
            timezoneWrappers = initTimezoneWrappers();
        } catch (Exception e) {
            timezoneWrappers = Collections.emptyList();
            logger.error("Error while initializing timezone wrappers, going to use empty list : " + e.getMessage(), e); //$NON-NLS-1Ò$
        }
    }

    private TimeZoneWrapper(String countryCode, String timezoneID, float gmtOffset, float dstOffset, float rawOffset) {
        this.countryCode = countryCode;
        this.timezoneID = timezoneID;
        this.gmtOffset = gmtOffset;
        this.dstOffset = dstOffset;
        this.rawOffset = rawOffset;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getTimezoneID() {
        return this.timezoneID;
    }

    public float getGmtOffset() {
        return this.gmtOffset;
    }

    public float getDstOffset() {
        return this.dstOffset;
    }

    public float getRawOffset() {
        return this.rawOffset;
    }

    private static Collection<TimeZoneWrapper> initTimezoneWrappers() throws IOException {
        final Collection<TimeZoneWrapper> tzWrappers = new LinkedList<TimeZoneWrapper>();
        BufferedReader reader = null;
        try {
            Bundle bundle = Platform.getBundle(WaqtSalatUIPlugin.BUNDLE_SYMBOLIC_NAME);
            URL resource = bundle.getResource("resources/timeZones.txt"); //$NON-NLS-1$
            URL resolvedURL = FileLocator.resolve(resource);
            reader = new BufferedReader(new InputStreamReader(resolvedURL.openStream(), "UTF-8")); //$NON-NLS-1$
            reader.readLine(); // read/skip first line.
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splittedLine = line.split("\\s+"); //$NON-NLS-1$
                String countryCode = splittedLine[0];
                String timezoneID = splittedLine[1];
                float gmtOffset = Float.parseFloat(splittedLine[2]);
                float dstOffset = Float.parseFloat(splittedLine[3]);
                float rawOffset = Float.parseFloat(splittedLine[4]);
                tzWrappers.add(new TimeZoneWrapper(countryCode, timezoneID, gmtOffset, dstOffset, rawOffset));
            }
            return Collections.unmodifiableCollection(tzWrappers);

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * @param countryCode
     * @return The collection of TimeZone wrappers. If the specified country code is null or blank, then an empty list
     *         is returned.
     */
    public static Collection<TimeZoneWrapper> getTimezoneWrappersFromCountryCode(final String countryCode) {
        final Collection<TimeZoneWrapper> result = new LinkedList<TimeZoneWrapper>();
        if (countryCode != null && !countryCode.trim().isEmpty()) {
            for (Iterator<TimeZoneWrapper> iterator = timezoneWrappers.iterator(); iterator.hasNext();) {
                TimeZoneWrapper tzWrapper = (TimeZoneWrapper) iterator.next();
                if (countryCode.toUpperCase(Locale.ENGLISH).equals(
                        tzWrapper.getCountryCode().toUpperCase(Locale.ENGLISH))) {
                    result.add(tzWrapper);
                }
            }
        }
        return result;
    }

    /**
     * <b>NOTE :</b> The collection of timezone may not be accurate or correct.
     * 
     * @param countryCode
     * @return The collection of <b>possible</b> TimeZones. If the specified country code is null or blank, then an
     *         empty list is returned.
     */
    public static Collection<TimeZone> getTimezonesFromCountryCode(final String countryCode) {
        final Collection<TimeZone> result = new LinkedHashSet<TimeZone>();
        if (countryCode != null && !countryCode.trim().isEmpty()) {
            for (Iterator<TimeZoneWrapper> iterator = timezoneWrappers.iterator(); iterator.hasNext();) {
                TimeZoneWrapper tzWrapper = (TimeZoneWrapper) iterator.next();
                if (countryCode.toUpperCase(Locale.ENGLISH).equals(
                        tzWrapper.getCountryCode().toUpperCase(Locale.ENGLISH))) {
                    String id = tzWrapper.getTimezoneID();
                    result.add(TimeZone.getTimeZone(id));
                }
            }
        }
        return result;
    }

}
