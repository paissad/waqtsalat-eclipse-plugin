package net.paissad.waqtsalat.ui.prefs.editors;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Spinner;

public class OffSetsFieldEditor extends FieldEditor {

    private static final int      OFFSETS_SIZE = 7;

    private static final String   SEPARATOR    = ","; //$NON-NLS-1$

    private Map<Integer, Spinner> spinnersMap;

    private Group                 spinnersGroup;

    private String                groupText;

    public OffSetsFieldEditor(String name, String labelText, Composite parent) {
        super(name, labelText, parent);
    }

    private void initSpinnersList(Composite parent) {
        if (spinnersMap == null) {
            spinnersMap = new LinkedHashMap<Integer, Spinner>();
        }
        for (int i = 0; i < OFFSETS_SIZE; i++) {
            Spinner spinner = new Spinner(parent, SWT.NONE);
            spinnersMap.put(Integer.valueOf(i), spinner);
        }
    }

    @Override
    protected void adjustForNumColumns(int numColumns) {
        GridData gd = (GridData) this.spinnersGroup.getLayoutData();
        gd.horizontalSpan = numColumns - 1;
        // We only grab excess space if we have to
        // If another field editor has more columns then
        // we assume it is setting the width.
        gd.grabExcessHorizontalSpace = gd.horizontalSpan == 1;
    }

    @Override
    protected void doFillIntoGrid(Composite parent, int numColumns) {
        getLabelControl(parent);

        this.spinnersGroup = getSpinnersGroup(parent);

        spinnersGroup.setLayout(new GridLayout(OFFSETS_SIZE, true));
        GridData layoutData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        layoutData.horizontalSpan = numColumns;

        if (getGroupText() != null) {
            this.spinnersGroup.setText(getGroupText());
        }

        spinnersGroup.setLayoutData(layoutData);

        initSpinnersList(spinnersGroup);
    }

    @Override
    protected void doLoad() {
        String storedValue = getPreferenceStore().getString(getPreferenceName());
        if (storedValue == null || storedValue.trim().isEmpty()) {
            return; // Do nothing
        }
        int[] offsets = buildOffsets(storedValue);
        updateSpinnersValues(offsets);
    }

    @Override
    protected void doLoadDefault() {
        String defaultValue = getPreferenceStore().getDefaultString(getPreferenceName());
        if (defaultValue == null || defaultValue.trim().isEmpty()) {
            return; // do nothing
        }
        int[] offsets = buildOffsets(defaultValue);
        updateSpinnersValues(offsets);
    }

    private void updateSpinnersValues(final int[] values) {
        for (int i = 0; i < values.length; i++) {
            Integer spinnerIndex = Integer.valueOf(i);
            this.spinnersMap.get(spinnerIndex).setSelection(values[i]);
        }
    }

    private int[] buildOffsets(final String str) throws IllegalStateException {
        String[] offsetsValue = str.split("\\s*" + Pattern.quote(SEPARATOR) + "\\s*"); //$NON-NLS-1$ //$NON-NLS-1$
        if (offsetsValue.length != OFFSETS_SIZE) {
            throw new IllegalStateException(
                    "The stored preference offset values must be an array of " + OFFSETS_SIZE + " in length."); //$NON-NLS-1$ //$NON-NLS-2$
        }
        int[] result = new int[OFFSETS_SIZE];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(offsetsValue[i]);
        }
        return result;
    }

    @Override
    protected void doStore() {
        String offsetsValue = "";
        boolean first = true;
        for (Integer spinnerIndex : spinnersMap.keySet()) {
            if (!first) offsetsValue += SEPARATOR;
            offsetsValue += spinnersMap.get(spinnerIndex).getSelection();
            first = false;
        }
        getPreferenceStore().setValue(getPreferenceName(), offsetsValue);
    }

    @Override
    public int getNumberOfControls() {
        return 1;
    }

    /**
     * @param parent
     * @return The {@link Group} control containing the list of spinners.
     */
    public Group getSpinnersGroup(Composite parent) {
        if (spinnersGroup == null) {
            spinnersGroup = new Group(parent, SWT.NONE);
        }
        return spinnersGroup;
    }

    public String getGroupText() {
        return groupText;
    }

    public void setGroupText(String groupText) {
        this.groupText = groupText;
        if (this.spinnersGroup != null) {
            this.spinnersGroup.setText(groupText);
        }
    }

}
