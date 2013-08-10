package net.paissad.waqtsalat.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;

public class RefreshAction extends Action {

    private final Composite composite;

    public RefreshAction(String text, ImageDescriptor image, Composite composite) {
        super(text, image);
        this.composite = composite;
    }

    @Override
    public void run() {
        if (composite != null) composite.layout();
    }

}
