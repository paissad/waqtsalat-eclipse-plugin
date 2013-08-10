package net.paissad.waqtsalat.locationsprovider.api;

/**
 * The interface to implement in order to provide geolocation support.
 * 
 * @author Papa Issa DIAKHATE (paissad) <paissad@gmail.com>
 * 
 */
public interface IGeolocationProvider {

    /**
     * @return <code>true</code> if the provider is ready to proceed geolocation operations, <code>false</code>
     *         otherwise.
     */
    boolean ready();

    /**
     * @param ipAddress - The IP address for which we want to retrieve the city.
     * @return The city from the IP address or <code>null</code> if unable to return it for the specified IP.
     */
    City getCity(final String ipAddress);

}
