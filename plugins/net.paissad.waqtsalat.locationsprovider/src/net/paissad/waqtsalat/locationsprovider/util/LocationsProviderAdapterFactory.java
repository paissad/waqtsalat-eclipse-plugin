/**
 */
package net.paissad.waqtsalat.locationsprovider.util;

import net.paissad.waqtsalat.locationsprovider.LocationsProviderPackage;

import net.paissad.waqtsalat.locationsprovider.api.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code>
 * method for each class of the model. <!-- end-user-doc -->
 * 
 * @see net.paissad.waqtsalat.locationsprovider.LocationsProviderPackage
 * @generated
 */
public class LocationsProviderAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static LocationsProviderPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public LocationsProviderAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = LocationsProviderPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc --> This
     * implementation returns <code>true</code> if the object is either the model's package or is an instance object of
     * the model. <!-- end-user-doc -->
     * 
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected LocationsProviderSwitch<Adapter> modelSwitch = new LocationsProviderSwitch<Adapter>() {
                                                               @Override
                                                               public Adapter caseCountry(Country object) {
                                                                   return createCountryAdapter();
                                                               }

                                                               @Override
                                                               public Adapter caseCity(City object) {
                                                                   return createCityAdapter();
                                                               }

                                                               @Override
                                                               public Adapter caseCoordinates(Coordinates object) {
                                                                   return createCoordinatesAdapter();
                                                               }

                                                               @Override
                                                               public <E> Adapter caseComparable(Comparable<E> object) {
                                                                   return createComparableAdapter();
                                                               }

                                                               @Override
                                                               public Adapter defaultCase(EObject object) {
                                                                   return createEObjectAdapter();
                                                               }
                                                           };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class ' {@link net.paissad.waqtsalat.locationsprovider.api.Country
     * <em>Country</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see net.paissad.waqtsalat.locationsprovider.api.Country
     * @generated
     */
    public Adapter createCountryAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.paissad.waqtsalat.locationsprovider.api.City
     * <em>City</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see net.paissad.waqtsalat.locationsprovider.api.City
     * @generated
     */
    public Adapter createCityAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.paissad.waqtsalat.locationsprovider.api.Coordinates
     * <em>Coordinates</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     * 
     * @return the new adapter.
     * @see net.paissad.waqtsalat.locationsprovider.api.Coordinates
     * @generated
     */
    public Adapter createCoordinatesAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link java.lang.Comparable <em>Comparable</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see java.lang.Comparable
     * @generated
     */
    public Adapter createComparableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns null.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // LocationsProviderAdapterFactory
