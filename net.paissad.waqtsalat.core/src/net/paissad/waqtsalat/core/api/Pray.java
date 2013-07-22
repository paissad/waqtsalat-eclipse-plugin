/**
 */
package net.paissad.waqtsalat.core.api;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Pray</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link net.paissad.waqtsalat.core.api.Pray#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @see net.paissad.waqtsalat.core.WaqtSalatPackage#getPray()
 * @model
 * @generated
 */
public interface Pray extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. The literals are from the enumeration
     * {@link net.paissad.waqtsalat.core.api.PrayName}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see net.paissad.waqtsalat.core.api.PrayName
     * @see #setName(PrayName)
     * @see net.paissad.waqtsalat.core.WaqtSalatPackage#getPray_Name()
     * @model
     * @generated
     */
    PrayName getName();

    /**
     * Sets the value of the '{@link net.paissad.waqtsalat.core.api.Pray#getName <em>Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see net.paissad.waqtsalat.core.api.PrayName
     * @see #getName()
     * @generated
     */
    void setName(PrayName value);

} // Pray
