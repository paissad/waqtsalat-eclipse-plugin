package net.paissad.waqtsalat.maxmindlocationsprovider;

import java.io.File;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.eclipse.logger.LoggerPlugin;
import net.paissad.waqtsalat.maxmindlocationsprovider.internal.util.GeoIPUtil;

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.SafeRunner;
import org.osgi.framework.BundleContext;

public class MaxmindLocationsProviderPlugin extends Plugin {

    public static final String                    PLUGIN_ID                = "net.paissad.waqtsalat.maxmindlocationsprovider"; //$NON-NLS-1$

    private static MaxmindLocationsProviderPlugin plugin;

    private static BundleContext                  context;

    private static boolean                        csvFileDownloaded        = false;

    private static boolean                        csvFileDownloadError     = false;

    private static boolean                        h2DatabaseAlreadyCreated = false;

    public MaxmindLocationsProviderPlugin() {
        plugin = this;
    }

    public static MaxmindLocationsProviderPlugin getDefault() {
        return plugin;
    }

    static BundleContext getContext() {
        return context;
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        super.start(bundleContext);
        downloadCSVFileIfNecessary();
        createAndPopulateH2DatabaseIfNecessary();
        MaxmindLocationsProviderPlugin.context = bundleContext;
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        super.stop(bundleContext);
        MaxmindLocationsProviderPlugin.context = null;
    }

    /**
     * @return An instance of logger.
     */
    public ILogger getLogger() {
        return LoggerPlugin.getDefault().getLogger(getBundle(), PLUGIN_ID, this);
    }

    private void downloadCSVFileIfNecessary() {
        SafeRunner.run(new ISafeRunnable() {

            @Override
            public void run() throws Exception {
                final File csvFile = GeoIPUtil.getGeoLiteCityCSVFile();
                if (!csvFile.exists()) {
                    GeoIPUtil.downloadCSVDatabase(true);
                }
                csvFileDownloaded = true;
            }

            @Override
            public void handleException(Throwable throwable) {
                String errMsg = "Error while downloading GeoliteCity CSV database file :" + throwable.getMessage(); //$NON-NLS-1$
                Exception e = (throwable instanceof Exception) ? (Exception) throwable : null;
                MaxmindLocationsProviderPlugin.getDefault().getLogger().error(errMsg, e);
                csvFileDownloadError = true;
            }
        });
    }

    private void createAndPopulateH2DatabaseIfNecessary() {
        SafeRunner.run(new ISafeRunnable() {

            @Override
            public void run() throws Exception {
                while (!csvFileDownloaded && !csvFileDownloadError) {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                    }
                }
                if (!csvFileDownloadError) {
                    GeoIPUtil.createDatabaseAndPopulateCitiesTable();
                    h2DatabaseAlreadyCreated = true;
                }
            }

            @Override
            public void handleException(Throwable exception) {
                Exception e = (exception instanceof Exception) ? (Exception) exception : null;
                getLogger().error("Error while creating/populating the H2 Database of cities : " + e.getMessage(), e); //$NON-NLS-1$
            }
        });
    }

    /**
     * @return <code>true</code> if the H2 Database is already created and has
     *         its tables populated.
     */
    public static boolean isH2DatabaseAlreadyCreated() {
        return h2DatabaseAlreadyCreated;
    }

}
