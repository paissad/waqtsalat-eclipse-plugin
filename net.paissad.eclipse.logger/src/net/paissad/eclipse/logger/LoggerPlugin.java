package net.paissad.eclipse.logger;

import net.paissad.eclipse.logger.impl.LoggerImpl;

import org.eclipse.core.internal.runtime.InternalPlatform;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

@SuppressWarnings("restriction")
public class LoggerPlugin extends Plugin {

    private static LoggerPlugin  plugin;

    private static BundleContext context;

    public LoggerPlugin() {
        plugin = this;
    }

    public static LoggerPlugin getDefault() {
        return plugin;
    }

    static BundleContext getContext() {
        return context;
    }

    public void start(BundleContext bundleContext) throws Exception {
        LoggerPlugin.context = bundleContext;
    }

    public void stop(BundleContext bundleContext) throws Exception {
        LoggerPlugin.context = null;
    }

    /**
     * <b>NOTE </b>: it is better to use {@link #getLogger(Bundle, String, Plugin)} instead.
     * 
     * @param bundle
     * @param pluginId
     * @return An instance of {@link ILogger}.
     */
    public ILogger getLogger(final Bundle bundle, final String pluginId) {
        ILog backendLog = InternalPlatform.getDefault().getLog(bundle);
        return new LoggerImpl(bundle, pluginId, backendLog);
    }

    /**
     * 
     * @param bundle
     * @param pluginId
     * @param plugin
     * @return An instance of {@link ILogger}.
     */
    public ILogger getLogger(final Bundle bundle, final String pluginId, Plugin plugin) {
        return new LoggerImpl(bundle, pluginId, plugin.getLog());
    }

}
