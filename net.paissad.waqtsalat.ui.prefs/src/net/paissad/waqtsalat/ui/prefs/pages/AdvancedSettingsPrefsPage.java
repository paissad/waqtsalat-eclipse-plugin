package net.paissad.waqtsalat.ui.prefs.pages;

import net.paissad.waqtsalat.ui.prefs.WaqtSalatPreferencePlugin;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class AdvancedSettingsPrefsPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public static final String PAGE_ID = "net.paissad.waqtsalat.ui.prefs.WaqtSalatAdvancedSettingsPrefsPage"; //$NON-NLS-1$

    public AdvancedSettingsPrefsPage() {
        super(GRID);
    }

    @Override
    public void init(IWorkbench workbench) {
        setPreferenceStore(WaqtSalatPreferencePlugin.getDefault().getPreferenceStore());
        setDescription("Advanced settings.");
    }

    @Override
    protected void createFieldEditors() {
        // TODO : implement field editors for Advanced Setting Preference Page.
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        super.propertyChange(event);
        this.hookAllListeners();
    }

    private void hookAllListeners() {
        // TODO Auto-generated method stub
    }

}
