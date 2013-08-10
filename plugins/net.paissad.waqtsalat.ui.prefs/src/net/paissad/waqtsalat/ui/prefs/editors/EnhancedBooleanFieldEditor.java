package net.paissad.waqtsalat.ui.prefs.editors;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class EnhancedBooleanFieldEditor extends BooleanFieldEditor {

    public EnhancedBooleanFieldEditor(String name, String labelText, Composite parent) {
        super(name, labelText, parent);
    }

    public Button getCheckBox(Composite parent) {
        return getChangeControl(parent);
    }
}
