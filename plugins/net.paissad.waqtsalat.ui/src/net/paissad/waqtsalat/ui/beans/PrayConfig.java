package net.paissad.waqtsalat.ui.beans;

import java.util.TimeZone;

import net.paissad.waqtsalat.core.api.AdjustingMethod;
import net.paissad.waqtsalat.core.api.CalculationMethod;
import net.paissad.waqtsalat.core.api.JuristicMethod;
import net.paissad.waqtsalat.core.api.TimeFormat;

public class PrayConfig {

    private TimeZone          timeZone;

    private TimeFormat        timeFormat;

    private CalculationMethod calculationMethod;

    private JuristicMethod    asrJuristicMethod;

    private AdjustingMethod   adjustingMethod;

    /** {Fajr, Sunrise, Dhuhr, Asr, Sunset, Maghrib, Isha} */
    private int[]             offsets;

    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public TimeFormat getTimeFormat() {
        return this.timeFormat;
    }

    public void setTimeFormat(TimeFormat timeFormat) {
        this.timeFormat = timeFormat;
    }

    public CalculationMethod getCalculationMethod() {
        return this.calculationMethod;
    }

    public void setCalculationMethod(CalculationMethod calculationMethod) {
        this.calculationMethod = calculationMethod;
    }

    public JuristicMethod getAsrJuristicMethod() {
        return this.asrJuristicMethod;
    }

    public void setAsrJuristicMethod(JuristicMethod asrJuristicMethod) {
        this.asrJuristicMethod = asrJuristicMethod;
    }

    public AdjustingMethod getAdjustingMethod() {
        return this.adjustingMethod;
    }

    public void setAdjustingMethod(AdjustingMethod adjustingMethod) {
        this.adjustingMethod = adjustingMethod;
    }

    public int[] getOffsets() {
        return this.offsets;
    }

    public void setOffsets(int[] offsets) {
        if (offsets.length != 7) throw new IllegalArgumentException("The length of offset table must be 7."); //$NON-NLS-1$
        this.offsets = offsets;
    }

}
