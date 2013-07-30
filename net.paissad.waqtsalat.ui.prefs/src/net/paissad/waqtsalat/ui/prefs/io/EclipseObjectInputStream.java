package net.paissad.waqtsalat.ui.prefs.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

/**
 * <b>NOTE :</b> When using this class, then {@link EclipseObjectOutputStream} must have been used during prior
 * serializing process.
 * 
 * @author paissad
 * 
 * @see EclipseObjectOutputStream
 */
public class EclipseObjectInputStream extends ObjectInputStream {

    public EclipseObjectInputStream(InputStream inputstream) throws IOException {
        super(inputstream);
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        String bundleName = this.readUTF();
        if ("bootClasspath".equals(bundleName)) { //$NON-NLS-1$
            return super.resolveClass(desc);
        } else {
            Bundle bundle = Platform.getBundle(bundleName);
            if (bundle != null) {
                return bundle.loadClass(desc.getName());
            }
            throw new ClassNotFoundException("Unable to find the bundle containing the class '" + desc.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }
}
