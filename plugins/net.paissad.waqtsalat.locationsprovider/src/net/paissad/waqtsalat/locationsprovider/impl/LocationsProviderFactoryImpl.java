/**
 */
package net.paissad.waqtsalat.locationsprovider.impl;

import net.paissad.waqtsalat.locationsprovider.LocationsProviderFactory;
import net.paissad.waqtsalat.locationsprovider.LocationsProviderPackage;

import net.paissad.waqtsalat.locationsprovider.api.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class LocationsProviderFactoryImpl extends EFactoryImpl implements LocationsProviderFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static LocationsProviderFactory init() {
        try {
            LocationsProviderFactory theLocationsProviderFactory = (LocationsProviderFactory) EPackage.Registry.INSTANCE
                    .getEFactory(LocationsProviderPackage.eNS_URI);
            if (theLocationsProviderFactory != null) {
                return theLocationsProviderFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new LocationsProviderFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public LocationsProviderFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case LocationsProviderPackage.COUNTRY:
                return createCountry();
            case LocationsProviderPackage.CITY:
                return createCity();
            case LocationsProviderPackage.COORDINATES:
                return createCoordinates();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Country createCountry() {
        CountryImpl country = new CountryImpl();
        return country;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public City createCity() {
        CityImpl city = new CityImpl();
        return city;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Coordinates createCoordinates() {
        CoordinatesImpl coordinates = new CoordinatesImpl();
        return coordinates;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public LocationsProviderPackage getLocationsProviderPackage() {
        return (LocationsProviderPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static LocationsProviderPackage getPackage() {
        return LocationsProviderPackage.eINSTANCE;
    }

} // LocationsProviderFactoryImpl
