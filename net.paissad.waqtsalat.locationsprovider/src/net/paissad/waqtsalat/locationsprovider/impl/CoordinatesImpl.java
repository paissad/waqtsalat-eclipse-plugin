/**
 */
package net.paissad.waqtsalat.locationsprovider.impl;

import net.paissad.waqtsalat.locationsprovider.LocationsProviderPackage;
import net.paissad.waqtsalat.locationsprovider.api.Coordinates;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Coordinates</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link net.paissad.waqtsalat.locationsprovider.impl.CoordinatesImpl#getLatitude <em>Latitude</em>}</li>
 * <li>{@link net.paissad.waqtsalat.locationsprovider.impl.CoordinatesImpl#getLongitude <em>Longitude</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class CoordinatesImpl extends MinimalEObjectImpl.Container implements Coordinates {
    /**
     * The default value of the '{@link #getLatitude() <em>Latitude</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLatitude()
     * @generated
     * @ordered
     */
    protected static final double LATITUDE_EDEFAULT  = 0.0;

    /**
     * The cached value of the '{@link #getLatitude() <em>Latitude</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLatitude()
     * @generated
     * @ordered
     */
    protected double              latitude           = LATITUDE_EDEFAULT;

    /**
     * The default value of the '{@link #getLongitude() <em>Longitude</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLongitude()
     * @generated
     * @ordered
     */
    protected static final double LONGITUDE_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getLongitude() <em>Longitude</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLongitude()
     * @generated
     * @ordered
     */
    protected double              longitude          = LONGITUDE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected CoordinatesImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return LocationsProviderPackage.Literals.COORDINATES;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setLatitude(double newLatitude) {
        double oldLatitude = latitude;
        latitude = newLatitude;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LocationsProviderPackage.COORDINATES__LATITUDE,
                    oldLatitude, latitude));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setLongitude(double newLongitude) {
        double oldLongitude = longitude;
        longitude = newLongitude;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LocationsProviderPackage.COORDINATES__LONGITUDE,
                    oldLongitude, longitude));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case LocationsProviderPackage.COORDINATES__LATITUDE:
                return getLatitude();
            case LocationsProviderPackage.COORDINATES__LONGITUDE:
                return getLongitude();
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
            case LocationsProviderPackage.COORDINATES__LATITUDE:
                setLatitude((Double) newValue);
                return;
            case LocationsProviderPackage.COORDINATES__LONGITUDE:
                setLongitude((Double) newValue);
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
            case LocationsProviderPackage.COORDINATES__LATITUDE:
                setLatitude(LATITUDE_EDEFAULT);
                return;
            case LocationsProviderPackage.COORDINATES__LONGITUDE:
                setLongitude(LONGITUDE_EDEFAULT);
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
            case LocationsProviderPackage.COORDINATES__LATITUDE:
                return latitude != LATITUDE_EDEFAULT;
            case LocationsProviderPackage.COORDINATES__LONGITUDE:
                return longitude != LONGITUDE_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(this.latitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(this.longitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        CoordinatesImpl other = (CoordinatesImpl) obj;
        if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude)) return false;
        if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude)) return false;
        return true;
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
        result.append(" (latitude: ");
        result.append(latitude);
        result.append(", longitude: ");
        result.append(longitude);
        result.append(')');
        return result.toString();
    }

} // CoordinatesImpl
