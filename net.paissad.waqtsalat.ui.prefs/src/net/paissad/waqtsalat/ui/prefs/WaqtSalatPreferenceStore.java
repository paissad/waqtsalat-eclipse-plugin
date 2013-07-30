package net.paissad.waqtsalat.ui.prefs;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.waqtsalat.ui.prefs.io.EclipseObjectInputStream;
import net.paissad.waqtsalat.ui.prefs.io.EclipseObjectOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

public class WaqtSalatPreferenceStore extends ScopedPreferenceStore {

    private static ILogger logger = WaqtSalatPreferencePlugin.getDefault().getLogger();

    public WaqtSalatPreferenceStore(IScopeContext context, String qualifier) {
        super(context, qualifier);
    }

    /**
     * <b>NOTE :</b> If an error occurs, the value is not set.
     * 
     * @param name
     * @param value
     */
    public void setValue(String name, Object value) {
        String stringValue = null;
        try {
            if (value != null) {
                stringValue = serializeToString(value);
            }
            setValue(name, stringValue);
        } catch (IOException e) {
            logger.error("Error while saving the preference value for '" + name + "' : " + e.getMessage(), e);
        }
    }

    /**
     * 
     * @param name
     * @return The stored value or <code>null</code> if an error occurs.
     */
    public Serializable getObject(String name) {
        Serializable result = null;
        try {
            String base64String = getString(name);
            if (base64String != null && !base64String.trim().isEmpty()) {
                result = (Serializable) deserializeToObject(base64String);
            }
        } catch (Exception e) {
            logger.error("Error while retrieving the preference value for '" + name + "' : " + e.getMessage(), e);
        }
        return result;
    }

    private static String serializeToString(Object object) throws IOException {

        ByteArrayOutputStream arrayOutputStream = null;
        EclipseObjectOutputStream objectOutputStream = null;
        try {
            arrayOutputStream = new ByteArrayOutputStream(8192);
            objectOutputStream = new EclipseObjectOutputStream(arrayOutputStream);
            objectOutputStream.writeObject(object);

            objectOutputStream.flush();
            return Base64.encodeBase64String(arrayOutputStream.toByteArray());

        } finally {
            closeQuietly(arrayOutputStream, objectOutputStream);
        }
    }

    private static Object deserializeToObject(String base64String) throws IOException, ClassNotFoundException {
        ByteArrayInputStream arrayInputStream = null;
        EclipseObjectInputStream objectInputStream = null;
        try {
            byte[] data = Base64.decodeBase64(base64String);
            arrayInputStream = new ByteArrayInputStream(data);
            objectInputStream = new EclipseObjectInputStream(arrayInputStream);
            return objectInputStream.readObject();

        } finally {
            closeQuietly(arrayInputStream, objectInputStream);
        }
    }

    private static void closeQuietly(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

}
