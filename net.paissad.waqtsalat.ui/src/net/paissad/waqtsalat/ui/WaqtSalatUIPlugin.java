/**
 */
package net.paissad.waqtsalat.ui;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.eclipse.logger.LoggerPlugin;
import net.paissad.waqtsalat.locationsprovider.LocationsProviderPlugin;
import net.paissad.waqtsalat.locationsprovider.LocationsProviderPlugin.LocationsProviderExtension;
import net.paissad.waqtsalat.ui.WaqtSalatUIConstants.ICONS;
import net.paissad.waqtsalat.ui.audio.api.ISoundPlayer;
import net.paissad.waqtsalat.ui.beans.SoundPlayerExtension;
import net.paissad.waqtsalat.ui.prefs.WaqtSalatPreferenceConstants;
import net.paissad.waqtsalat.ui.prefs.WaqtSalatPreferencePlugin;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
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

    private static final String                      SOUNDPLAYERS_EXTENSION_POINT_ID = "soundplayers";            //$NON-NLS-1$

    public static final String                       PLUGIN_ID                       = "net.paissad.waqtsalat.ui"; //$NON-NLS-1$

    public static final String                       BUNDLE_SYMBOLIC_NAME            = PLUGIN_ID;

    /**
     * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final WaqtSalatUIPlugin            INSTANCE                        = new WaqtSalatUIPlugin();

    /**
     * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static Implementation                    plugin;

    private static Map<String, SoundPlayerExtension> soundPlayersManager;

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
            initSoundPlayersManager();
            initializeImageRegistry();
        }

        private static void initSoundPlayersManager() {

            soundPlayersManager = new HashMap<String, SoundPlayerExtension>();

            IConfigurationElement[] configElements = Platform.getExtensionRegistry().getConfigurationElementsFor(
                    PLUGIN_ID, SOUNDPLAYERS_EXTENSION_POINT_ID);

            for (IConfigurationElement elt : configElements) {
                String id = elt.getAttribute("id"); //$NON-NLS-1$
                String name = elt.getAttribute("name"); //$NON-NLS-1$
                ISoundPlayer soundPlayer;
                try {
                    soundPlayer = (ISoundPlayer) elt.createExecutableExtension("class"); //$NON-NLS-1$
                } catch (CoreException e) {
                    soundPlayer = null;
                }
                String[] supportedTypes = elt.getAttribute("supportedTypes").split("\\s*,\\s*"); //$NON-NLS-1$ //$NON-NLS-2$
                for (int i = 0; i < supportedTypes.length; i++) {
                    supportedTypes[i] = supportedTypes[i].toLowerCase(Locale.ENGLISH);
                }
                soundPlayersManager.put(id, new SoundPlayerExtension(id, name, soundPlayer, supportedTypes));
            }
            soundPlayersManager = Collections.unmodifiableMap(soundPlayersManager);
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
            imageRegistry.put(ICONS.KEY.SUNRISE, createImageDescriptor(ICONS.PATH.SUNRISE));
            imageRegistry.put(ICONS.KEY.SUNSET, createImageDescriptor(ICONS.PATH.SUNSET));
            imageRegistry.put(ICONS.KEY.GRAY_POINT, createImageDescriptor(ICONS.PATH.GRAY_POINT));
            imageRegistry.put(ICONS.KEY.YELLOW_POINT, createImageDescriptor(ICONS.PATH.YELLOW_POINT));
            imageRegistry.put(ICONS.KEY.GREEN_POINT, createImageDescriptor(ICONS.PATH.GREEN_POINT));
            imageRegistry.put(ICONS.KEY.START, createImageDescriptor(ICONS.PATH.START));
            imageRegistry.put(ICONS.KEY.TERMINATE, createImageDescriptor(ICONS.PATH.TERMINATE));
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

    /**
     * @return The sound players manager which helps to retrieve the correct extension (implementation) to use for
     *         reading an audio file.
     */
    public static Map<String, SoundPlayerExtension> getSoundPlayersManager() {
        return soundPlayersManager;
    }

}
