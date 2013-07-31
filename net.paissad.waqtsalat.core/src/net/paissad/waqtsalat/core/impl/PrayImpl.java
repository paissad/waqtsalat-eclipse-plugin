/**
 */
package net.paissad.waqtsalat.core.impl;

import java.util.Calendar;
import net.paissad.waqtsalat.core.WaqtSalatPackage;
import net.paissad.waqtsalat.core.api.Pray;
import net.paissad.waqtsalat.core.api.PrayName;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Pray</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link net.paissad.waqtsalat.core.impl.PrayImpl#getName <em>Name</em>}</li>
 * <li>{@link net.paissad.waqtsalat.core.impl.PrayImpl#getTime <em>Time</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PrayImpl extends MinimalEObjectImpl.Container implements Pray {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final PrayName NAME_EDEFAULT = PrayName.FADJR;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected PrayName              name          = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getTime() <em>Time</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getTime()
     * @generated
     * @ordered
     */
    protected static final Calendar TIME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTime() <em>Time</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getTime()
     * @generated
     * @ordered
     */
    protected Calendar              time          = TIME_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected PrayImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return WaqtSalatPackage.Literals.PRAY;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PrayName getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setName(PrayName newName) {
        PrayName oldName = name;
        name = newName == null ? NAME_EDEFAULT : newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, WaqtSalatPackage.PRAY__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Calendar getTime() {
        return time;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTime(Calendar newTime) {
        Calendar oldTime = time;
        time = newTime;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, WaqtSalatPackage.PRAY__TIME, oldTime, time));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case WaqtSalatPackage.PRAY__NAME:
                return getName();
            case WaqtSalatPackage.PRAY__TIME:
                return getTime();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case WaqtSalatPackage.PRAY__NAME:
                setName((PrayName) newValue);
                return;
            case WaqtSalatPackage.PRAY__TIME:
                setTime((Calendar) newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case WaqtSalatPackage.PRAY__NAME:
                setName(NAME_EDEFAULT);
                return;
            case WaqtSalatPackage.PRAY__TIME:
                setTime(TIME_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case WaqtSalatPackage.PRAY__NAME:
                return name != NAME_EDEFAULT;
            case WaqtSalatPackage.PRAY__TIME:
                return TIME_EDEFAULT == null ? time != null : !TIME_EDEFAULT.equals(time);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", time: "); //$NON-NLS-1$
        result.append(time);
        result.append(')');
        return result.toString();
    }

} // PrayImpl
