package net.paissad.waqtsalat.ui.actions;

import java.util.concurrent.Callable;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.waqtsalat.core.api.PrayName;
import net.paissad.waqtsalat.ui.WaqtSalatUIPlugin;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

public class HidePrayAction extends Action {

    private static final ILogger logger = WaqtSalatUIPlugin.getPlugin().getLogger();

    private PrayName             prayName;

    private Callable<Void>       callable;

    public HidePrayAction(PrayName prayName, String text, int style, ImageDescriptor imageDescriptor) {
        super(text, style);
        this.prayName = prayName;
        setImageDescriptor(imageDescriptor);
    }

    public void setCallable(Callable<Void> callable) {
        this.callable = callable;
    }

    @Override
    public void run() {
        try {
            if (this.callable != null) {
                this.callable.call();
            }
        } catch (Exception e) {
            logger.error("Error while running callable for show/hide for '" + prayName + "' : " + e.getMessage(), e); //$NON-NLS-1$ //$NON-NLS-2$ 
        }
    }

}
