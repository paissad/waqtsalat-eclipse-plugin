package net.paissad.waqtsalat.maxmindlocationsprovider.internal;

import java.io.File;
import java.net.Inet6Address;
import java.net.InetAddress;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.waqtsalat.locationsprovider.LocationsProviderFactory;
import net.paissad.waqtsalat.locationsprovider.api.City;
import net.paissad.waqtsalat.locationsprovider.api.Coordinates;
import net.paissad.waqtsalat.locationsprovider.api.Country;
import net.paissad.waqtsalat.locationsprovider.api.IGeolocationProvider;
import net.paissad.waqtsalat.maxmindlocationsprovider.MaxmindLocationsProviderPlugin;

import org.eclipse.core.runtime.IPath;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

public class MaxmindGeolocationProvider implements IGeolocationProvider {

    private static final ILogger logger                     = MaxmindLocationsProviderPlugin.getDefault().getLogger();

    private static final IPath   DATABASE_ROOT_PATH         = MaxmindLocationsProviderPlugin.getDefault()
                                                                    .getStateLocation().append("/database");                            //$NON-NLS-1$

    private static final File    GEOLITE_CITY_DAT_IPV4_FILE = new File(DATABASE_ROOT_PATH.toFile(), "GeoLiteCity.dat");                 //$NON-NLS-1$

    private static final String  GEOLITE_CITY_DAT_IPV4_LINK = "http://geolite.maxmind.com/download/geoip/database/GeoLiteCity.dat.gz";  //$NON-NLS-1$

    private static final File    GEOLITE_CITY_DAT_IPV6_FILE = new File(DATABASE_ROOT_PATH.toFile(), "GeoLiteCity6.dat");                //$NON-NLS-1$

    private static final String  GEOLITE_CITY_DAT_IPV6_LINK = "http://geolite.maxmind.com/download/geoip/database/GeoLiteCityv6.dat.gz"; //$NON-NLS-1$

    private static boolean       initialized                = false;

    @Override
    public void init() {
        logger.info("Initializing Maxmind Geolocation Provider"); //$NON-NLS-1$
        if (!GEOLITE_CITY_DAT_IPV4_FILE.isFile()) {
            // TODO: download and gunzip the file.
            return;
        }
        initialized = true;
    }

    @Override
    public City getCity(String ipAddress) {

        City city = null;

        if (initialized && ipAddress != null && !ipAddress.trim().isEmpty()) {

            try {

                final InetAddress addr = InetAddress.getByName(ipAddress);
                final boolean isIPV6 = addr instanceof Inet6Address;

                final LookupService lookupService = new LookupService(GEOLITE_CITY_DAT_IPV4_FILE,
                        LookupService.GEOIP_MEMORY_CACHE);

                // Supposed to be GeoLiteCity.dat, otherwise an error will occur.
                final Location location = (isIPV6) ? lookupService.getLocationV6(ipAddress) : lookupService
                        .getLocation(ipAddress);

                Country country = LocationsProviderFactory.eINSTANCE.createCountry();
                Coordinates coordinates = LocationsProviderFactory.eINSTANCE.createCoordinates();
                city = LocationsProviderFactory.eINSTANCE.createCity();

                country.setName(location.countryName);
                country.setCode(location.countryCode);
                country.getCities().add(city);

                coordinates.setLatitude(Double.valueOf(location.latitude));
                coordinates.setLongitude(Double.valueOf(location.longitude));

                city.setName(location.city);
                city.setRegion(location.region);
                city.setPostalCode(location.postalCode);
                city.setCountry(country);
                city.setCoordinates(coordinates);

            } catch (Exception e) {
                logger.error(
                        "Error while retrieving the city from the IP address '" + ipAddress + "' : " + e.getMessage(), //$NON-NLS-1$ //$NON-NLS-2$
                        e);
                city = null;
            }
        }

        return city;
    }

}
