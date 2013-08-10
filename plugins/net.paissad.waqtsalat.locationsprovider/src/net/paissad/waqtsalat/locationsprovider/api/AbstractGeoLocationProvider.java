package net.paissad.waqtsalat.locationsprovider.api;

public abstract class AbstractGeoLocationProvider implements IGeolocationProvider {

    /**
     * This method should be called first and only once before trying to call the methods and other utilities which
     * return the coordinates from the IP address.
     */
    protected abstract void init();

}
