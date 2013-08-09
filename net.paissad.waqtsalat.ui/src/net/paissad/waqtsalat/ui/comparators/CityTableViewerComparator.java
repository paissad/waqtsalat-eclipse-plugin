package net.paissad.waqtsalat.ui.comparators;

import net.paissad.waqtsalat.locationsprovider.api.City;
import net.paissad.waqtsalat.locationsprovider.api.Country;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

public class CityTableViewerComparator extends ViewerComparator {

    private static final int DESCENDING = 1;
    private int              propertyIndex;
    private int              direction  = DESCENDING;

    public CityTableViewerComparator() {
        super();
        this.propertyIndex = 0;
        direction = DESCENDING;
    }

    public int getDirection() {
        return direction == 1 ? SWT.DOWN : SWT.UP;
    }

    public void setColumn(int column) {
        if (column == this.propertyIndex) {
            // Same column as last sort; toggle the direction
            direction = 1 - direction;
        } else {
            // New column; do an ascending sort
            this.propertyIndex = column;
            direction = DESCENDING;
        }
    }

    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {

        if (e1 instanceof City && e1 instanceof City) {

            City city1 = (City) e1;
            City city2 = (City) e2;

            int result;

            switch (propertyIndex) {
                case 0:
                    Country country1 = city1.getCountry();
                    Country country2 = city2.getCountry();
                    result = country1.getName().compareTo(country2.getName());
                    break;
                case 1:
                    result = city1.getName().compareTo(city2.getName());
                    break;
                case 2:
                    result = city1.getRegion().compareTo(city2.getRegion());
                case 3:
                    result = city1.getPostalCode().compareTo(city2.getPostalCode());
                default:
                    result = 0;
            }

            // If descending order, flip the direction
            if (direction == DESCENDING) {
                result = -result;
            }

            return result;
        }
        return super.compare(viewer, e1, e2);
    }

}
