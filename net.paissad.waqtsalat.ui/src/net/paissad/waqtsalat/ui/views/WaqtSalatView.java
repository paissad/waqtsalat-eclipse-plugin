package net.paissad.waqtsalat.ui.views;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TimeZone;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.waqtsalat.locationsprovider.LocationsProviderPlugin.LocationsProviderExtension;
import net.paissad.waqtsalat.locationsprovider.api.City;
import net.paissad.waqtsalat.ui.WaqtSalatUIConstants.ICONS;
import net.paissad.waqtsalat.ui.WaqtSalatUIPlugin;
import net.paissad.waqtsalat.ui.actions.OpenPreferencesAction;
import net.paissad.waqtsalat.ui.beans.DummyCityWrapper;
import net.paissad.waqtsalat.ui.beans.TimeZoneWrapper;
import net.paissad.waqtsalat.ui.comparators.CityTableViewerComparator;
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
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ArrayContentProvider;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

public class WaqtSalatView extends ViewPart implements IPropertyChangeListener {

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

    private Button               buttonSetCity;

    private CLabel               labelSelectedtimezone;
    private Composite            customContentsComposite;

    private Button               buttonGetAutomaticCity;

    private Group                groupSearchCity;

    /** The specified date for which to show the pray times. */
    private Calendar             currentDate;
    private TableColumn          tblclmnName;
    private TableViewerColumn    tableViewerColumn_1;
    private TableColumn          tblclmnHour;
    private TableViewerColumn    tableViewerColumn_2;

    public WaqtSalatView() {
    }

    @Override
    public void init(IViewSite site) throws PartInitException {
        super.init(site);
        setContentDescription("WaqtSalat View ...");
        WaqtSalatPreferencePlugin.getDefault().getPreferenceStore().addPropertyChangeListener(this);
        this.initLuceneCitiesIndex();
        updateCurrentDate();
    }

    @Override
    public void dispose() {
        WaqtSalatPreferencePlugin.getDefault().getPreferenceStore().removePropertyChangeListener(this);
        super.dispose();
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
                locationComposite.setLayout(new GridLayout(1, false));
                locationComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
                {
                    labelSelectedCity = new CLabel(locationComposite, SWT.NONE);
                    labelSelectedCity.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
                    updateLabelSelectedCity(getCityFromPreference());
                }
                {
                    labelSelectedtimezone = new CLabel(locationComposite, SWT.NONE);
                    labelSelectedtimezone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
                    updateLabelSelectedTimezone();
                }
                {
                    buttonGetAutomaticCity = new Button(locationComposite, SWT.CHECK);
                    buttonGetAutomaticCity.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
                    initButtonGetAutomaticCity();
                }
                {
                    groupSearchCity = new Group(leftComposite, SWT.NONE);
                    groupSearchCity.setLayout(new GridLayout(2, false));
                    groupSearchCity.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
                    initGroupSearchCity();
                }
            }
            {
                customContentsComposite = new Composite(leftComposite, SWT.NONE);
                customContentsComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
            }

        }
        {
            Composite rightComposite = new Composite(container, SWT.NONE);
            rightComposite.setLayout(new GridLayout(1, false));
            rightComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
            {
                dateTime = new DateTime(rightComposite, SWT.BORDER | SWT.DROP_DOWN | SWT.LONG);
                dateTime.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
                initDateTimeComponent();
            }
            {
                tableViewer = new TableViewer(rightComposite, SWT.BORDER);
                table = tableViewer.getTable();
                table.setLinesVisible(true);
                table.setHeaderVisible(true);

                int desiredHeight = table.getItemHeight() * 4 + table.getHeaderHeight();
                GridData doubleColumnGridData = new GridData(200, desiredHeight);
                doubleColumnGridData.grabExcessHorizontalSpace = true;
                doubleColumnGridData.grabExcessVerticalSpace = false;
                doubleColumnGridData.horizontalAlignment = SWT.FILL;
                doubleColumnGridData.verticalAlignment = SWT.FILL;
                doubleColumnGridData.horizontalSpan = 2;
                table.setLayoutData(doubleColumnGridData);

                {
                    tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
                    tblclmnName = tableViewerColumn_1.getColumn();
                    tblclmnName.setWidth(100);
                    tblclmnName.setText("name");
                }
                {
                    tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
                    tblclmnHour = tableViewerColumn_2.getColumn();
                    tblclmnHour.setWidth(100);
                    tblclmnHour.setText("hour");
                }
            }
        }

        hookComponentsEnabled();

        getSite().setSelectionProvider(searchBox.getTableViewer());

        createActions();
        contributeToActionBars();
    }

    private void hookComponentsEnabled() {
        searchBox.setEnabled(!buttonGetAutomaticCity.getSelection());
        buttonSetCity.setEnabled(!buttonGetAutomaticCity.getSelection());
    }

    private void initButtonGetAutomaticCity() {
        buttonGetAutomaticCity.setText("Get the location automatically (Need internet connection !)");
        boolean prefValue = getPrefStore().getBoolean(WaqtSalatPreferenceConstants.P_GET_LOCATION_FROM_IP_ADDRESS);
        buttonGetAutomaticCity.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                boolean selection = buttonGetAutomaticCity.getSelection();
                getPrefStore().setValue(WaqtSalatPreferenceConstants.P_GET_LOCATION_FROM_IP_ADDRESS, selection);
                try {
                    getPrefStore().save();
                    hookComponentsEnabled();
                } catch (IOException ioe) {
                    logger.error(
                            "Error while saving preference value for '" //$NON-NLS-1$
                                    + WaqtSalatPreferenceConstants.P_GET_LOCATION_FROM_IP_ADDRESS
                                    + "' : " + ioe.getMessage(), ioe); //$NON-NLS-1$
                }
            }
        });
        buttonGetAutomaticCity.setSelection(prefValue);
    }

    /**
     * @param city - may be <code>null</code>.
     */
    private void updateLabelSelectedCity(final City city) {
        labelSelectedCity.setText(buildLabelForCity(city));
        labelSelectedCity.setToolTipText("Represents the current city for which the pray times will be provided.");
        final String countryCode = city == null ? "-" : city.getCountry().getCode(); //$NON-NLS-1$
        labelSelectedCity.setImage(WaqtSalatUIHelper.getFlagForCountryCode(countryCode));
    }

    private void updateLabelSelectedTimezone() {
        TimeZone tz = getTimezoneFromPreference();
        labelSelectedtimezone.setText(tz.getID());
        labelSelectedtimezone.setImage(WaqtSalatUIPlugin.getImageRegistry().get(ICONS.KEY.TIMEZONE));
        labelSelectedtimezone
                .setToolTipText("Represents the current timezone for which the pray times calculation is based onto.");
    }

    private void initGroupSearchCity() {
        groupSearchCity.setText("Search a city");
        groupSearchCity
                .setToolTipText("Search a city from the database and pick the desired one for which it is necessary to compute and display the pray times.");
        this.initSearchBox(groupSearchCity);
        this.initButtonSetCity(groupSearchCity);
    }

    private void initSearchBox(Composite parent) {
        searchBox = new SearchBox(parent, SWT.NONE);
        searchBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        initTableColumns(searchBox.getTableViewer());
        searchBox.getTableViewer().setLabelProvider(new CityTableLabelProvider());
        searchBox.getTableViewer().setContentProvider(ArrayContentProvider.getInstance());
        searchBox.setInputPolicyRule(new InputPolicyRuleImpl(searchBox));
        searchBox.getTableViewer().setComparator(new CityTableViewerComparator());
    }

    private void initButtonSetCity(final Composite parent) {
        buttonSetCity = new Button(parent, SWT.NONE);
        buttonSetCity.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
        buttonSetCity.setText("Select");
        buttonSetCity.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ISelection searchSelection = searchBox.getTableViewer().getSelection();
                if (searchSelection instanceof IStructuredSelection) {
                    Object selectedElement = ((IStructuredSelection) searchSelection).getFirstElement();
                    if (selectedElement instanceof City) {
                        City selectedCity = (City) selectedElement;
                        updateLabelSelectedCity(selectedCity);
                        saveCityPreference(selectedCity);
                    }
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
            logger.error("Error while saving the city preference : " + e.getMessage(), e); //$NON-NLS-1$
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

    private void initDateTimeComponent() {
        dateTime.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int year = dateTime.getYear();
                int month = dateTime.getMonth();
                int day = dateTime.getDay();
                currentDate.set(year, month, day);
            }
        });
    }

    /**
     * @return The TimeZone "computed" from preference settings. If the TimeZone could be retrieved correctly, the
     *         system TimeZone is resulted.
     */
    private TimeZone getTimezoneFromPreference() {

        TimeZone result = null;

        boolean getTimezoneFromCountry = getPrefStore().getBoolean(
                WaqtSalatPreferenceConstants.P_GET_TIMEZONE_FROM_COUNTRY);
        boolean useSystemTimeZone = getPrefStore().getBoolean(WaqtSalatPreferenceConstants.P_USE_SYSTEM_TIMEZONE);

        if (getTimezoneFromCountry) {
            String countryCode = getCityFromPreference().getCountry().getCode();
            Collection<TimeZone> possibleTimezones = TimeZoneWrapper.getTimezonesFromCountryCode(countryCode);
            if (!possibleTimezones.isEmpty()) {
                result = possibleTimezones.iterator().next();
            }

        } else if (useSystemTimeZone) {
            result = TimeZone.getDefault();

        } else {
            String prefTimezoneID = getPrefStore().getString(WaqtSalatPreferenceConstants.P_TIMEZONE);
            result = TimeZone.getTimeZone(prefTimezoneID);
        }

        return result == null ? TimeZone.getDefault() : result;
    }

    private void initTableColumns(TableViewer tableViewer) {
        String[] columnNames = new String[] { "Country", "City", "Region", "Postal Code" };
        int[] columnWidth = new int[] { 150, 250, 100, 100 };
        for (int i = 0; i < columnNames.length; i++) {
            TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.None);
            TableColumn tableColumn = tableViewerColumn.getColumn();
            tableColumn.setText(columnNames[i]);
            tableColumn.setWidth(columnWidth[i]);
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
            logger.error("Error while creating Lucene cities index.", e); //$NON-NLS-1$
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
                if (enteredText.trim().length() >= 2) {
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

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        updateLabelSelectedTimezone();
        updateCurrentDate();
    }

    /**
     * Initializes the current date for which to show pray time or update its timezone if ever the preference timezone
     * value changes.
     */
    private void updateCurrentDate() {
        if (currentDate == null) {
            currentDate = Calendar.getInstance(getTimezoneFromPreference());
        } else {
            currentDate.setTimeZone(getTimezoneFromPreference());
        }
    }

}
