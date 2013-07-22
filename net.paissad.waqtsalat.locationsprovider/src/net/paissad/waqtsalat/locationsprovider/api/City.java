/**
 */
package net.paissad.waqtsalat.locationsprovider.api;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>City</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link net.paissad.waqtsalat.locationsprovider.api.City#getName <em>Name</em>}</li>
 * <li>{@link net.paissad.waqtsalat.locationsprovider.api.City#getCountry <em>Country</em>}</li>
 * <li>{@link net.paissad.waqtsalat.locationsprovider.api.City#getCoordinates <em>Coordinates</em>}</li>
 * <li>{@link net.paissad.waqtsalat.locationsprovider.api.City#getRegion <em>Region</em>}</li>
 * </ul>
 * </p>
 * 
 * @see net.paissad.waqtsalat.locationsprovider.LocationsProviderPackage#getCity()
 * @model
 * @generated
 */
public interface City extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see net.paissad.waqtsalat.locationsprovider.LocationsProviderPackage#getCity_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link net.paissad.waqtsalat.locationsprovider.api.City#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Country</b></em>' container reference. It is bidirectional and its opposite is '
     * {@link net.paissad.waqtsalat.locationsprovider.api.Country#getCities <em>Cities</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Country</em>' container reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Country</em>' container reference.
     * @see #setCountry(Country)
     * @see net.paissad.waqtsalat.locationsprovider.LocationsProviderPackage#getCity_Country()
     * @see net.paissad.waqtsalat.locationsprovider.api.Country#getCities
     * @model opposite="cities" transient="false"
     * @generated
     */
    Country getCountry();

    /**
     * Sets the value of the '{@link net.paissad.waqtsalat.locationsprovider.api.City#getCountry <em>Country</em>}'
     * container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Country</em>' container reference.
     * @see #getCountry()
     * @generated
     */
    void setCountry(Country value);

    /**
     * Returns the value of the '<em><b>Coordinates</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Coordinates</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Coordinates</em>' reference.
     * @see #setCoordinates(Coordinates)
     * @see net.paissad.waqtsalat.locationsprovider.LocationsProviderPackage#getCity_Coordinates()
     * @model
     * @generated
     */
    Coordinates getCoordinates();

    /**
     * Sets the value of the '{@link net.paissad.waqtsalat.locationsprovider.api.City#getCoordinates
     * <em>Coordinates</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Coordinates</em>' reference.
     * @see #getCoordinates()
     * @generated
     */
    void setCoordinates(Coordinates value);

    /**
     * Returns the value of the '<em><b>Region</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Region</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Region</em>' attribute.
     * @see #setRegion(String)
     * @see net.paissad.waqtsalat.locationsprovider.LocationsProviderPackage#getCity_Region()
     * @model
     * @generated
     */
    String getRegion();

    /**
     * Sets the value of the '{@link net.paissad.waqtsalat.locationsprovider.api.City#getRegion <em>Region</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Region</em>' attribute.
     * @see #getRegion()
     * @generated
     */
    void setRegion(String value);

} // City
