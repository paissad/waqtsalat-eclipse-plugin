/**
 */
package net.paissad.waqtsalat.core.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>Time Format</b></em>', and
 * utility methods for working with them. <!-- end-user-doc -->
 * 
 * @see net.paissad.waqtsalat.core.WaqtSalatPackage#getTimeFormat()
 * @model
 * @generated
 */
public enum TimeFormat implements Enumerator {
    /**
     * The '<em><b>TIME 24</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #TIME_24_VALUE
     * @generated
     * @ordered
     */
    TIME_24(0, "TIME_24", "TIME_24"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>TIME 12</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #TIME_12_VALUE
     * @generated
     * @ordered
     */
    TIME_12(1, "TIME_12", "TIME_12"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>TIME 12 NS</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #TIME_12_NS_VALUE
     * @generated
     * @ordered
     */
    TIME_12_NS(2, "TIME_12_NS", "TIME_12_NS"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>FLOATING</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #FLOATING_VALUE
     * @generated
     * @ordered
     */
    FLOATING(3, "FLOATING", "FLOATING"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>TIME 24</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>TIME 24</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #TIME_24
     * @model
     * @generated
     * @ordered
     */
    public static final int              TIME_24_VALUE    = 0;

    /**
     * The '<em><b>TIME 12</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>TIME 12</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #TIME_12
     * @model
     * @generated
     * @ordered
     */
    public static final int              TIME_12_VALUE    = 1;

    /**
     * The '<em><b>TIME 12 NS</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>TIME 12 NS</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #TIME_12_NS
     * @model
     * @generated
     * @ordered
     */
    public static final int              TIME_12_NS_VALUE = 2;

    /**
     * The '<em><b>FLOATING</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>FLOATING</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #FLOATING
     * @model
     * @generated
     * @ordered
     */
    public static final int              FLOATING_VALUE   = 3;

    /**
     * An array of all the '<em><b>Time Format</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final TimeFormat[]    VALUES_ARRAY     = new TimeFormat[] { TIME_24, TIME_12, TIME_12_NS, FLOATING, };

    /**
     * A public read-only list of all the '<em><b>Time Format</b></em>' enumerators. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static final List<TimeFormat> VALUES           = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Time Format</b></em>' literal with the specified literal value. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static TimeFormat get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            TimeFormat result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Time Format</b></em>' literal with the specified name. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static TimeFormat getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            TimeFormat result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Time Format</b></em>' literal with the specified integer value. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static TimeFormat get(int value) {
        switch (value) {
            case TIME_24_VALUE:
                return TIME_24;
            case TIME_12_VALUE:
                return TIME_12;
            case TIME_12_NS_VALUE:
                return TIME_12_NS;
            case FLOATING_VALUE:
                return FLOATING;
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
    private TimeFormat(int value, String name, String literal) {
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

} // TimeFormat
