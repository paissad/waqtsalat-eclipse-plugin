/**
 */
package net.paissad.waqtsalat.locationsprovider.api;

import java.lang.Comparable;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Coordinates</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link net.paissad.waqtsalat.locationsprovider.api.Coordinates#getLatitude <em>Latitude</em>}</li>
 * <li>{@link net.paissad.waqtsalat.locationsprovider.api.Coordinates#getLongitude <em>Longitude</em>}</li>
 * </ul>
 * </p>
 * 
 * @see net.paissad.waqtsalat.locationsprovider.LocationsProviderPackage#getCoordinates()
 * @model superTypes=
 *        "net.paissad.waqtsalat.locationsprovider.api.Comparable<net.paissad.waqtsalat.locationsprovider.api.Coordinates>"
 * @generated
 */
public interface Coordinates extends EObject, Comparable<Coordinates> {
    /**
     * Returns the value of the '<em><b>Latitude</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Latitude</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Latitude</em>' attribute.
     * @see #setLatitude(double)
     * @see net.paissad.waqtsalat.locationsprovider.LocationsProviderPackage#getCoordinates_Latitude()
     * @model
     * @generated
     */
    double getLatitude();

    /**
     * Sets the value of the ' {@link net.paissad.waqtsalat.locationsprovider.api.Coordinates#getLatitude
     * <em>Latitude</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Latitude</em>' attribute.
     * @see #getLatitude()
     * @generated
     */
    void setLatitude(double value);

    /**
     * Returns the value of the '<em><b>Longitude</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Longitude</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Longitude</em>' attribute.
     * @see #setLongitude(double)
     * @see net.paissad.waqtsalat.locationsprovider.LocationsProviderPackage#getCoordinates_Longitude()
     * @model
     * @generated
     */
    double getLongitude();

    /**
     * Sets the value of the ' {@link net.paissad.waqtsalat.locationsprovider.api.Coordinates#getLongitude
     * <em>Longitude</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Longitude</em>' attribute.
     * @see #getLongitude()
     * @generated
     */
    void setLongitude(double value);

} // Coordinates
