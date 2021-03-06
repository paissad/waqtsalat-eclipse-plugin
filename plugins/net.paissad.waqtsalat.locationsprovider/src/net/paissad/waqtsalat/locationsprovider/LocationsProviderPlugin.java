/**
 */
package net.paissad.waqtsalat.locationsprovider;

import java.util.HashMap;
import java.util.Map;

import net.paissad.waqtsalat.locationsprovider.api.IGeolocationProvider;
import net.paissad.waqtsalat.locationsprovider.api.ILocationsProvider;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Version;

/**
 * This is the central singleton for the Locations edit plugin. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public final class LocationsProviderPlugin extends EMFPlugin {

    /** This plugin ID */
    public static final String                             PLUGIN_ID                = Implementation.PLUGIN_ID;

    private static Map<String, LocationsProviderExtension> locationsProviderManager = new HashMap<String, LocationsProviderExtension>();

    /**
     * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final LocationsProviderPlugin            INSTANCE                 = new LocationsProviderPlugin();

    /**
     * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static Implementation                          plugin;

    /**
     * Create the instance. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public LocationsProviderPlugin() {
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

        public static final String PLUGIN_ID                              = "net.paissad.waqtsalat.locationsprovider"; //$NON-NLS-1$

        public static final String LOCATIONS_PROVIDER_EXTENSION_POINTNAME = "locationsprovider";                      //$NON-NLS-1$

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
            initLocationsProviderManager();
        }

        private static void initLocationsProviderManager() {
            IConfigurationElement[] configurationElements = Platform.getExtensionRegistry()
                    .getConfigurationElementsFor(PLUGIN_ID, LOCATIONS_PROVIDER_EXTENSION_POINTNAME);
            for (final IConfigurationElement elt : configurationElements) {
                String namespaceIdentifier = elt.getNamespaceIdentifier();
                Bundle bundle = Platform.getBundle(namespaceIdentifier);
                Version version = (bundle == null) ? Version.emptyVersion : bundle.getVersion();
                String id = elt.getAttribute("id"); //$NON-NLS-1$
                String name = elt.getAttribute("name"); //$NON-NLS-1$
                boolean geolocationSupported = Boolean.parseBoolean(elt.getAttribute("geolocationSupported")); //$NON-NLS-1$
                ILocationsProvider locationsProvider;
                try {
                    locationsProvider = (ILocationsProvider) elt.createExecutableExtension("class"); //$NON-NLS-1$
                } catch (CoreException e) {
                    locationsProvider = null;
                }
                IGeolocationProvider geolocationProvider;
                try {
                    geolocationProvider = (IGeolocationProvider) elt
                            .createExecutableExtension("geolocationProviderClass"); //$NON-NLS-1$
                } catch (CoreException e) {
                    geolocationProvider = null;
                }
                locationsProviderManager.put(id, new LocationsProviderExtension(id, name, version, locationsProvider,
                        geolocationSupported, geolocationProvider));
            }
        }
    }

    /**
     * @return The map containing the known implementations of ILocationsProvider.
     */
    public static Map<String, LocationsProviderExtension> getLocationsProviderManager() {
        return locationsProviderManager;
    }

    /**
     * The bean representing the extension point of locationsprovider. This class is immutable.
     */
    public static final class LocationsProviderExtension {

        private final String               id;
        private final String               name;
        private final Version              version;
        private final ILocationsProvider   locationsProvider;
        private final boolean              geolocationSupported;
        private final IGeolocationProvider geolocationProvider;

        public LocationsProviderExtension(String id, String name, Version version,
                ILocationsProvider locationsProvider, boolean geolocationSupported,
                IGeolocationProvider geolocationProvider) {
            this.id = id;
            this.name = name;
            this.version = version;
            this.locationsProvider = locationsProvider;
            this.geolocationSupported = geolocationSupported;
            this.geolocationProvider = geolocationProvider;
        }

        public String getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public Version getVersion() {
            return this.version;
        }

        public ILocationsProvider getLocationsProvider() {
            return this.locationsProvider;
        }

        public boolean isGeolocationSupported() {
            return this.geolocationSupported;
        }

        public IGeolocationProvider getGeolocationProvider() {
            return this.geolocationProvider;
        }
    }

}
