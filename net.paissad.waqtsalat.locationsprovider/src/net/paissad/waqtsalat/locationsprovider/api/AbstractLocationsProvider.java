package net.paissad.waqtsalat.locationsprovider.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractLocationsProvider implements ILocationsProvider {

    @Override
    public Collection<City> getCities() {
        final List<City> cities = new ArrayList<City>();
        for (final Country country : getCountries()) {
            cities.addAll(country.getCities());
        }
        return cities;
    }

}
