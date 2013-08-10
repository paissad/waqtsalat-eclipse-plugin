/**
 */
package net.paissad.waqtsalat.core.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration ' <em><b>Juristic Method</b></em>', and
 * utility methods for working with them. <!-- end-user-doc -->
 * 
 * @see net.paissad.waqtsalat.core.WaqtSalatPackage#getJuristicMethod()
 * @model
 * @generated
 */
public enum JuristicMethod implements Enumerator {
    /**
     * The '<em><b>SHAFII</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #SHAFII_VALUE
     * @generated
     * @ordered
     */
    SHAFII(0, "SHAFII", "SHAFII"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>HANAFI</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #HANAFI_VALUE
     * @generated
     * @ordered
     */
    HANAFI(1, "HANAFI", "HANAFI"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>SHAFII</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>SHAFII</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #SHAFII
     * @model
     * @generated
     * @ordered
     */
    public static final int                  SHAFII_VALUE = 0;

    /**
     * The '<em><b>HANAFI</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>HANAFI</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #HANAFI
     * @model
     * @generated
     * @ordered
     */
    public static final int                  HANAFI_VALUE = 1;

    /**
     * An array of all the '<em><b>Juristic Method</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final JuristicMethod[]    VALUES_ARRAY = new JuristicMethod[] { SHAFII, HANAFI, };

    /**
     * A public read-only list of all the '<em><b>Juristic Method</b></em>' enumerators. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static final List<JuristicMethod> VALUES       = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Juristic Method</b></em>' literal with the specified literal value. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public static JuristicMethod get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            JuristicMethod result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Juristic Method</b></em>' literal with the specified name. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static JuristicMethod getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            JuristicMethod result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Juristic Method</b></em>' literal with the specified integer value. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public static JuristicMethod get(int value) {
        switch (value) {
            case SHAFII_VALUE:
                return SHAFII;
            case HANAFI_VALUE:
                return HANAFI;
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
    private JuristicMethod(int value, String name, String literal) {
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

} // JuristicMethod
