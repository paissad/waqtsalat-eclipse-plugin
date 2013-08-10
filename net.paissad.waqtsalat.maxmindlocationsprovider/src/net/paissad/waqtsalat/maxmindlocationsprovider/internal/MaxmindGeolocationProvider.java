package net.paissad.waqtsalat.maxmindlocationsprovider.internal;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.waqtsalat.locationsprovider.LocationsProviderFactory;
import net.paissad.waqtsalat.locationsprovider.api.AbstractGeoLocationProvider;
import net.paissad.waqtsalat.locationsprovider.api.City;
import net.paissad.waqtsalat.locationsprovider.api.Coordinates;
import net.paissad.waqtsalat.locationsprovider.api.Country;
import net.paissad.waqtsalat.maxmindlocationsprovider.MaxmindLocationsProviderPlugin;
import net.paissad.waqtsalat.maxmindlocationsprovider.internal.util.GeoIPUtil;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

public class MaxmindGeolocationProvider extends AbstractGeoLocationProvider {

    // TODO: add support for IPV6

    private static final ILogger logger                     = MaxmindLocationsProviderPlugin.getDefault().getLogger();

    private static final File    GEOLITE_CITY_DAT_IPV4_FILE = new File(GeoIPUtil.getGeoliteCityDatabaseDir(),
                                                                    "GeoLiteCity.dat");                                               //$NON-NLS-1$

    private static final String  GEOLITE_CITY_DAT_IPV4_LINK = "http://geolite.maxmind.com/download/geoip/database/GeoLiteCity.dat.gz"; //$NON-NLS-1$

    // private static final File GEOLITE_CITY_DAT_IPV6_FILE = new File(GeoIPUtil.getGeoliteCityDatabaseDir(),
    //                                                                    "GeoLiteCity6.dat");                                                //$NON-NLS-1$
    //
    //    private static final String  GEOLITE_CITY_DAT_IPV6_LINK = "http://geolite.maxmind.com/download/geoip/database/GeoLiteCityv6.dat.gz"; //$NON-NLS-1$

    private static boolean       ready                      = false;

    public MaxmindGeolocationProvider() {
        init();
    }

    @Override
    protected void init() {
        logger.info("Initializing Maxmind Geolocation Provider"); //$NON-NLS-1$
        if (!GEOLITE_CITY_DAT_IPV4_FILE.isFile()) {
            downloadAndGunzip(GEOLITE_CITY_DAT_IPV4_LINK, "GeoLiteCity.dat"); //$NON-NLS-1$
        } else {
            ready = true;
        }
    }

    @Override
    public boolean ready() {
        return ready;
    }

    private static void downloadAndGunzip(final String downloadLink, final String outputFilename) {
        Job job = new Job("Download and gunzip GeoLiteCity.dat.gz file") { //$NON-NLS-1$

            @SuppressWarnings("resource")
            @Override
            protected IStatus run(IProgressMonitor monitor) {

                IProgressMonitor downloadProgressMonitor = Job.getJobManager().createProgressGroup();
                IProgressMonitor gunzipProgressMonitor = Job.getJobManager().createProgressGroup();

                InputStream in = null;
                OutputStream out = null;
                File tmpFile = null;

                try {

                    logger.info("Downloading and gunzipping GeoLiteCity.dat.gz file."); //$NON-NLS-1$

                    // DOWNLOAD

                    URL url = new URL(downloadLink);
                    URLConnection urlConnection = url.openConnection();
                    urlConnection.setConnectTimeout((int) TimeUnit.MINUTES.toMillis(1));
                    urlConnection.setAllowUserInteraction(false);
                    urlConnection.connect();
                    int contentLength = urlConnection.getContentLength();
                    downloadProgressMonitor.beginTask("Downloading Maxmind GeoLiteCity.dat.gz file.", contentLength); //$NON-NLS-1$
                    byte[] data = new byte[8192];
                    int bytesRead;
                    int currentLength = 0;

                    in = urlConnection.getInputStream();
                    tmpFile = File.createTempFile(outputFilename + "_", ".temp"); //$NON-NLS-1$ //$NON-NLS-2$
                    tmpFile.delete();
                    out = new BufferedOutputStream(new FileOutputStream(tmpFile), data.length);
                    while ((bytesRead = in.read(data, 0, data.length)) > 0) {
                        if (downloadProgressMonitor.isCanceled()) {
                            return new Status(IStatus.CANCEL, MaxmindLocationsProviderPlugin.PLUGIN_ID,
                                    "Download of " + outputFilename + " canceled."); //$NON-NLS-1$ //$NON-NLS-2$
                        }
                        out.write(data, 0, bytesRead);
                        currentLength += bytesRead;
                        downloadProgressMonitor.worked(bytesRead);
                    }
                    if (currentLength != contentLength) {
                        throw new IOException("Expected to download " + contentLength + " bytes, but got only " //$NON-NLS-1$ //$NON-NLS-2$
                                + currentLength);
                    }
                    out.close();
                    out.flush();
                    logger.info("Downloaded GeoLiteCity.dat.gz file successfully."); //$NON-NLS-1$
                    downloadProgressMonitor.done();

                    // GUNZIP

                    in = new GZIPInputStream(new BufferedInputStream(new FileInputStream(tmpFile), 8192));
                    File datFile = new File(GeoIPUtil.getGeoliteCityDatabaseDir(), outputFilename);
                    out = new BufferedOutputStream(new FileOutputStream(datFile), 8192);

                    gunzipProgressMonitor.beginTask("Unarchiving GeoLiteCity database file", (int) tmpFile.length()); //$NON-NLS-1$

                    while ((bytesRead = in.read(data, 0, data.length)) > 0) {
                        if (gunzipProgressMonitor.isCanceled()) {
                            try {
                                datFile.delete();
                            } catch (Exception e) {
                            }
                            return new Status(IStatus.CANCEL, MaxmindLocationsProviderPlugin.PLUGIN_ID,
                                    "Unarchiving of " + datFile.getName() + " canceled."); //$NON-NLS-1$ //$NON-NLS-2$
                        }
                        out.write(data, 0, bytesRead);
                        gunzipProgressMonitor.worked(bytesRead);
                    }
                    out.flush();
                    out.close();
                    gunzipProgressMonitor.done();
                    logger.info("Unarchived successfully file to " + datFile); //$NON-NLS-1$

                    ready = true;

                    return new Status(Status.INFO, MaxmindLocationsProviderPlugin.PLUGIN_ID,
                            "GeoLiteCity database file retrieved successfully."); //$NON-NLS-1$                  

                } catch (Exception e) {
                    String errMsg = "Error while downloading/gunzipping the GeoLiteCity database file : " //$NON-NLS-1$
                            + e.getMessage();
                    logger.error(errMsg, e);
                    return new Status(Status.ERROR, MaxmindLocationsProviderPlugin.PLUGIN_ID, errMsg, e);

                } finally {
                    downloadProgressMonitor.done();
                    gunzipProgressMonitor.done();
                    closeQuietly(in, out);
                    if (tmpFile != null) {
                        tmpFile.delete();
                    }
                }
            }
        };
        job.schedule();
        // try {
        // job.join();
        // } catch (InterruptedException e) {
        // }
    }

    @Override
    public City getCity(String ipAddress) {

        City city = null;

        if (ready() && ipAddress != null && !ipAddress.trim().isEmpty()) {

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

    private static void closeQuietly(Closeable... closeables) {
        for (Closeable o : closeables) {
            try {
                o.close();
            } catch (Exception e) {
            }
        }
    }

}
