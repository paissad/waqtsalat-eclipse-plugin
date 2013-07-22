package net.paissad.waqtsalat.locationsprovider.api;

/**
 * The base interface to implements in order to provide geolocation support.
 * 
 * @author Papa Issa DIAKHATE (paissad) <paissad@gmail.com>
 * 
 */
public interface IGeolocationProvider {

    /**
     * @param ipAddress - The IP address for which we want to retrieve the coordinates.
     * @return The coordinates from the IP address.
     */
    Coordinates getCoordinates(final String ipAddress);

}
