package net.paissad.waqtsalat.ui.providers;

import net.paissad.waqtsalat.core.api.CalculationMethod;

import org.eclipse.jface.viewers.LabelProvider;

public class CalculationMethodLabelProvider extends LabelProvider {

    private static CalculationMethodLabelProvider instance;

    public static CalculationMethodLabelProvider getInstance() {
        synchronized (CalculationMethodLabelProvider.class) {
            if (instance == null) {
                instance = new CalculationMethodLabelProvider();
            }
            return instance;
        }
    }

    @Override
    public String getText(Object element) {
        if (element instanceof CalculationMethod) {
            String description = CalculationMethod.getDescription((CalculationMethod) element);
            return ((CalculationMethod) element).getLiteral() + "  (" + description + ")";
        }
        return super.getText(element);
    }

}
