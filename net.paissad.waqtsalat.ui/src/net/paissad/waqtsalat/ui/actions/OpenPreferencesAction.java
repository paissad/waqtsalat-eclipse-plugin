package net.paissad.waqtsalat.ui.actions;

import net.paissad.waqtsalat.ui.prefs.WaqtSalatPreferenceConstants;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferencesUtil;

public class OpenPreferencesAction extends Action {

    @Override
    public void run() {
        Shell shell = null;
        IWorkbench wb = PlatformUI.getWorkbench();
        if (wb != null) {
            IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
            if (win != null) {
                shell = win.getShell();
            }
        }
        PreferenceDialog dialog = PreferencesUtil.createPreferenceDialogOn(shell,
                WaqtSalatPreferenceConstants.PageID.PREFS_MAIN_PAGE, null, null);
        dialog.open();
    }

}
