package net.paissad.waqtsalat.locationsprovider.api;

/**
 * The interface to implement in order to provide geolocation support.
 * 
 * @author Papa Issa DIAKHATE (paissad) <paissad@gmail.com>
 * 
 */
public interface IGeolocationProvider {

    /**
     * This method will be called first and only once before trying to call the methods and other utilities which return
     * the coordinates from the IP address.
     */
    void init();

    /**
     * @param ipAddress - The IP address for which we want to retrieve the city.
     * @return The city from the IP address or <code>null</code> if unable to return it for the specified IP.
     */
    City getCity(final String ipAddress);

}
