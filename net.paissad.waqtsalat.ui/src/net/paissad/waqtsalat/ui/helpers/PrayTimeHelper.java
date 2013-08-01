// --------------------- Copyright Block ----------------------
/*
 * 
 * PrayTime.java: Prayer Times Calculator (ver 1.0) Copyright (C) 2007-2010
 * PrayTimes.org
 * 
 * Java Code By: Hussain Ali Khan Original JS Code By: Hamid Zarrabi-Zadeh
 * Modified by Papa Issa DIAKHATE (paissad) for the project WaqtSalat.
 * 
 * License: GNU General private License, ver 3
 * 
 * TERMS OF USE: Permission is granted to use this code, with or without
 * modification, in any website or application provided that credit is given to
 * the original work with a link back to PrayTimes.org.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY.
 * 
 * PLEASE DO NOT REMOVE THIS COPYRIGHT BLOCK.
 */

package net.paissad.waqtsalat.ui.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import net.paissad.waqtsalat.core.WaqtSalatFactory;
import net.paissad.waqtsalat.core.api.AdjustingMethod;
import net.paissad.waqtsalat.core.api.CalculationMethod;
import net.paissad.waqtsalat.core.api.JuristicMethod;
import net.paissad.waqtsalat.core.api.Pray;
import net.paissad.waqtsalat.core.api.PrayName;
import net.paissad.waqtsalat.core.api.TimeFormat;
import net.paissad.waqtsalat.locationsprovider.LocationsProviderFactory;
import net.paissad.waqtsalat.locationsprovider.api.Coordinates;
import net.paissad.waqtsalat.ui.beans.PrayConfig;

public class PrayTimeHelper {

    private CalculationMethod                calcMethod;
    private JuristicMethod                   asrJuristic;
    private AdjustingMethod                  adjustHighLats;
    private TimeFormat                       timeFormat;

    private int                              dhuhrMinutes;          // minutes after mid-day for Dhuhr

    private double                           latitude;
    private double                           longitude;
    private double                           timeZone;
    private double                           jDate;                 // julian date

    // The string used for invalid times
    private static final String              INVALID_TIME = "-----";

    // ------------------- Calc Method Parameters --------------------
    private Map<CalculationMethod, double[]> methodParams;

    //@formatter:off
    /*
     * this.methodParams[methodNum] = new Array(fa, ms, mv, is, iv);
     * 
     * fa : fajr angle
     * ms : maghrib selector (0 = angle; 1 = minutes after sunset) 
     * mv : maghrib parameter value (in angle or minutes) 
     * is : isha selector (0 = angle; 1 = minutes after maghrib)
     * iv : isha parameter value (in angle or minutes)
     */
    //@formatter:on
    private int[]                            offsets;

    private PrayTimeHelper() {

        this.setCalcMethod(CalculationMethod.JAFARI);
        this.setAsrJuristic(JuristicMethod.HANAFI);
        this.setTimeFormat(TimeFormat.TIME_24);
        this.setAdjustHighLats(AdjustingMethod.MIDNIGHT);
        this.setDhuhrMinutes(0);

        // Tuning offsets {fajr, sunrise, dhuhr, asr, sunset, maghrib, isha}
        this.offsets = new int[] { 0, 0, 0, 0, 0, 0, 0 };

        this.methodParams = new HashMap<CalculationMethod, double[]>();

        // Jafari
        double[] Jvalues = { 16, 0, 4, 0, 14 };
        this.methodParams.put(CalculationMethod.JAFARI, Jvalues);

        // Karachi
        double[] Kvalues = { 18, 1, 0, 0, 18 };
        this.methodParams.put(CalculationMethod.KARACHI, Kvalues);

        // ISNA
        double[] Ivalues = { 15, 1, 0, 0, 15 };
        methodParams.put(CalculationMethod.ISNA, Ivalues);

        // MWL
        double[] MWvalues = { 18, 1, 0, 0, 17 };
        this.methodParams.put(CalculationMethod.MWL, MWvalues);

        // Makkah
        double[] MKvalues = { 18.5, 1, 0, 1, 90 };
        this.methodParams.put(CalculationMethod.MAKKAH, MKvalues);

        // Egypt
        double[] Evalues = { 19.5, 1, 0, 0, 17.5 };
        this.methodParams.put(CalculationMethod.EGYPT, Evalues);

        // Tehran
        double[] Tvalues = { 17.7, 0, 4.5, 0, 14 };
        this.methodParams.put(CalculationMethod.TEHRAN, Tvalues);

        // Custom
        double[] Cvalues = { 18, 1, 0, 0, 17 };
        this.methodParams.put(CalculationMethod.CUSTOM, Cvalues);
    }

    // ---------------------- Trigonometric Functions -----------------------
    // range reduce angle in degrees.
    private double fixangle(double a) {
        a = a - (360 * (Math.floor(a / 360.0)));
        return (a < 0) ? (a + 360) : a;
    }

    // range reduce hours to 0..23
    private double fixhour(double a) {
        a = a - 24.0 * Math.floor(a / 24.0);
        return (a < 0) ? (a + 24) : a;
    }

    // radian to degree
    private double radiansToDegrees(double alpha) {
        return ((alpha * 180.0) / Math.PI);
    }

    // deree to radian
    private double DegreesToRadians(double alpha) {
        return ((alpha * Math.PI) / 180.0);
    }

    // degree sin
    private double dsin(double d) {
        return (Math.sin(DegreesToRadians(d)));
    }

    // degree cos
    private double dcos(double d) {
        return (Math.cos(DegreesToRadians(d)));
    }

    // degree tan
    private double dtan(double d) {
        return (Math.tan(DegreesToRadians(d)));
    }

    // degree arcsin
    private double darcsin(double x) {
        return radiansToDegrees(Math.asin(x));
    }

    // degree arccos
    private double darccos(double x) {
        return radiansToDegrees(Math.acos(x));
    }

    // degree arctan2
    private double darctan2(double y, double x) {
        return radiansToDegrees(Math.atan2(y, x));
    }

    // degree arccot
    private double darccot(double x) {
        return radiansToDegrees(Math.atan2(1.0, x));
    }

    // ---------------------- Time-Zone Functions -----------------------
    // compute local time-zone for a specific date
    private double getTimeZone1() {
        TimeZone timez = TimeZone.getDefault();
        return (timez.getRawOffset() / 1000.0) / 3600;
    }

    // compute base time-zone of the system
    private double getBaseTimeZone() {
        TimeZone timez = TimeZone.getDefault();
        return (timez.getRawOffset() / 1000.0) / 3600;
    }

    // detect daylight saving in a given date
    private double detectDaylightSaving() {
        TimeZone timez = TimeZone.getDefault();
        return timez.getDSTSavings();
    }

    // ---------------------- Julian Date Functions -----------------------
    // calculate julian date from a calendar date
    private double julianDate(int year, int month, int day) {

        if (month <= 2) {
            year -= 1;
            month += 12;
        }
        double A = Math.floor(year / 100.0);

        double B = 2 - A + Math.floor(A / 4.0);

        double JD = Math.floor(365.25 * (year + 4716)) + Math.floor(30.6001 * (month + 1)) + day + B - 1524.5;

        return JD;
    }

    // convert a calendar date to julian date (second method)
    private double calcJD(int year, int month, int day) {

        Calendar cal = Calendar.getInstance();
        cal.set(1900 + year, month - 1, day);
        Date date = cal.getTime();

        double ms = date.getTime();
        double days = Math.floor(ms / (1000.0 * 60.0 * 60.0 * 24.0));
        double j1970 = 2440588.0;
        return j1970 + days - 0.5;
    }

    // ---------------------- Calculation Functions -----------------------
    // References:
    // http://www.ummah.net/astronomy/saltime
    // http://aa.usno.navy.mil/faq/docs/SunApprox.html
    // compute declination angle of sun and equation of time
    private double[] sunPosition(double jd) {

        double D = jd - 2451545;
        double g = fixangle(357.529 + 0.98560028 * D);
        double q = fixangle(280.459 + 0.98564736 * D);
        double L = fixangle(q + (1.915 * dsin(g)) + (0.020 * dsin(2 * g)));

        // double R = 1.00014 - 0.01671 * [self dcos:g] - 0.00014 * [self
        // dcos:(2*g)];
        double e = 23.439 - (0.00000036 * D);
        double d = darcsin(dsin(e) * dsin(L));
        double RA = (darctan2((dcos(e) * dsin(L)), (dcos(L)))) / 15.0;
        RA = fixhour(RA);
        double EqT = q / 15.0 - RA;
        double[] sPosition = new double[2];
        sPosition[0] = d;
        sPosition[1] = EqT;

        return sPosition;
    }

    // compute equation of time
    private double equationOfTime(double jd) {
        return sunPosition(jd)[1];
    }

    // compute declination angle of sun
    private double sunDeclination(double jd) {
        return sunPosition(jd)[0];
    }

    // compute mid-day (Dhuhr, Zawal) time
    private double computeMidDay(double t) {
        double T = equationOfTime(this.getJDate() + t);
        double Z = fixhour(12 - T);
        return Z;
    }

    // compute time for a given angle G
    private double computeTime(double G, double t) {

        double D = sunDeclination(this.getJDate() + t);
        double Z = computeMidDay(t);
        double Beg = -dsin(G) - dsin(D) * dsin(this.getLatitude());
        double Mid = dcos(D) * dcos(this.getLatitude());
        double V = darccos(Beg / Mid) / 15.0;

        return Z + (G > 90 ? -V : V);
    }

    // compute the time of Asr
    // Shafii: step=1, Hanafi: step=2
    private double computeAsr(double step, double t) {
        double D = sunDeclination(this.getJDate() + t);
        double G = -darccot(step + dtan(Math.abs(this.getLatitude() - D)));
        return computeTime(G, t);
    }

    // ---------------------- Misc Functions -----------------------
    // compute the difference between two times
    private double timeDiff(double time1, double time2) {
        return fixhour(time2 - time1);
    }

    // -------------------- Interface Functions --------------------
    /**
     * Return prayer times for a given date
     * 
     * @param year
     * @param month
     * @param day
     * @param latitude
     * @param longitude
     * @param tZone
     * @return A <code>List</code> containing the pray times.
     */
    private List<String> getDatePrayerTimes(int year, int month, int day, double latitude, double longitude,
            double tZone) {

        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setTimeZone(tZone);
        this.setJDate(julianDate(year, month, day));
        double lonDiff = longitude / (15.0 * 24.0);
        this.setJDate(this.getJDate() - lonDiff);
        return computeDayTimes();
    }

    /**
     * Return prayer times for a given date
     * 
     * @param date
     * @param latitude
     * @param longitude
     * @param tZone
     * @return A <code>List</code> containing the pray times.
     */
    private List<String> getPrayerTimes(Calendar date, double latitude, double longitude, double tZone) {

        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DATE);

        return getDatePrayerTimes(year, month + 1, day, latitude, longitude, tZone);
    }

    // set custom values for calculation parameters
    private void setCustomParams(double[] params) {

        for (int i = 0; i < 5; i++) {
            if (params[i] == -1) {
                params[i] = methodParams.get(this.getCalcMethod())[i];
                methodParams.put(CalculationMethod.CUSTOM, params);
            } else {
                methodParams.get(CalculationMethod.CUSTOM)[i] = params[i];
            }
        }
        this.setCalcMethod(CalculationMethod.CUSTOM);
    }

    // set the angle for calculating Fajr
    private void setFajrAngle(double angle) {
        double[] params = { angle, -1, -1, -1, -1 };
        setCustomParams(params);
    }

    // set the angle for calculating Maghrib
    private void setMaghribAngle(double angle) {
        double[] params = { -1, 0, angle, -1, -1 };
        setCustomParams(params);
    }

    // set the angle for calculating Isha
    private void setIshaAngle(double angle) {
        double[] params = { -1, -1, -1, 0, angle };
        setCustomParams(params);
    }

    // set the minutes after Sunset for calculating Maghrib
    private void setMaghribMinutes(double minutes) {
        double[] params = { -1, 1, minutes, -1, -1 };
        setCustomParams(params);
    }

    // set the minutes after Maghrib for calculating Isha
    private void setIshaMinutes(double minutes) {
        double[] params = { -1, -1, -1, 1, minutes };
        setCustomParams(params);
    }

    // convert double hours to 24h format
    private String floatToTime24(double time) {

        String result;

        if (Double.isNaN(time)) {
            return INVALID_TIME;
        }

        time = fixhour(time + 0.5 / 60.0); // add 0.5 minutes to round
        int hours = (int) Math.floor(time);
        double minutes = Math.floor((time - hours) * 60.0);

        if ((hours >= 0 && hours <= 9) && (minutes >= 0 && minutes <= 9)) {
            result = "0" + hours + ":0" + Math.round(minutes);
        } else if ((hours >= 0 && hours <= 9)) {
            result = "0" + hours + ":" + Math.round(minutes);
        } else if ((minutes >= 0 && minutes <= 9)) {
            result = hours + ":0" + Math.round(minutes);
        } else {
            result = hours + ":" + Math.round(minutes);
        }
        return result;
    }

    // convert double hours to 12h format
    private String floatToTime12(double time, boolean noSuffix) {

        if (Double.isNaN(time)) {
            return INVALID_TIME;
        }

        time = fixhour(time + 0.5 / 60); // add 0.5 minutes to round
        int hours = (int) Math.floor(time);
        double minutes = Math.floor((time - hours) * 60);
        String suffix, result;
        if (hours >= 12) {
            suffix = "pm";
        } else {
            suffix = "am";
        }
        hours = ((((hours + 12) - 1) % (12)) + 1);
        /*
         * hours = (hours + 12) - 1; int hrs = (int) hours % 12; hrs += 1;
         */
        if (noSuffix == false) {
            if ((hours >= 0 && hours <= 9) && (minutes >= 0 && minutes <= 9)) {
                result = "0" + hours + ":0" + Math.round(minutes) + " " + suffix;
            } else if ((hours >= 0 && hours <= 9)) {
                result = "0" + hours + ":" + Math.round(minutes) + " " + suffix;
            } else if ((minutes >= 0 && minutes <= 9)) {
                result = hours + ":0" + Math.round(minutes) + " " + suffix;
            } else {
                result = hours + ":" + Math.round(minutes) + " " + suffix;
            }

        } else {
            if ((hours >= 0 && hours <= 9) && (minutes >= 0 && minutes <= 9)) {
                result = "0" + hours + ":0" + Math.round(minutes);
            } else if ((hours >= 0 && hours <= 9)) {
                result = "0" + hours + ":" + Math.round(minutes);
            } else if ((minutes >= 0 && minutes <= 9)) {
                result = hours + ":0" + Math.round(minutes);
            } else {
                result = hours + ":" + Math.round(minutes);
            }
        }
        return result;
    }

    // convert double hours to 12h format with no suffix
    private String floatToTime12NS(double time) {
        return floatToTime12(time, true);
    }

    // ---------------------- Compute Prayer Times -----------------------
    // compute prayer times at given julian date
    private double[] computeTimes(double[] times) {

        double[] t = dayPortion(times);

        double Fajr = this.computeTime(180 - methodParams.get(this.getCalcMethod())[0], t[0]);

        double Sunrise = this.computeTime(180 - 0.833, t[1]);

        double Dhuhr = this.computeMidDay(t[2]);
        double Asr = this.computeAsr(1 + this.getAsrJuristic().getValue(), t[3]);
        double Sunset = this.computeTime(0.833, t[4]);

        double Maghrib = this.computeTime(methodParams.get(this.getCalcMethod())[2], t[5]);
        double Isha = this.computeTime(methodParams.get(this.getCalcMethod())[4], t[6]);

        double[] CTimes = { Fajr, Sunrise, Dhuhr, Asr, Sunset, Maghrib, Isha };

        return CTimes;
    }

    // compute prayer times at given julian date
    private List<String> computeDayTimes() {
        double[] times = { 5, 6, 12, 13, 18, 18, 18 }; // default times

        times = computeTimes(times);
        times = adjustTimes(times);
        times = tuneTimes(times);

        return adjustTimesFormat(times);
    }

    // adjust times in a prayer time array
    private double[] adjustTimes(double[] times) {
        for (int i = 0; i < times.length; i++) {
            times[i] += this.getTimeZone() - this.getLongitude() / 15;
        }

        // Dhuhr
        times[2] += this.getDhuhrMinutes() / 60;

        // Maghrib
        if (methodParams.get(this.getCalcMethod())[1] == 1) {
            times[5] = times[4] + methodParams.get(this.getCalcMethod())[2] / 60;
        }

        // Isha
        if (methodParams.get(this.getCalcMethod())[3] == 1) {
            times[6] = times[5] + methodParams.get(this.getCalcMethod())[4] / 60;
        }

        if (this.getAdjustHighLats() != AdjustingMethod.NONE) {
            times = adjustHighLatTimes(times);
        }

        return times;
    }

    // convert times array to given time format
    private List<String> adjustTimesFormat(double[] times) {

        List<String> result = new ArrayList<String>();

        if (this.getTimeFormat() == TimeFormat.FLOATING) {
            for (double time : times) {
                result.add(String.valueOf(time));
            }
            return result;
        }

        for (int i = 0; i < 7; i++) {
            if (this.getTimeFormat() == TimeFormat.TIME_12) {
                result.add(floatToTime12(times[i], false));
            } else if (this.getTimeFormat() == TimeFormat.TIME_12_NS) {
                result.add(floatToTime12(times[i], true));
            } else {
                result.add(floatToTime24(times[i]));
            }
        }
        return result;
    }

    // adjust Fajr, Isha and Maghrib for locations in higher latitudes
    private double[] adjustHighLatTimes(double[] times) {
        double nightTime = timeDiff(times[4], times[1]); // sunset to sunrise

        // Adjust Fajr
        double FajrDiff = nightPortion(methodParams.get(this.getCalcMethod())[0]) * nightTime;

        if (Double.isNaN(times[0]) || timeDiff(times[0], times[1]) > FajrDiff) {
            times[0] = times[1] - FajrDiff;
        }

        // Adjust Isha
        double IshaAngle = (methodParams.get(this.getCalcMethod())[3] == 0) ? methodParams.get(this.getCalcMethod())[4]
                : 18;

        double IshaDiff = this.nightPortion(IshaAngle) * nightTime;

        if (Double.isNaN(times[6]) || this.timeDiff(times[4], times[6]) > IshaDiff) {
            times[6] = times[4] + IshaDiff;
        }

        // Adjust Maghrib
        double MaghribAngle = (methodParams.get(this.getCalcMethod())[1] == 0) ? methodParams.get(this.getCalcMethod())[2]
                : 4;

        double MaghribDiff = nightPortion(MaghribAngle) * nightTime;
        if (Double.isNaN(times[5]) || this.timeDiff(times[4], times[5]) > MaghribDiff) {
            times[5] = times[4] + MaghribDiff;
        }

        return times;
    }

    // the night portion used for adjusting times in higher latitudes
    private double nightPortion(double angle) {
        double calc = 0;

        if (adjustHighLats == AdjustingMethod.ANGLE_BASED)
            calc = (angle) / 60.0;
        else if (adjustHighLats == AdjustingMethod.MIDNIGHT)
            calc = 0.5;
        else if (adjustHighLats == AdjustingMethod.ONE_SEVENTH) calc = 0.14286;

        return calc;
    }

    // convert hours to day portions
    private double[] dayPortion(double[] times) {
        for (int i = 0; i < 7; i++) {
            times[i] /= 24;
        }
        return times;
    }

    /**
     * Set time offsets.<br>
     * The size of the array should be 7 in the respective order of Fajr, Sunrise, Dhuhr, Asr, Sunset, Maghrib, Isha.
     * 
     * @param offsetTimes - Tune timings for adjustments (in minutes).
     */
    private void tune(final int[] offsetTimes) {

        /*
         * offsetTimes length should be 7 and respectively in the following order: Fajr, Sunrise, Dhuhr, Asr, Sunset,
         * Maghrib, Isha.
         */
        for (int i = 0; i < offsetTimes.length; i++) {
            this.offsets[i] = offsetTimes[i];
        }
    }

    private double[] tuneTimes(final double[] times) {
        double[] tunedTimes = Arrays.copyOf(times, times.length);
        for (int i = 0; i < tunedTimes.length; i++) {
            tunedTimes[i] += this.offsets[i] / 60d;
        }
        return tunedTimes;
    }

    private CalculationMethod getCalcMethod() {
        return calcMethod;
    }

    private void setCalcMethod(CalculationMethod calcMethod) {
        this.calcMethod = calcMethod;
    }

    private JuristicMethod getAsrJuristic() {
        return asrJuristic;
    }

    private void setAsrJuristic(JuristicMethod asrJuristic) {
        this.asrJuristic = asrJuristic;
    }

    private int getDhuhrMinutes() {
        return dhuhrMinutes;
    }

    private void setDhuhrMinutes(int dhuhrMinutes) {
        this.dhuhrMinutes = dhuhrMinutes;
    }

    private AdjustingMethod getAdjustHighLats() {
        return adjustHighLats;
    }

    private void setAdjustHighLats(AdjustingMethod adjustHighLats) {
        this.adjustHighLats = adjustHighLats;
    }

    private TimeFormat getTimeFormat() {
        return timeFormat;
    }

    private void setTimeFormat(TimeFormat timeFormat) {
        this.timeFormat = timeFormat;
    }

    private double getLatitude() {
        return latitude;
    }

    private void setLatitude(double lat) {
        this.latitude = lat;
    }

    private double getLongitude() {
        return longitude;
    }

    private void setLongitude(double lng) {
        this.longitude = lng;
    }

    private double getTimeZone() {
        return timeZone;
    }

    private void setTimeZone(double timeZone) {
        this.timeZone = timeZone;
    }

    private double getJDate() {
        return jDate;
    }

    private void setJDate(double jDate) {
        this.jDate = jDate;
    }

    public static Collection<Pray> computePrayTimes(final Calendar date, final Coordinates coords,
            final PrayConfig config) {

        if (coords == null) throw new IllegalArgumentException("The coordinates cannot be null.");
        if (config == null) throw new IllegalArgumentException("The config for computing pray times cannot be null.");

        // The number of hours between the the current time and UTC time.
        TimeZone tz = config.getTimeZone();
        double timeZoneOffset = tz.getOffset(date.getTimeInMillis()) / TimeUnit.HOURS.toMillis(1);

        final PrayTimeHelper helper = new PrayTimeHelper();
        helper.setTimeFormat(config.getTimeFormat());
        helper.setCalcMethod(config.getCalculationMethod());
        helper.setAsrJuristic(config.getAsrJuristicMethod());
        helper.setAdjustHighLats(config.getAdjustingMethod());
        helper.tune(config.getOffsets());

        final EList<Pray> result = new BasicEList<Pray>();
        final List<String> prayerTimes = helper.getPrayerTimes(date, coords.getLatitude(), coords.getLongitude(),
                timeZoneOffset);

        for (int i = 0; i < prayerTimes.size(); i++) {
            Pray pray = WaqtSalatFactory.eINSTANCE.createPray();
            PrayName name = PrayName.get(i);
            pray.setName(name);
            Calendar cal = (Calendar) date.clone();
            String[] time = prayerTimes.get(i).split("\\s*:\\s*"); //$NON-NLS-1$
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
            cal.set(Calendar.MINUTE, Integer.parseInt(time[1]));
            pray.setTime(cal);

            result.add(pray);
        }

        return result;
    }

    /*
     * For testing purpose only !
     */
    public static void main(String[] args) {

        PrayConfig prayConfig = new PrayConfig();
        prayConfig.setTimeZone(TimeZone.getDefault());
        prayConfig.setTimeFormat(TimeFormat.TIME_24); // we are going to use TIME_24 in order to simplify the job.
        prayConfig.setCalculationMethod(CalculationMethod.JAFARI);
        prayConfig.setAsrJuristicMethod(JuristicMethod.SHAFII);
        prayConfig.setAdjustingMethod(AdjustingMethod.ANGLE_BASED);
        prayConfig.setOffsets(new int[] { 0, 0, 0, 0, 0, 0, 0 });

        // Coordinates for Montpellier/FRANCE ...
        Coordinates coordinates = LocationsProviderFactory.eINSTANCE.createCoordinates();
        coordinates.setLatitude(43.61090087890625);
        coordinates.setLongitude(3.87719988822937);

        Collection<Pray> prayTimes = computePrayTimes(Calendar.getInstance(), coordinates, prayConfig);
        for (Pray pray : prayTimes) {
            String hour = String.format("%02d", pray.getTime().get(Calendar.HOUR_OF_DAY));
            String minutes = String.format("%02d", pray.getTime().get(Calendar.MINUTE));
            System.out.format("%-7s : %s\n", pray.getName(), hour + ":" + minutes);
        }
    }
}
