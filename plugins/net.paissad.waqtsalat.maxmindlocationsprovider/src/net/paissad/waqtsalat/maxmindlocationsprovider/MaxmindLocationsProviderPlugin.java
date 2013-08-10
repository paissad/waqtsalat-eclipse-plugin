package net.paissad.waqtsalat.maxmindlocationsprovider;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.eclipse.logger.LoggerPlugin;
import net.paissad.waqtsalat.maxmindlocationsprovider.internal.util.GeoIPUtil;

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.SafeRunner;
import org.osgi.framework.BundleContext;

public class MaxmindLocationsProviderPlugin extends Plugin {

    public static final String                    PLUGIN_ID                = "net.paissad.waqtsalat.locationsprovider.maxmindlocationsprovider"; //$NON-NLS-1$

    public static final String                    BUNDLE_SYMBOLIC_NAME     = PLUGIN_ID;

    private static MaxmindLocationsProviderPlugin plugin;

    private static BundleContext                  context;

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

    private void createAndPopulateH2DatabaseIfNecessary() {
        SafeRunner.run(new ISafeRunnable() {

            @Override
            public void run() throws Exception {
                if (!GeoIPUtil.getGeoLiteCityCSVFile().exists()) {
                    if (!GeoIPUtil.unzipGeoLiteCityZipFile()) {
                        return; // H2 database will not be created since the CSV
                                // file is not extracted from the zip file.
                    }
                }
                GeoIPUtil.createDatabaseAndPopulateCitiesTable();
                h2DatabaseAlreadyCreated = true;
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
