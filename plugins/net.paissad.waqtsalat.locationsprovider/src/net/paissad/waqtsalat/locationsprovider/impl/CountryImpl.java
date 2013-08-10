/**
 */
package net.paissad.waqtsalat.locationsprovider.impl;

import java.util.Collection;

import net.paissad.waqtsalat.locationsprovider.LocationsProviderPackage;
import net.paissad.waqtsalat.locationsprovider.api.City;
import net.paissad.waqtsalat.locationsprovider.api.Country;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Country</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link net.paissad.waqtsalat.locationsprovider.impl.CountryImpl#getName <em>Name</em>}</li>
 * <li>{@link net.paissad.waqtsalat.locationsprovider.impl.CountryImpl#getCities <em>Cities</em>}</li>
 * <li>{@link net.paissad.waqtsalat.locationsprovider.impl.CountryImpl#getCode <em>Code</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class CountryImpl extends MinimalEObjectImpl.Container implements Country {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String              name          = NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getCities() <em>Cities</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getCities()
     * @generated
     * @ordered
     */
    protected EList<City>         cities;

    /**
     * The default value of the '{@link #getCode() <em>Code</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getCode()
     * @generated
     * @ordered
     */
    protected static final String CODE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCode() <em>Code</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getCode()
     * @generated
     * @ordered
     */
    protected String              code          = CODE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected CountryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return LocationsProviderPackage.Literals.COUNTRY;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LocationsProviderPackage.COUNTRY__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<City> getCities() {
        if (cities == null) {
            cities = new EObjectContainmentWithInverseEList<City>(City.class, this,
                    LocationsProviderPackage.COUNTRY__CITIES, LocationsProviderPackage.CITY__COUNTRY);
        }
        return cities;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getCode() {
        return code;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setCode(String newCode) {
        String oldCode = code;
        code = newCode;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LocationsProviderPackage.COUNTRY__CODE, oldCode, code));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case LocationsProviderPackage.COUNTRY__CITIES:
                return ((InternalEList<InternalEObject>) (InternalEList<?>) getCities()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case LocationsProviderPackage.COUNTRY__CITIES:
                return ((InternalEList<?>) getCities()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case LocationsProviderPackage.COUNTRY__NAME:
                return getName();
            case LocationsProviderPackage.COUNTRY__CITIES:
                return getCities();
            case LocationsProviderPackage.COUNTRY__CODE:
                return getCode();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case LocationsProviderPackage.COUNTRY__NAME:
                setName((String) newValue);
                return;
            case LocationsProviderPackage.COUNTRY__CITIES:
                getCities().clear();
                getCities().addAll((Collection<? extends City>) newValue);
                return;
            case LocationsProviderPackage.COUNTRY__CODE:
                setCode((String) newValue);
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
            case LocationsProviderPackage.COUNTRY__NAME:
                setName(NAME_EDEFAULT);
                return;
            case LocationsProviderPackage.COUNTRY__CITIES:
                getCities().clear();
                return;
            case LocationsProviderPackage.COUNTRY__CODE:
                setCode(CODE_EDEFAULT);
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
            case LocationsProviderPackage.COUNTRY__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case LocationsProviderPackage.COUNTRY__CITIES:
                return cities != null && !cities.isEmpty();
            case LocationsProviderPackage.COUNTRY__CODE:
                return CODE_EDEFAULT == null ? code != null : !CODE_EDEFAULT.equals(code);
        }
        return super.eIsSet(featureID);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.cities == null) ? 0 : this.cities.hashCode());
        result = prime * result + ((this.code == null) ? 0 : this.code.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        CountryImpl other = (CountryImpl) obj;
        if (this.cities == null) {
            if (other.cities != null) return false;
        } else if (!this.cities.equals(other.cities)) return false;
        if (this.code == null) {
            if (other.code != null) return false;
        } else if (!this.code.equals(other.code)) return false;
        if (this.name == null) {
            if (other.name != null) return false;
        } else if (!this.name.equals(other.name)) return false;
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
        result.append(" (name: ");
        result.append(name);
        result.append(", code: ");
        result.append(code);
        result.append(')');
        return result.toString();
    }

    @Override
    public int compareTo(Country o) {
        int result = this.getCode().compareTo(o.getCode());
        if (result == 0) {
            result = this.getName().compareTo(o.getName());
        }
        return result;
    }

} // CountryImpl
