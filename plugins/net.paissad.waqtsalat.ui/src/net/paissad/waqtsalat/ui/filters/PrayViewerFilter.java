package net.paissad.waqtsalat.ui.filters;

import net.paissad.waqtsalat.core.api.Pray;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class PrayViewerFilter extends ViewerFilter {

    private boolean showSunrise;
    private boolean showSunset;

    public PrayViewerFilter() {
        this(true, true);
    }

    public PrayViewerFilter(boolean showSunrise, boolean showSunset) {
        this.showSunrise = showSunrise;
        this.showSunset = showSunset;
    }

    /**
     * @return <code>true</code> if the element is to be shown into the Viewer, <code>false</code> otherwise.
     */
    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        if (element instanceof Pray) {
            Pray pray = (Pray) element;
            switch (pray.getName()) {
                case SUNRISE:
                    return isShowSunrise();
                case SUNSET:
                    return isShowSunset();
                default:
                    break;
            }
        }
        return true;
    }

    public boolean isShowSunrise() {
        return showSunrise;
    }

    public void setShowSunrise(boolean showSunrise) {
        this.showSunrise = showSunrise;
    }

    public boolean isShowSunset() {
        return showSunset;
    }

    public void setShowSunset(boolean showSunset) {
        this.showSunset = showSunset;
    }

}
