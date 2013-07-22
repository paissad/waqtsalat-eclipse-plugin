package net.paissad.waqtsalat.ui.views;

import java.util.Collection;
import java.util.Iterator;
import java.util.TimeZone;

import net.paissad.waqtsalat.core.util.WaqtSalatUtil;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.part.ViewPart;

public class WaqtSalatView extends ViewPart {

    public static final String    VIEW_ID         = "net.paissad.waqtsalat.ui.views.WaqtSalatView"; //$NON-NLS-1$

    private static final TimeZone SYSTEM_TIMEZONE = TimeZone.getDefault();

    private Table                 table;

    public WaqtSalatView() {
    }

    @Override
    public void createPartControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout(2, false));
        {
            Composite leftComposite = new Composite(container, SWT.NONE);
            leftComposite.setLayout(new GridLayout(1, false));
            leftComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
            {
                Composite locationComposite = new Composite(leftComposite, SWT.NONE);
                locationComposite.setLayout(new GridLayout(2, false));
                locationComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
                {
                    Label lblSelectedCity = new Label(locationComposite, SWT.NONE);
                    lblSelectedCity.setText("SelectedCity");
                }
                {
                    Label lblFlag = new Label(locationComposite, SWT.NONE);
                    lblFlag.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
                    lblFlag.setText("flag");
                }
                {
                    Label lblSelectedtimezone = new Label(locationComposite, SWT.NONE);
                    lblSelectedtimezone.setText("SelectedTimezone");
                }
                new Label(locationComposite, SWT.NONE);
                {
                    Button btnGetTheLocation = new Button(locationComposite, SWT.CHECK);
                    btnGetTheLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
                    btnGetTheLocation.setText("Get the location automatically (Need internet connection !)");
                }
                new Label(locationComposite, SWT.NONE);
                {
                    ComboViewer comboViewer = new ComboViewer(locationComposite, SWT.NONE);
                    Combo searchLocationCombo = comboViewer.getCombo();
                    searchLocationCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
                }
                {
                    Button setButton = new Button(locationComposite, SWT.NONE);
                    setButton.setText("Set");
                }
            }

            {
                Label label = new Label(leftComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
                label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
            }
            {
                {
                    Collection<TimeZone> availableTimezones = WaqtSalatUtil.getAvailableTimezones();
                    int selectionIndex = 0;
                    for (Iterator<TimeZone> iterator = availableTimezones.iterator(); iterator.hasNext();) {
                        TimeZone timeZone = (TimeZone) iterator.next();
                        if (!SYSTEM_TIMEZONE.equals(timeZone)) {
                            selectionIndex++;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        {
            Composite rightComposite = new Composite(container, SWT.NONE);
            rightComposite.setLayout(new GridLayout(1, false));
            rightComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

            TableViewer tableViewer = new TableViewer(rightComposite, SWT.BORDER | SWT.FULL_SELECTION);
            table = tableViewer.getTable();
            table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        }

        createActions();
        initializeToolBar();
        initializeMenu();

    }

    /**
     * Create the actions.
     */
    private void createActions() {
        // Create the actions
    }

    /**
     * Initialize the toolbar.
     */
    private void initializeToolBar() {
        IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
    }

    /**
     * Initialize the menu.
     */
    private void initializeMenu() {
        IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
    }

    @Override
    public void setFocus() {
        // Set the focus
    }
}
