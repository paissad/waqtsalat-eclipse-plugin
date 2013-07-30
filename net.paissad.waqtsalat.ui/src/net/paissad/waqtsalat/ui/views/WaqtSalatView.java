package net.paissad.waqtsalat.ui.views;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.waqtsalat.locationsprovider.LocationsProviderPlugin.LocationsProviderExtension;
import net.paissad.waqtsalat.locationsprovider.api.City;
import net.paissad.waqtsalat.ui.WaqtSalatUIConstants.ICONS;
import net.paissad.waqtsalat.ui.WaqtSalatUIPlugin;
import net.paissad.waqtsalat.ui.actions.OpenPreferencesAction;
import net.paissad.waqtsalat.ui.beans.DummyCityWrapper;
import net.paissad.waqtsalat.ui.components.SearchBox;
import net.paissad.waqtsalat.ui.components.SearchBox.InputPolicyRule;
import net.paissad.waqtsalat.ui.prefs.WaqtSalatPreferenceConstants;
import net.paissad.waqtsalat.ui.prefs.WaqtSalatPreferencePlugin;
import net.paissad.waqtsalat.ui.prefs.WaqtSalatPreferenceStore;
import net.paissad.waqtsalat.ui.providers.CityTableLabelProvider;
import net.paissad.waqtsalat.ui.util.LuceneUtil;
import net.paissad.waqtsalat.ui.util.WaqtSalatUIHelper;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

public class WaqtSalatView extends ViewPart {

    public static final String   VIEW_ID                                  = "net.paissad.waqtsalat.ui.views.WaqtSalatView"; //$NON-NLS-1$

    private static final ILogger logger                                   = WaqtSalatUIPlugin.getPlugin().getLogger();

    private IAction              openPreferencesAction;

    private static Set<String>   locProvidersHavingLuceneIndexInitialized = new HashSet<String>();

    private LuceneUtil           luceneUtil;

    private SearchBox            searchBox;

    private CLabel               labelSelectedCity;
    private Table                table;

    private DateTime             dateTime;

    private TableViewer          tableViewer;

    private City                 citySelectedFromSearch;

    private Button               buttonSetCity;

    private CLabel               labelSelectedtimezone;

    public WaqtSalatView() {
    }

    @Override
    public void init(IViewSite site) throws PartInitException {
        super.init(site);
        setContentDescription("WaqtSalat View ...");
        this.initLuceneCitiesIndex();
    }

    @SuppressWarnings("unused")
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
                    labelSelectedCity = new CLabel(locationComposite, SWT.NONE);
                    labelSelectedCity.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
                    updateLabelSelectedCity(getCityFromPreference());
                }
                new Label(locationComposite, SWT.NONE);
                {
                    labelSelectedtimezone = new CLabel(locationComposite, SWT.NONE);
                    labelSelectedtimezone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
                    updateLabelSelectedTimezone();
                }
                new Label(locationComposite, SWT.NONE);
                {
                    Button btnGetTheLocation = new Button(locationComposite, SWT.CHECK);
                    btnGetTheLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
                    btnGetTheLocation.setText("Get the location automatically (Need internet connection !)");
                }
                new Label(locationComposite, SWT.NONE);
                {
                    searchBox = new SearchBox(locationComposite, SWT.NONE);
                    searchBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
                    initTableColumns(searchBox.getTableViewer());
                    searchBox.getTableViewer().setLabelProvider(new CityTableLabelProvider());
                    searchBox.getTableViewer().setContentProvider(ArrayContentProvider.getInstance());
                    searchBox.setInputPolicyRule(new InputPolicyRuleImpl(searchBox));
                    searchBox.getTableViewer().addDoubleClickListener(new IDoubleClickListener() {
                        @Override
                        public void doubleClick(DoubleClickEvent event) {
                            ISelection selection = event.getSelection();
                            if (selection instanceof IStructuredSelection) {
                                Object selectedElement = ((IStructuredSelection) selection).getFirstElement();
                                if (selectedElement instanceof City) {
                                    citySelectedFromSearch = (City) selectedElement;
                                }
                            }
                        }
                    });
                }
                {
                    initButtonSetCity(locationComposite);
                }
            }

        }
        {
            Composite rightComposite = new Composite(container, SWT.NONE);
            rightComposite.setLayout(new GridLayout(1, false));
            rightComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
            {
                dateTime = new DateTime(rightComposite, SWT.BORDER | SWT.DROP_DOWN | SWT.LONG);
                dateTime.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
            }
            {
                tableViewer = new TableViewer(rightComposite, SWT.BORDER | SWT.FULL_SELECTION);
                table = tableViewer.getTable();
                table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
            }
        }

        getSite().setSelectionProvider(searchBox.getTableViewer());

        createActions();
        contributeToActionBars();
    }

    /**
     * @param city - may be <code>null</code>.
     */
    private void updateLabelSelectedCity(final City city) {
        labelSelectedCity.setText(buildLabelForCity(city));
        final String countryCode = city == null ? "-" : city.getCountry().getCode(); //$NON-NLS-1$
        labelSelectedCity.setImage(WaqtSalatUIHelper.getFlagForCountryCode(countryCode));
        labelSelectedCity.layout();
    }

    private void updateLabelSelectedTimezone() {
        String selectedTimezoneID = getPrefStore().getString(WaqtSalatPreferenceConstants.P_TIMEZONE);
        labelSelectedtimezone.setText(selectedTimezoneID);
        labelSelectedtimezone.setImage(WaqtSalatUIPlugin.getImageRegistry().get(ICONS.KEY.TIMEZONE));
    }

    private void initButtonSetCity(final Composite parent) {
        buttonSetCity = new Button(parent, SWT.NONE);
        buttonSetCity.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
        buttonSetCity.setText("Set");
        buttonSetCity.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (citySelectedFromSearch != null) {
                    updateLabelSelectedCity(citySelectedFromSearch);
                    saveCityPreference(citySelectedFromSearch);
                }
            }
        });
    }

    private City getCityFromPreference() {
        Object obj = getPrefStore().getObject(WaqtSalatPreferenceConstants.P_CURRENT_CITY);
        if (obj == null) {
            return null;

        } else if (!(obj instanceof DummyCityWrapper)) {
            throw new IllegalArgumentException(
                    "The stored city into prefererence is not an instance of DummyCityWrapper"); //$NON-NLS-1$

        } else {
            return ((DummyCityWrapper) obj).unwrap();
        }
    }

    private void saveCityPreference(City city) {
        // We use the city wrapper because it is hard, really hard to save the city EObject
        // instance which is not contained into a resource.
        // Using the wrapper which a serializable object, but not an EObject helps as a
        // workaround.
        DummyCityWrapper cityWrapper = new DummyCityWrapper(city);
        getPrefStore().setValue(WaqtSalatPreferenceConstants.P_CURRENT_CITY, cityWrapper);
        try {
            getPrefStore().save();
        } catch (IOException e) {
            logger.error("Error while saving the city preference : " + e.getMessage(), e);
        }
    }

    /**
     * @param city - may be <code>null</code>.
     * @return The label to show.
     */
    private String buildLabelForCity(City city) {
        if (city == null) {
            return "<no city selected yet>";
        } else {
            StringBuilder sb = new StringBuilder().append(city.getName()).append(", ")
                    .append(city.getCountry().getName());
            String postalCode = city.getPostalCode();
            if (postalCode != null && !postalCode.trim().isEmpty()) {
                sb.append(" (").append(postalCode).append(")");
            }
            String region = city.getRegion();
            if (region != null && !region.trim().isEmpty()) {
                sb.append(" - ").append(region);
            }
            return sb.toString();
        }
    }

    private void initTableColumns(TableViewer tableViewer) {
        for (String columnName : new String[] { "Country", "City", "Region", "Postal Code" }) {
            TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.None);
            TableColumn tableColumn = tableViewerColumn.getColumn();
            tableColumn.setText(columnName);
            tableColumn.setWidth(100);
            tableColumn.setMoveable(true);
            tableColumn.setResizable(true);
        }
    }

    private WaqtSalatPreferenceStore getPrefStore() {
        return WaqtSalatPreferencePlugin.getDefault().getPreferenceStore();
    }

    /**
     * Fills the action Bars
     */
    private void contributeToActionBars() {
        IActionBars bars = getViewSite().getActionBars();
        fillLocalPullDown(bars.getMenuManager());
        fillLocalToolBar(bars.getToolBarManager());
    }

    private void fillLocalPullDown(IMenuManager menuManager) {
        menuManager.add(openPreferencesAction);
    }

    /**
     * Initialize the toolbar.
     */
    private void fillLocalToolBar(IToolBarManager toolbarManager) {
        toolbarManager.add(openPreferencesAction);
    }

    /**
     * Create the actions.
     */
    private void createActions() {
        this.openPreferencesAction = new OpenPreferencesAction();
        this.openPreferencesAction.setText("Open Preferences");
        this.openPreferencesAction.setToolTipText("Open WaqtSalat Preferences Page.");
        this.openPreferencesAction.setImageDescriptor(WaqtSalatUIPlugin.getImageDescriptor(ICONS.PATH.PREFS));
    }

    @Override
    public void setFocus() {
        if (labelSelectedCity != null) {
            labelSelectedCity.setFocus();
        }
    }

    private void initLuceneCitiesIndex() {
        luceneUtil = new LuceneUtil();
        try {
            LocationsProviderExtension currentProviderExtension = WaqtSalatUIPlugin.getCurrentProviderExtension();
            if (currentProviderExtension != null) {
                String providerID = currentProviderExtension.getId();
                boolean force = !(locProvidersHavingLuceneIndexInitialized.contains(providerID));
                luceneUtil.createCitiesIndex(force);
                locProvidersHavingLuceneIndexInitialized.add(providerID);
            } else {
                logger.warn("Lucene cities index not created due to unavailable locations provider."); //$NON-NLS-1$
            }
        } catch (Exception e) {
            logger.error("Error while creating Lucene cities index.", e);
        }
    }

    private static class InputPolicyRuleImpl implements InputPolicyRule {

        private final SearchBox box;

        public InputPolicyRuleImpl(SearchBox searchBox) {
            this.box = searchBox;
        }

        @Override
        public Object getInput() {
            String enteredText = box.getCurrentSearchText();
            if (enteredText != null) {
                enteredText = enteredText.replaceAll("^[\\*\\?]", ""); //$NON-NLS-1$
                if (enteredText.trim().length() >= 3) {
                    if (!enteredText.endsWith("*")) enteredText += "*"; //$NON-NLS-1$ //$NON-NLS-2$
                    try {
                        final Set<City> cities = new LinkedHashSet<City>(LuceneUtil.searchByCityName(enteredText));
                        cities.addAll(LuceneUtil.searchByCountryName(enteredText));
                        cities.addAll(LuceneUtil.searchByPostalCode(enteredText));
                        cities.addAll(LuceneUtil.searchByRegion(enteredText));
                        return cities;

                    } catch (Exception e) {
                        logger.error(
                                "Error search city by using query --> " + enteredText + " <-- : " + e.getMessage(), e); //$NON-NLS-1$ //$NON-NLS-2$
                    }
                }
            }
            return null;
        }
    }
}
