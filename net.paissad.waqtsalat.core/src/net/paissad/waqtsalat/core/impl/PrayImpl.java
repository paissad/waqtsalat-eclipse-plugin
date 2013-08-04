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
 * <li>{@link net.paissad.waqtsalat.core.impl.PrayImpl#isPlayingAdhan <em>Playing Adhan</em>}</li>
 * <li>{@link net.paissad.waqtsalat.core.impl.PrayImpl#getAdhanPlayer <em>Adhan Player</em>}</li>
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
    protected static final PrayName NAME_EDEFAULT          = PrayName.FADJR;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected PrayName              name                   = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getTime() <em>Time</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getTime()
     * @generated
     * @ordered
     */
    protected static final Calendar TIME_EDEFAULT          = null;

    /**
     * The cached value of the '{@link #getTime() <em>Time</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getTime()
     * @generated
     * @ordered
     */
    protected Calendar              time                   = TIME_EDEFAULT;

    /**
     * The default value of the '{@link #isPlayingAdhan() <em>Playing Adhan</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isPlayingAdhan()
     * @generated
     * @ordered
     */
    protected static final boolean  PLAYING_ADHAN_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isPlayingAdhan() <em>Playing Adhan</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isPlayingAdhan()
     * @generated
     * @ordered
     */
    protected boolean               playingAdhan           = PLAYING_ADHAN_EDEFAULT;

    /**
     * The default value of the '{@link #getAdhanPlayer() <em>Adhan Player</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getAdhanPlayer()
     * @generated
     * @ordered
     */
    protected static final Object   ADHAN_PLAYER_EDEFAULT  = null;

    /**
     * The cached value of the '{@link #getAdhanPlayer() <em>Adhan Player</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getAdhanPlayer()
     * @generated
     * @ordered
     */
    protected Object                adhanPlayer            = ADHAN_PLAYER_EDEFAULT;

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
    public boolean isPlayingAdhan() {
        return playingAdhan;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setPlayingAdhan(boolean newPlayingAdhan) {
        boolean oldPlayingAdhan = playingAdhan;
        playingAdhan = newPlayingAdhan;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, WaqtSalatPackage.PRAY__PLAYING_ADHAN,
                    oldPlayingAdhan, playingAdhan));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Object getAdhanPlayer() {
        return adhanPlayer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setAdhanPlayer(Object newAdhanPlayer) {
        Object oldAdhanPlayer = adhanPlayer;
        adhanPlayer = newAdhanPlayer;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, WaqtSalatPackage.PRAY__ADHAN_PLAYER, oldAdhanPlayer,
                    adhanPlayer));
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
            case WaqtSalatPackage.PRAY__PLAYING_ADHAN:
                return isPlayingAdhan();
            case WaqtSalatPackage.PRAY__ADHAN_PLAYER:
                return getAdhanPlayer();
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
            case WaqtSalatPackage.PRAY__PLAYING_ADHAN:
                setPlayingAdhan((Boolean) newValue);
                return;
            case WaqtSalatPackage.PRAY__ADHAN_PLAYER:
                setAdhanPlayer(newValue);
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
            case WaqtSalatPackage.PRAY__PLAYING_ADHAN:
                setPlayingAdhan(PLAYING_ADHAN_EDEFAULT);
                return;
            case WaqtSalatPackage.PRAY__ADHAN_PLAYER:
                setAdhanPlayer(ADHAN_PLAYER_EDEFAULT);
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
            case WaqtSalatPackage.PRAY__PLAYING_ADHAN:
                return playingAdhan != PLAYING_ADHAN_EDEFAULT;
            case WaqtSalatPackage.PRAY__ADHAN_PLAYER:
                return ADHAN_PLAYER_EDEFAULT == null ? adhanPlayer != null : !ADHAN_PLAYER_EDEFAULT.equals(adhanPlayer);
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
        result.append(", playingAdhan: "); //$NON-NLS-1$
        result.append(playingAdhan);
        result.append(", adhanPlayer: "); //$NON-NLS-1$
        result.append(adhanPlayer);
        result.append(')');
        return result.toString();
    }

} // PrayImpl
