package net.paissad.waqtsalat.maxmindlocationsprovider.internal;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.waqtsalat.locationsprovider.api.AbstractLocationsProvider;
import net.paissad.waqtsalat.locationsprovider.api.City;
import net.paissad.waqtsalat.locationsprovider.api.Country;
import net.paissad.waqtsalat.maxmindlocationsprovider.MaxmindLocationsProviderPlugin;
import net.paissad.waqtsalat.maxmindlocationsprovider.internal.util.GeoIPUtil;

public class MaxmindLocationsProvider extends AbstractLocationsProvider {

    private static final ILogger logger = MaxmindLocationsProviderPlugin.getDefault().getLogger();

    @Override
    public Collection<Country> getCountries() {
        final Collection<Country> countries = new HashSet<Country>();
        for (final City city : getCities()) {
            countries.add(city.getCountry());
        }
        return countries;
    }

    @Override
    public Collection<City> getCities() {
        try {
            return GeoIPUtil.getAllCities();
        } catch (SQLException e) {
            String errMsg = "Error while retrieving the list of cities : " + e.getMessage(); //$NON-NLS-1$
            logger.error(errMsg, e);
            return null;
        }
    }
}
