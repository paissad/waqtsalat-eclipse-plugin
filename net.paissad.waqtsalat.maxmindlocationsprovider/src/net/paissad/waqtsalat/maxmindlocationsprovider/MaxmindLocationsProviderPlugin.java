package net.paissad.waqtsalat.maxmindlocationsprovider;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.eclipse.logger.LoggerPlugin;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class MaxmindLocationsProviderPlugin extends Plugin {

    public static final String                    PLUGIN_ID = "net.paissad.waqtsalat.maxmindlocationsprovider";

    private static MaxmindLocationsProviderPlugin plugin;

    private static BundleContext                  context;

    public MaxmindLocationsProviderPlugin() {
        plugin = this;
    }

    public static MaxmindLocationsProviderPlugin getDefault() {
        return plugin;
    }

    static BundleContext getContext() {
        return context;
    }

    public void start(BundleContext bundleContext) throws Exception {
        super.start(bundleContext);
        MaxmindLocationsProviderPlugin.context = bundleContext;
    }

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

}
