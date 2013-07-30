package net.paissad.waqtsalat.ui.comparators;

import net.paissad.waqtsalat.locationsprovider.api.City;
import net.paissad.waqtsalat.locationsprovider.api.Country;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

public class CityTableViewerComparator extends ViewerComparator {

    public CityTableViewerComparator() {
        super();
    }

    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        // TODO: better null checks should be done before comparing ...
        if (e1 instanceof City && e1 instanceof City) {
            City city1 = (City) e1;
            City city2 = (City) e2;

            int result = city1.getName().compareTo(city2.getName());
            if (result == 0) {
                Country country1 = city1.getCountry();
                Country country2 = city2.getCountry();

            }
        }
        return super.compare(viewer, e1, e2);
    }

}
