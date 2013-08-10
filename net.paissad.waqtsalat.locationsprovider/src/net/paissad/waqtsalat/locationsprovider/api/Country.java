/**
 */
package net.paissad.waqtsalat.locationsprovider.api;

import java.lang.Comparable;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Country</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link net.paissad.waqtsalat.locationsprovider.api.Country#getName <em>Name</em>}</li>
 * <li>{@link net.paissad.waqtsalat.locationsprovider.api.Country#getCities <em>Cities</em>}</li>
 * <li>{@link net.paissad.waqtsalat.locationsprovider.api.Country#getCode <em>Code</em>}</li>
 * </ul>
 * </p>
 * 
 * @see net.paissad.waqtsalat.locationsprovider.LocationsProviderPackage#getCountry()
 * @model superTypes=
 *        "net.paissad.waqtsalat.locationsprovider.api.Comparable<net.paissad.waqtsalat.locationsprovider.api.Country>"
 * @generated
 */
public interface Country extends EObject, Comparable<Country> {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see net.paissad.waqtsalat.locationsprovider.LocationsProviderPackage#getCountry_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link net.paissad.waqtsalat.locationsprovider.api.Country#getName <em>Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Cities</b></em>' containment reference list. The list contents are of type
     * {@link net.paissad.waqtsalat.locationsprovider.api.City}. It is bidirectional and its opposite is '
     * {@link net.paissad.waqtsalat.locationsprovider.api.City#getCountry <em>Country</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Cities</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Cities</em>' containment reference list.
     * @see net.paissad.waqtsalat.locationsprovider.LocationsProviderPackage#getCountry_Cities()
     * @see net.paissad.waqtsalat.locationsprovider.api.City#getCountry
     * @model opposite="country" containment="true"
     * @generated
     */
    EList<City> getCities();

    /**
     * Returns the value of the '<em><b>Code</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Code</em>' attribute isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Code</em>' attribute.
     * @see #setCode(String)
     * @see net.paissad.waqtsalat.locationsprovider.LocationsProviderPackage#getCountry_Code()
     * @model
     * @generated
     */
    String getCode();

    /**
     * Sets the value of the '{@link net.paissad.waqtsalat.locationsprovider.api.Country#getCode <em>Code</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Code</em>' attribute.
     * @see #getCode()
     * @generated
     */
    void setCode(String value);

} // Country
