/**
 */
package net.paissad.waqtsalat.ui.providers;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import net.paissad.waqtsalat.core.WaqtSalatPackage;
import net.paissad.waqtsalat.core.api.Pray;
import net.paissad.waqtsalat.core.api.PrayName;
import net.paissad.waqtsalat.ui.WaqtSalatUIConstants.ICONS;
import net.paissad.waqtsalat.ui.WaqtSalatUIPlugin;
import net.paissad.waqtsalat.ui.helpers.PreferenceHelper;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemColorProvider;
import org.eclipse.emf.edit.provider.IItemFontProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITableItemColorProvider;
import org.eclipse.emf.edit.provider.ITableItemFontProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link net.paissad.waqtsalat.core.api.Pray} object. <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class PrayItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
        IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource,
        ITableItemLabelProvider, ITableItemColorProvider, ITableItemFontProvider, IItemColorProvider, IItemFontProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PrayItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addNamePropertyDescriptor(object);
            addTimePropertyDescriptor(object);
            addPlayingAdhanPropertyDescriptor(object);
            addAdhanPlayerPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Name feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Pray_name_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_Pray_name_feature", "_UI_Pray_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                WaqtSalatPackage.Literals.PRAY__NAME, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                null, null));
    }

    /**
     * This adds a property descriptor for the Time feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addTimePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Pray_time_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_Pray_time_feature", "_UI_Pray_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                WaqtSalatPackage.Literals.PRAY__TIME, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                null, null));
    }

    /**
     * This adds a property descriptor for the Playing Adhan feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addPlayingAdhanPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Pray_playingAdhan_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_Pray_playingAdhan_feature", "_UI_Pray_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                WaqtSalatPackage.Literals.PRAY__PLAYING_ADHAN, true, false, false,
                ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Adhan Player feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addAdhanPlayerPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_Pray_adhanPlayer_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_Pray_adhanPlayer_feature", "_UI_Pray_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                WaqtSalatPackage.Literals.PRAY__ADHAN_PLAYER, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This returns Pray.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Pray")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getText(Object object) {
        PrayName labelValue = ((Pray) object).getName();
        String label = labelValue == null ? null : labelValue.toString();
        return label == null || label.length() == 0 ? getString("_UI_Pray_type") : //$NON-NLS-1$
                getString("_UI_Pray_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached children and by creating
     * a viewer notification, which it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(Pray.class)) {
            case WaqtSalatPackage.PRAY__NAME:
            case WaqtSalatPackage.PRAY__TIME:
            case WaqtSalatPackage.PRAY__PLAYING_ADHAN:
            case WaqtSalatPackage.PRAY__ADHAN_PLAYER:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that can be created
     * under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }

    /**
     * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return WaqtSalatUIPlugin.INSTANCE;
    }

    @Override
    public String getColumnText(Object object, int columnIndex) {
        if (object instanceof Pray) {
            Pray pray = (Pray) object;
            switch (columnIndex) {
                case 0:
                    return pray.getName().getLiteral();
                case 1:
                    String hour = String.format("%02d", pray.getTime().get(Calendar.HOUR_OF_DAY)); //$NON-NLS-1$
                    String minutes = String.format("%02d", pray.getTime().get(Calendar.MINUTE)); //$NON-NLS-1$
                    return hour + ":" + minutes; //$NON-NLS-1$
                default:
                    break;
            }
        }
        return super.getColumnText(object, columnIndex);
    }

    @Override
    public Object getColumnImage(Object object, int columnIndex) {
        if (object instanceof Pray) {
            Pray pray = (Pray) object;
            switch (columnIndex) {
                case 0:
                    if (PrayName.SUNRISE.equals(pray.getName()) || PrayName.SUNSET.equals(pray.getName())) {
                        return WaqtSalatUIPlugin.getImageDescriptor(ICONS.PATH.YELLOW_POINT);

                    } else if (pray.isPlayingAdhan()) {
                        return WaqtSalatUIPlugin.getImageDescriptor(ICONS.PATH.TERMINATE);

                    } else if (isIncomingPray(pray)) {
                        return WaqtSalatUIPlugin.getImageDescriptor(ICONS.PATH.GREEN_POINT);
                    }

                    return WaqtSalatUIPlugin.getImageDescriptor(ICONS.PATH.GRAY_POINT);

                default:
                    break;
            }
        }
        return super.getColumnImage(object, columnIndex);
    }

    private static boolean isIncomingPray(final Pray currentPray) {
        boolean incomingPray = false;
        PrayName prayName = currentPray.getName();
        if (!(PrayName.SUNRISE.equals(prayName) || PrayName.SUNSET.equals(prayName))) {
            Calendar cal = Calendar.getInstance(PreferenceHelper.getTimezoneFromPreference());
            Calendar currentPrayTime = currentPray.getTime();
            incomingPray = cal.before(currentPrayTime);
        }
        return incomingPray;
    }
}
