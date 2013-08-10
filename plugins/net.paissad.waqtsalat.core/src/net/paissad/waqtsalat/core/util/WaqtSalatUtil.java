package net.paissad.waqtsalat.core.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.TimeZone;
import java.util.TreeSet;

public class WaqtSalatUtil {

    private WaqtSalatUtil() {
    }

    public static Collection<TimeZone> getAvailableTimezones() {
        final Collection<TimeZone> tZones = new TreeSet<TimeZone>(new Comparator<TimeZone>() {
            @Override
            public int compare(TimeZone o1, TimeZone o2) {
                return o1.getID().compareTo(o2.getID());
            }
        });
        for (String id : TimeZone.getAvailableIDs()) {
            tZones.add(TimeZone.getTimeZone(id));
        }
        return tZones;
    }

}
