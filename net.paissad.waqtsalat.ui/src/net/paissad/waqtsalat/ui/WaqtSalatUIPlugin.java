/**
 */
package net.paissad.waqtsalat.ui;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.eclipse.logger.LoggerPlugin;
import net.paissad.waqtsalat.locationsprovider.LocationsProviderPlugin;
import net.paissad.waqtsalat.locationsprovider.LocationsProviderPlugin.LocationsProviderExtension;
import net.paissad.waqtsalat.ui.WaqtSalatUIConstants.ICONS;
import net.paissad.waqtsalat.ui.prefs.WaqtSalatPreferenceConstants;
import net.paissad.waqtsalat.ui.prefs.WaqtSalatPreferencePlugin;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * This is the central singleton for the WaqtSalat edit plugin. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public final class WaqtSalatUIPlugin extends EMFPlugin {

    public static final String            PLUGIN_ID            = "net.paissad.waqtsalat.ui"; //$NON-NLS-1$

    public static final String            BUNDLE_SYMBOLIC_NAME = PLUGIN_ID;

    /**
     * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final WaqtSalatUIPlugin INSTANCE             = new WaqtSalatUIPlugin();

    /**
     * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static Implementation         plugin;

    /**
     * Create the instance. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public WaqtSalatUIPlugin() {
        super(new ResourceLocator[] {});
    }

    /**
     * Returns the singleton instance of the Eclipse plugin. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the singleton instance.
     * @generated
     */
    @Override
    public ResourceLocator getPluginResourceLocator() {
        return plugin;
    }

    /**
     * Returns the singleton instance of the Eclipse plugin. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the singleton instance.
     * @generated
     */
    public static Implementation getPlugin() {
        return plugin;
    }

    /**
     * The actual implementation of the Eclipse <b>Plugin</b>. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static class Implementation extends EclipsePlugin {

        private ImageRegistry imageRegistry;

        /**
         * Creates an instance. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        public Implementation() {
            super();

            // Remember the static instance.
            //
            plugin = this;
        }

        @Override
        public void start(BundleContext context) throws Exception {
            super.start(context);
            initializeImageRegistry();
        }

        private void initializeImageRegistry() {
            // If we are in the UI Thread use that
            if (Display.getCurrent() != null) {
                imageRegistry = new ImageRegistry(Display.getCurrent());
            } else if (PlatformUI.isWorkbenchRunning()) {
                imageRegistry = new ImageRegistry(PlatformUI.getWorkbench().getDisplay());
            } else {
                // Invalid thread access if it is not the UI Thread
                // and the workbench is not created.
                throw new SWTError(SWT.ERROR_THREAD_INVALID_ACCESS);
            }

            imageRegistry.put(ICONS.KEY.PREFS, createImageDescriptor(ICONS.PATH.PREFS));
            imageRegistry.put(ICONS.KEY.TIMEZONE, createImageDescriptor(ICONS.PATH.TIMEZONE));
            imageRegistry.put(ICONS.KEY.REFRESH, createImageDescriptor(ICONS.PATH.REFRESH));
            imageRegistry.put(ICONS.KEY.LOOP, createImageDescriptor(ICONS.PATH.LOOP));
        }

        public ImageRegistry getImageRegistry() {
            return this.imageRegistry;
        }

        private ImageDescriptor createImageDescriptor(final String imageFilePath) {
            return ImageDescriptor.createFromURL(FileLocator.find(getBundle(), new Path(imageFilePath), null));
        }

        public ILogger getLogger() {
            return LoggerPlugin.getDefault().getLogger(getPlugin().getBundle(), PLUGIN_ID, getPlugin());
        }
    }

    /**
     * @return The {@link ImageRegistry}.
     */
    public static ImageRegistry getImageRegistry() {
        return getPlugin().getImageRegistry();
    }

    /**
     * This method is a proxy to {@link AbstractUIPlugin#imageDescriptorFromPlugin(String, String)}
     * 
     * @param imageFilePath
     * @return
     */
    public static ImageDescriptor getImageDescriptor(String imageFilePath) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, imageFilePath);
    }

    /**
     * @return the current {@link LocationsProviderExtension} or <code>null</code> if none.
     */
    public static LocationsProviderExtension getCurrentProviderExtension() {
        IPreferenceStore prefStore = WaqtSalatPreferencePlugin.getDefault().getPreferenceStore();
        String providerID = prefStore.getString(WaqtSalatPreferenceConstants.P_LOCATIONS_PROVIDER);
        LocationsProviderExtension locationsProviderExtension = null;
        if (providerID != null && !providerID.trim().isEmpty()) {
            locationsProviderExtension = LocationsProviderPlugin.getLocationsProviderManager().get(providerID);
        }
        return locationsProviderExtension;
    }

}
