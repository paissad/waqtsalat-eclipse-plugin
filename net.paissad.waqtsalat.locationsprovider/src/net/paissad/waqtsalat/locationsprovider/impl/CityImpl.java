/**
 */
package net.paissad.waqtsalat.locationsprovider.impl;

import net.paissad.waqtsalat.locationsprovider.LocationsProviderPackage;
import net.paissad.waqtsalat.locationsprovider.api.City;
import net.paissad.waqtsalat.locationsprovider.api.Coordinates;
import net.paissad.waqtsalat.locationsprovider.api.Country;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>City</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link net.paissad.waqtsalat.locationsprovider.impl.CityImpl#getName <em>Name</em>}</li>
 * <li>{@link net.paissad.waqtsalat.locationsprovider.impl.CityImpl#getCountry <em>Country</em>}</li>
 * <li>{@link net.paissad.waqtsalat.locationsprovider.impl.CityImpl#getCoordinates <em>Coordinates</em>}</li>
 * <li>{@link net.paissad.waqtsalat.locationsprovider.impl.CityImpl#getRegion <em>Region</em>}</li>
 * <li>{@link net.paissad.waqtsalat.locationsprovider.impl.CityImpl#getPostalCode <em>Postal Code</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class CityImpl extends MinimalEObjectImpl.Container implements City {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT        = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String              name                 = NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getCoordinates() <em>Coordinates</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getCoordinates()
     * @generated
     * @ordered
     */
    protected Coordinates         coordinates;

    /**
     * The default value of the '{@link #getRegion() <em>Region</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getRegion()
     * @generated
     * @ordered
     */
    protected static final String REGION_EDEFAULT      = null;

    /**
     * The cached value of the '{@link #getRegion() <em>Region</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getRegion()
     * @generated
     * @ordered
     */
    protected String              region               = REGION_EDEFAULT;

    /**
     * The default value of the '{@link #getPostalCode() <em>Postal Code</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getPostalCode()
     * @generated
     * @ordered
     */
    protected static final String POSTAL_CODE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPostalCode() <em>Postal Code</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getPostalCode()
     * @generated
     * @ordered
     */
    protected String              postalCode           = POSTAL_CODE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected CityImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return LocationsProviderPackage.Literals.CITY;
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
            eNotify(new ENotificationImpl(this, Notification.SET, LocationsProviderPackage.CITY__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Country getCountry() {
        if (eContainerFeatureID() != LocationsProviderPackage.CITY__COUNTRY) return null;
        return (Country) eInternalContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetCountry(Country newCountry, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject) newCountry, LocationsProviderPackage.CITY__COUNTRY, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setCountry(Country newCountry) {
        if (newCountry != eInternalContainer()
                || (eContainerFeatureID() != LocationsProviderPackage.CITY__COUNTRY && newCountry != null)) {
            if (EcoreUtil.isAncestor(this, newCountry))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null) msgs = eBasicRemoveFromContainer(msgs);
            if (newCountry != null)
                msgs = ((InternalEObject) newCountry).eInverseAdd(this, LocationsProviderPackage.COUNTRY__CITIES,
                        Country.class, msgs);
            msgs = basicSetCountry(newCountry, msgs);
            if (msgs != null) msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LocationsProviderPackage.CITY__COUNTRY, newCountry,
                    newCountry));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Coordinates getCoordinates() {
        if (coordinates != null && coordinates.eIsProxy()) {
            InternalEObject oldCoordinates = (InternalEObject) coordinates;
            coordinates = (Coordinates) eResolveProxy(oldCoordinates);
            if (coordinates != oldCoordinates) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            LocationsProviderPackage.CITY__COORDINATES, oldCoordinates, coordinates));
            }
        }
        return coordinates;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Coordinates basicGetCoordinates() {
        return coordinates;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setCoordinates(Coordinates newCoordinates) {
        Coordinates oldCoordinates = coordinates;
        coordinates = newCoordinates;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LocationsProviderPackage.CITY__COORDINATES,
                    oldCoordinates, coordinates));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getRegion() {
        return region;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setRegion(String newRegion) {
        String oldRegion = region;
        region = newRegion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LocationsProviderPackage.CITY__REGION, oldRegion,
                    region));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setPostalCode(String newPostalCode) {
        String oldPostalCode = postalCode;
        postalCode = newPostalCode;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LocationsProviderPackage.CITY__POSTAL_CODE,
                    oldPostalCode, postalCode));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case LocationsProviderPackage.CITY__COUNTRY:
                if (eInternalContainer() != null) msgs = eBasicRemoveFromContainer(msgs);
                return basicSetCountry((Country) otherEnd, msgs);
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
            case LocationsProviderPackage.CITY__COUNTRY:
                return basicSetCountry(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID()) {
            case LocationsProviderPackage.CITY__COUNTRY:
                return eInternalContainer().eInverseRemove(this, LocationsProviderPackage.COUNTRY__CITIES,
                        Country.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case LocationsProviderPackage.CITY__NAME:
                return getName();
            case LocationsProviderPackage.CITY__COUNTRY:
                return getCountry();
            case LocationsProviderPackage.CITY__COORDINATES:
                if (resolve) return getCoordinates();
                return basicGetCoordinates();
            case LocationsProviderPackage.CITY__REGION:
                return getRegion();
            case LocationsProviderPackage.CITY__POSTAL_CODE:
                return getPostalCode();
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
            case LocationsProviderPackage.CITY__NAME:
                setName((String) newValue);
                return;
            case LocationsProviderPackage.CITY__COUNTRY:
                setCountry((Country) newValue);
                return;
            case LocationsProviderPackage.CITY__COORDINATES:
                setCoordinates((Coordinates) newValue);
                return;
            case LocationsProviderPackage.CITY__REGION:
                setRegion((String) newValue);
                return;
            case LocationsProviderPackage.CITY__POSTAL_CODE:
                setPostalCode((String) newValue);
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
            case LocationsProviderPackage.CITY__NAME:
                setName(NAME_EDEFAULT);
                return;
            case LocationsProviderPackage.CITY__COUNTRY:
                setCountry((Country) null);
                return;
            case LocationsProviderPackage.CITY__COORDINATES:
                setCoordinates((Coordinates) null);
                return;
            case LocationsProviderPackage.CITY__REGION:
                setRegion(REGION_EDEFAULT);
                return;
            case LocationsProviderPackage.CITY__POSTAL_CODE:
                setPostalCode(POSTAL_CODE_EDEFAULT);
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
            case LocationsProviderPackage.CITY__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case LocationsProviderPackage.CITY__COUNTRY:
                return getCountry() != null;
            case LocationsProviderPackage.CITY__COORDINATES:
                return coordinates != null;
            case LocationsProviderPackage.CITY__REGION:
                return REGION_EDEFAULT == null ? region != null : !REGION_EDEFAULT.equals(region);
            case LocationsProviderPackage.CITY__POSTAL_CODE:
                return POSTAL_CODE_EDEFAULT == null ? postalCode != null : !POSTAL_CODE_EDEFAULT.equals(postalCode);
        }
        return super.eIsSet(featureID);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.coordinates == null) ? 0 : this.coordinates.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.postalCode == null) ? 0 : this.postalCode.hashCode());
        result = prime * result + ((this.region == null) ? 0 : this.region.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        CityImpl other = (CityImpl) obj;
        if (this.coordinates == null) {
            if (other.coordinates != null) return false;
        } else if (!this.coordinates.equals(other.coordinates)) return false;
        if (this.name == null) {
            if (other.name != null) return false;
        } else if (!this.name.equals(other.name)) return false;
        if (this.postalCode == null) {
            if (other.postalCode != null) return false;
        } else if (!this.postalCode.equals(other.postalCode)) return false;
        if (this.region == null) {
            if (other.region != null) return false;
        } else if (!this.region.equals(other.region)) return false;
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
        result.append(", region: ");
        result.append(region);
        result.append(", postalCode: ");
        result.append(postalCode);
        result.append(')');
        return result.toString();
    }

} // CityImpl
