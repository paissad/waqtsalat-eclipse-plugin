/**
 */
package net.paissad.waqtsalat.locationsprovider;

import net.paissad.waqtsalat.locationsprovider.api.City;
import net.paissad.waqtsalat.locationsprovider.api.Coordinates;
import net.paissad.waqtsalat.locationsprovider.api.Country;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 * 
 * @see net.paissad.waqtsalat.locationsprovider.LocationsProviderPackage
 * @generated
 */
public interface LocationsProviderFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    LocationsProviderFactory eINSTANCE = net.paissad.waqtsalat.locationsprovider.impl.LocationsProviderFactoryImpl
                                               .init();

    /**
     * Returns a new object of class '<em>Country</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Country</em>'.
     * @generated
     */
    Country createCountry();

    /**
     * Returns a new object of class '<em>City</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>City</em>'.
     * @generated
     */
    City createCity();

    /**
     * Returns a new object of class '<em>Coordinates</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Coordinates</em>'.
     * @generated
     */
    Coordinates createCoordinates();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the package supported by this factory.
     * @generated
     */
    LocationsProviderPackage getLocationsProviderPackage();

} // LocationsProviderFactory
