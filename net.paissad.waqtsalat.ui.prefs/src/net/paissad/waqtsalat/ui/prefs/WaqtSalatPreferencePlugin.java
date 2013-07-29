package net.paissad.waqtsalat.ui.prefs;

import static net.paissad.waqtsalat.ui.prefs.WaqtSalatPreferenceConstants.ICONS;
import net.paissad.eclipse.logger.ILogger;
import net.paissad.eclipse.logger.LoggerPlugin;
import net.paissad.waqtsalat.ui.prefs.WaqtSalatPreferenceConstants.IconsKeys;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class WaqtSalatPreferencePlugin extends AbstractUIPlugin {

    // The plug-in ID
    public static final String               PLUGIN_ID                = "net.paissad.waqtsalat.ui.prefs"; //$NON-NLS-1$

    private boolean                          imageRegistryInitialised = false;

    private WaqtSalatPreferenceStore         preferenceStore;

    // The shared instance
    private static WaqtSalatPreferencePlugin plugin;

    public WaqtSalatPreferencePlugin() {
    }

    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        this.initializeImageRegistry(getImageRegistry());
    }

    public void stop(BundleContext context) throws Exception {
        if (imageRegistryInitialised) {
            getImageRegistry().dispose();
            imageRegistryInitialised = false;
        }
        plugin = null;
        super.stop(context);
    }

    protected void initializeImageRegistry(ImageRegistry reg) {
        reg.put(IconsKeys.NOTIFICATION_1, getImageDescriptor(ICONS.get(IconsKeys.NOTIFICATION_1)));
        imageRegistryInitialised = true;
    }

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static WaqtSalatPreferencePlugin getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in relative path
     * 
     * @param path the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

    @Override
    public WaqtSalatPreferenceStore getPreferenceStore() {
        if (preferenceStore == null) {
            preferenceStore = new WaqtSalatPreferenceStore(InstanceScope.INSTANCE, getBundle().getSymbolicName());
        }
        return preferenceStore;
    }

    public ILogger getLogger() {
        return LoggerPlugin.getDefault().getLogger(getBundle(), PLUGIN_ID, this);
    }
}
