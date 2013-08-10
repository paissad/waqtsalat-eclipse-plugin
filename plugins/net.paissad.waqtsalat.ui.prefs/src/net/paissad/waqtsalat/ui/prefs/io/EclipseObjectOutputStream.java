package net.paissad.waqtsalat.ui.prefs.io;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * <b>NOTE :</b> When using this class for serializing, then {@link EclipseObjectInputStream} must be used for
 * deserialization process.
 * 
 * @author paissad
 * 
 * @see EclipseObjectInputStream
 */
public class EclipseObjectOutputStream extends ObjectOutputStream {

    public EclipseObjectOutputStream(OutputStream outputstream) throws IOException {
        super(outputstream);
    }

    @Override
    protected void annotateClass(Class<?> clazz) throws IOException {
        super.annotateClass(clazz);
        Bundle declaringBundle = FrameworkUtil.getBundle(clazz);
        String bundleName = null;
        if (declaringBundle != null) {
            bundleName = declaringBundle.getSymbolicName();
        } else {
            bundleName = "bootClasspath"; //$NON-NLS-1$
        }
        /*
         * Write in the stream the bundle that loaded the class. It will be read by resolveClass() of
         * EclipseObjectInputStream.
         */
        this.writeUTF(bundleName);
    }
}
