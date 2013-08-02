package net.paissad.waqtsalat.ui.actions;

import java.util.concurrent.Callable;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.waqtsalat.ui.WaqtSalatUIPlugin;

import org.eclipse.jface.action.Action;

public class SetAutomaticUpdateAtMidnightAction extends Action {

    private static ILogger       logger = WaqtSalatUIPlugin.getPlugin().getLogger();

    private final Callable<Void> callable;

    public SetAutomaticUpdateAtMidnightAction(Callable<Void> callable, String text, int style) {
        super(text, style);
        this.callable = callable;
    }

    @Override
    public void run() {
        try {
            this.callable.call();
        } catch (Exception e) {
            logger.error("Error while updating displayed pray times at midnight : " + e.getMessage(), e); //$NON-NLS-1$
        }
    }

}
