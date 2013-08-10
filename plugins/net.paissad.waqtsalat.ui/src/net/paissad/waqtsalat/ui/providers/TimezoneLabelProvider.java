package net.paissad.waqtsalat.ui.providers;

import java.util.TimeZone;

import org.eclipse.jface.viewers.LabelProvider;

public class TimezoneLabelProvider extends LabelProvider {

    private static TimezoneLabelProvider instance;

    public static TimezoneLabelProvider getInstance() {
        synchronized (TimezoneLabelProvider.class) {
            if (instance == null) {
                instance = new TimezoneLabelProvider();
            }
            return instance;
        }
    }

    @Override
    public String getText(Object element) {
        if (element instanceof TimeZone) {
            return ((TimeZone) element).getID();
        }
        return super.getText(element);
    }

}
