package net.paissad.waqtsalat.ui.prefs.util;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class SWTUtil {

    private SWTUtil() {
    }

    public static void setEnabledRecursively(final Control control, boolean enabled) {
        if (control instanceof Composite) {
            Composite composite = (Composite) control;
            for (Control c : composite.getChildren()) {
                setEnabledRecursively(c, enabled);
            }
        } else {
            control.setEnabled(enabled);
        }
    }
}
