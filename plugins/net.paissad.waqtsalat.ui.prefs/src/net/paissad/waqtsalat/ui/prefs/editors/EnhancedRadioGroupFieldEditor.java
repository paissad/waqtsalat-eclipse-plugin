package net.paissad.waqtsalat.ui.prefs.editors;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class EnhancedRadioGroupFieldEditor extends RadioGroupFieldEditor {

    public EnhancedRadioGroupFieldEditor(String name, String labelText, int numColumns, String[][] labelAndValues,
            Composite parent) {
        super(name, labelText, numColumns, labelAndValues, parent);
    }

    /**
     * @param parent
     * @return The collection of buttons contained into this RadioGroupFieldEditor.
     */
    public Collection<Button> getButtons(Composite parent) {
        final Collection<Button> result = new ArrayList<Button>();
        for (final Control child : getRadioBoxControl(parent).getChildren()) {
            if (child instanceof Button) {
                result.add((Button) child);
            }
        }
        return result;
    }

    public Button getButton(final String name, Composite parent) {
        for (Button button : getButtons(parent)) {
            if (name.equals(button.getText())) {
                return button;
            }
        }
        return null;
    }

}
