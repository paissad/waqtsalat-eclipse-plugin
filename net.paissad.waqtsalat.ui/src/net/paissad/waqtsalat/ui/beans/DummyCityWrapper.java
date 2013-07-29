package net.paissad.waqtsalat.ui.beans;

import java.io.Serializable;

import net.paissad.waqtsalat.locationsprovider.LocationsProviderFactory;
import net.paissad.waqtsalat.locationsprovider.api.City;
import net.paissad.waqtsalat.locationsprovider.api.Coordinates;
import net.paissad.waqtsalat.locationsprovider.api.Country;

import org.eclipse.emf.ecore.EObject;

public class DummyCityWrapper implements Serializable {

    private static final long serialVersionUID = 1L;

    private String            countryCode;
    private String            countryName;
    private String            cityName;
    private String            region;
    private String            postalCode;
    private double            latitude;
    private double            longitude;

    public DummyCityWrapper(City city) {
        Country country = city.getCountry();
        if (country != null) {
            this.countryCode = country.getCode();
            this.countryName = country.getName();
        }
        this.cityName = city.getName();
        this.region = city.getRegion();
        this.postalCode = city.getPostalCode();
        Coordinates coordinates = city.getCoordinates();
        if (coordinates != null) {
            this.latitude = coordinates.getLatitude();
            this.longitude = coordinates.getLongitude();
        }
    }

    /**
     * @return The real city {@link EObject} from this dummy one.
     */
    public City unwrap() {
        City city = LocationsProviderFactory.eINSTANCE.createCity();
        Country country = LocationsProviderFactory.eINSTANCE.createCountry();
        Coordinates coordinates = LocationsProviderFactory.eINSTANCE.createCoordinates();

        country.setCode(countryCode);
        country.setName(countryName);
        country.getCities().add(city);

        city.setName(cityName);
        city.setRegion(region);
        city.setPostalCode(postalCode);
        city.setCoordinates(coordinates);

        coordinates.setLatitude(latitude);
        coordinates.setLongitude(longitude);

        return city;
    }
}
