/**
 */
package net.paissad.waqtsalat.core.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.paissad.waqtsalat.core.WaqtSalatPackage;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EEnumLiteral;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration ' <em><b>Adjusting Method</b></em>', and
 * utility methods for working with them. <!-- end-user-doc -->
 * 
 * @see net.paissad.waqtsalat.core.WaqtSalatPackage#getAdjustingMethod()
 * @model
 * @generated
 */
public enum AdjustingMethod implements Enumerator {
    /**
     * The '<em><b>NONE</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #NONE_VALUE
     * @generated
     * @ordered
     */
    NONE(0, "NONE", "NONE"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>MIDNIGHT</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #MIDNIGHT_VALUE
     * @generated
     * @ordered
     */
    MIDNIGHT(1, "MIDNIGHT", "MIDNIGHT"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>ONE SEVENTH</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #ONE_SEVENTH_VALUE
     * @generated
     * @ordered
     */
    ONE_SEVENTH(2, "ONE_SEVENTH", "ONE_SEVENTH"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>ANGLE BASED</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #ANGLE_BASED_VALUE
     * @generated
     * @ordered
     */
    ANGLE_BASED(3, "ANGLE_BASED", "ANGLE_BASED"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>NONE</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>NONE</b></em>' literal object isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #NONE
     * @model annotation="net.paissad.waqtsalat.core/adjustingmethod/1.0 description='No adjustments'"
     * @generated
     * @ordered
     */
    public static final int                   NONE_VALUE        = 0;

    /**
     * The '<em><b>MIDNIGHT</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>MIDNIGHT</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #MIDNIGHT
     * @model annotation="net.paissad.waqtsalat.core/adjustingmethod/1.0 description='The middle of the night method'"
     * @generated
     * @ordered
     */
    public static final int                   MIDNIGHT_VALUE    = 1;

    /**
     * The '<em><b>ONE SEVENTH</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>ONE SEVENTH</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #ONE_SEVENTH
     * @model annotation="net.paissad.waqtsalat.core/adjustingmethod/1.0 description='The 1/7th of the night method'"
     * @generated
     * @ordered
     */
    public static final int                   ONE_SEVENTH_VALUE = 2;

    /**
     * The '<em><b>ANGLE BASED</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>ANGLE BASED</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #ANGLE_BASED
     * @model 
     *        annotation="net.paissad.waqtsalat.core/adjustingmethod/1.0 description='The angle-based method (recommended)'"
     * @generated
     * @ordered
     */
    public static final int                   ANGLE_BASED_VALUE = 3;

    /**
     * An array of all the '<em><b>Adjusting Method</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final AdjustingMethod[]    VALUES_ARRAY      = new AdjustingMethod[] { NONE, MIDNIGHT, ONE_SEVENTH,
            ANGLE_BASED,                                       };

    /**
     * A public read-only list of all the '<em><b>Adjusting Method</b></em>' enumerators. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static final List<AdjustingMethod> VALUES            = Collections.unmodifiableList(Arrays
                                                                        .asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Adjusting Method</b></em>' literal with the specified literal value. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public static AdjustingMethod get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            AdjustingMethod result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Adjusting Method</b></em>' literal with the specified name. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static AdjustingMethod getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            AdjustingMethod result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Adjusting Method</b></em>' literal with the specified integer value. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public static AdjustingMethod get(int value) {
        switch (value) {
            case NONE_VALUE:
                return NONE;
            case MIDNIGHT_VALUE:
                return MIDNIGHT;
            case ONE_SEVENTH_VALUE:
                return ONE_SEVENTH;
            case ANGLE_BASED_VALUE:
                return ANGLE_BASED;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private final int    value;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private AdjustingMethod(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public int getValue() {
        return value;
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
    public String getLiteral() {
        return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string representation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }

    /**
     * @param adjustingMethod
     * @return The description of the the specified calculation method or <code>null</code> if no description is
     *         available from the annotation of the literal.
     */
    public static String getDescription(final AdjustingMethod adjustingMethod) {
        EEnumLiteral eEnumLiteral = WaqtSalatPackage.eINSTANCE.getAdjustingMethod().getEEnumLiteral(
                adjustingMethod.getLiteral());
        EAnnotation eAnnotation = eEnumLiteral.getEAnnotation("net.paissad.waqtsalat.core/adjustingmethod/1.0"); //$NON-NLS-1$
        String description = null;
        if (eAnnotation != null) {
            description = eAnnotation.getDetails().get("description"); //$NON-NLS-1$
        }
        return description;

    }

} // AdjustingMethod
