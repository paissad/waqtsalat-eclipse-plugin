package net.paissad.waqtsalat.locationsprovider.api;

import java.util.Collection;

/**
 * This is the base class to implement in order to furnish an implementation/provider of locations.
 * 
 * @author Papa Issa DIAKHATE (paissad) <paissad@gmail.com>
 */
public interface ILocationsProvider {

    /**
     * @return All the available countries. that this provider delivers.
     */
    Collection<Country> getCountries();

    /**
     * @return All the cities this provider can return. It should logically be the sum of all cities of each country.
     */
    Collection<City> getCities();

}
