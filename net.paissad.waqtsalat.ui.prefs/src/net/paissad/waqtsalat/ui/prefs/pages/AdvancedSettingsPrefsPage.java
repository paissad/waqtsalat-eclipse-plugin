package net.paissad.waqtsalat.ui.prefs.pages;

import net.paissad.waqtsalat.core.api.AdjustingMethod;
import net.paissad.waqtsalat.core.api.PrayName;
import net.paissad.waqtsalat.ui.prefs.WaqtSalatPreferenceConstants;
import net.paissad.waqtsalat.ui.prefs.WaqtSalatPreferencePlugin;
import net.paissad.waqtsalat.ui.prefs.editors.OffSetsFieldEditor;

import org.eclipse.jface.preference.ComboFieldEditor;
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
        addAdjustingMethodEditor();
        addOffsetsEditor();
    }

    private void addOffsetsEditor() {
        OffSetsFieldEditor offSetsFieldEditor = new OffSetsFieldEditor(WaqtSalatPreferenceConstants.P_OFFSETS,
                "Offsets", getFieldEditorParent());
        String tooltip = "Offsets to use in order to adjust the time in specific latitudes.\nBe careful, the strict order must be respected when setting offsets.";
        offSetsFieldEditor.getLabelControl(getFieldEditorParent()).setToolTipText(tooltip);

        StringBuilder groupText = new StringBuilder();
        boolean first = true;
        for (PrayName prayName : PrayName.getSortedPrayNames()) {
            if (!first) groupText.append(" - ");
            groupText.append(prayName.getLiteral());
            first = false;
        }
        offSetsFieldEditor.setGroupText(groupText.toString());

        addField(offSetsFieldEditor);
    }

    private void addAdjustingMethodEditor() {
        ComboFieldEditor adjustingMethodEditor = new ComboFieldEditor(WaqtSalatPreferenceConstants.P_ADJUSTING_METHOD,
                "Adjusting method", getAdjustingMethods(), getFieldEditorParent());
        adjustingMethodEditor.getLabelControl(getFieldEditorParent()).setToolTipText(
                "Set the adjusting method to use for higher latitudes.");
        addField(adjustingMethodEditor);
    }

    private String[][] getAdjustingMethods() {
        final AdjustingMethod[] adjustingMethods = AdjustingMethod.values();
        final String[][] result = new String[adjustingMethods.length][2];
        int i = 0;
        for (AdjustingMethod adjustingMethod : adjustingMethods) {
            result[i][0] = adjustingMethod.getLiteral() + "  (" + AdjustingMethod.getDescription(adjustingMethod) + ")";
            result[i][1] = adjustingMethod.getLiteral();
            i++;
        }
        return result;
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        super.propertyChange(event);
    }

}
