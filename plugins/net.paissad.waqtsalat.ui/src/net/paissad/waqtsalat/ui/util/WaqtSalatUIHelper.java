package net.paissad.waqtsalat.ui.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.waqtsalat.ui.WaqtSalatUIPlugin;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

public class WaqtSalatUIHelper {

    private static final ILogger logger = WaqtSalatUIPlugin.getPlugin().getLogger();

    private WaqtSalatUIHelper() {
    }

    public static Image getFlagForCountryCode(final String countryCode) {
        ImageRegistry imageRegistry = WaqtSalatUIPlugin.getImageRegistry();
        String key = "__flag__" + countryCode; //$NON-NLS-1$
        if (imageRegistry.get(key) == null) {
            String code = (countryCode == null || countryCode.trim().isEmpty()) ? "-" : countryCode.toLowerCase(Locale.ENGLISH); //$NON-NLS-1$
            ImageDescriptor imageDescriptor = WaqtSalatUIPlugin
                    .getImageDescriptor("icons/custom/flags/" + code + ".gif"); //$NON-NLS-1$ //$NON-NLS-2$
            if (imageDescriptor == null) {
                // Flag not available from current images.
                imageDescriptor = WaqtSalatUIPlugin.getImageDescriptor("icons/custom/flags/-.gif"); //$NON-NLS-1$
            }
            Image flagImage = imageDescriptor.createImage();
            imageRegistry.put(key, flagImage);
        }
        return imageRegistry.get(key);
    }

    /**
     * @return The IP address or <code>null</code> if unable to retrieve correctly (error, timeout, ... whatever).
     */
    public static String getIPAddress() {

        BufferedReader in = null;
        try {

            String ip = null;

            URL url = new URL("http://checkip.dyndns.com/"); //$NON-NLS-1$
            URLConnection urlConnection = url.openConnection();
            urlConnection.setConnectTimeout((int) TimeUnit.MINUTES.toMillis(1));
            urlConnection.setAllowUserInteraction(false);
            urlConnection.connect();

            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8")); //$NON-NLS-1$
            String line = in.readLine();
            if (line != null) {
                ip = line.replaceAll(".*:\\s*([\\d\\.]+).*", "$1").trim(); //$NON-NLS-1$ //$NON-NLS-2$
            }

            return ip;

        } catch (Exception e) {
            logger.error(
                    "Error while trying to retrieve the IP address. Going to return null value : " + e.getMessage(), e); //$NON-NLS-1$
            return null;

        } finally {
            try {
                in.close();
            } catch (Exception e) {
            }
        }
    }
}
