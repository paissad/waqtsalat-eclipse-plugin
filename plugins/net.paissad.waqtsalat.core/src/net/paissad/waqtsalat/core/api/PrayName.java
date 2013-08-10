/**
 */
package net.paissad.waqtsalat.core.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration ' <em><b>Pray Name</b></em>', and utility
 * methods for working with them. <!-- end-user-doc -->
 * 
 * @see net.paissad.waqtsalat.core.WaqtSalatPackage#getPrayName()
 * @model
 * @generated
 */
public enum PrayName implements Enumerator {
    /**
     * The '<em><b>FADJR</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #FADJR_VALUE
     * @generated
     * @ordered
     */
    FADJR(0, "FADJR", "FADJR"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>SUNRISE</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #SUNRISE_VALUE
     * @generated
     * @ordered
     */
    SUNRISE(1, "SUNRISE", "SUNRISE"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>DHUHR</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #DHUHR_VALUE
     * @generated
     * @ordered
     */
    DHUHR(2, "DHUHR", "DHUHR"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>ASR</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #ASR_VALUE
     * @generated
     * @ordered
     */
    ASR(3, "ASR", "ASR"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>SUNSET</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #SUNSET_VALUE
     * @generated
     * @ordered
     */
    SUNSET(4, "SUNSET", "SUNSET"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>MAGHRIB</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #MAGHRIB_VALUE
     * @generated
     * @ordered
     */
    MAGHRIB(5, "MAGHRIB", "MAGHRIB"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>ISHA</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #ISHA_VALUE
     * @generated
     * @ordered
     */
    ISHA(6, "ISHA", "ISHA"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>FADJR</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>FADJR</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #FADJR
     * @model
     * @generated
     * @ordered
     */
    public static final int            FADJR_VALUE   = 0;

    /**
     * The '<em><b>SUNRISE</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>SUNRISE</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #SUNRISE
     * @model
     * @generated
     * @ordered
     */
    public static final int            SUNRISE_VALUE = 1;

    /**
     * The '<em><b>DHUHR</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>DHUHR</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #DHUHR
     * @model
     * @generated
     * @ordered
     */
    public static final int            DHUHR_VALUE   = 2;

    /**
     * The '<em><b>ASR</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>ASR</b></em>' literal object isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #ASR
     * @model
     * @generated
     * @ordered
     */
    public static final int            ASR_VALUE     = 3;

    /**
     * The '<em><b>SUNSET</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>SUNSET</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #SUNSET
     * @model
     * @generated
     * @ordered
     */
    public static final int            SUNSET_VALUE  = 4;

    /**
     * The '<em><b>MAGHRIB</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>MAGHRIB</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #MAGHRIB
     * @model
     * @generated
     * @ordered
     */
    public static final int            MAGHRIB_VALUE = 5;

    /**
     * The '<em><b>ISHA</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>ISHA</b></em>' literal object isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #ISHA
     * @model
     * @generated
     * @ordered
     */
    public static final int            ISHA_VALUE    = 6;

    /**
     * An array of all the '<em><b>Pray Name</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final PrayName[]    VALUES_ARRAY  = new PrayName[] { FADJR, SUNRISE, DHUHR, ASR, SUNSET, MAGHRIB,
            ISHA,                                   };

    /**
     * A public read-only list of all the '<em><b>Pray Name</b></em>' enumerators. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static final List<PrayName> VALUES        = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Pray Name</b></em>' literal with the specified literal value. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static PrayName get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            PrayName result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Pray Name</b></em>' literal with the specified name. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static PrayName getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            PrayName result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Pray Name</b></em>' literal with the specified integer value. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static PrayName get(int value) {
        switch (value) {
            case FADJR_VALUE:
                return FADJR;
            case SUNRISE_VALUE:
                return SUNRISE;
            case DHUHR_VALUE:
                return DHUHR;
            case ASR_VALUE:
                return ASR;
            case SUNSET_VALUE:
                return SUNSET;
            case MAGHRIB_VALUE:
                return MAGHRIB;
            case ISHA_VALUE:
                return ISHA;
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
    private PrayName(int value, String name, String literal) {
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

    public static Collection<PrayName> getSortedPrayNames() {
        final List<PrayName> prayNames = new ArrayList<PrayName>(PrayName.VALUES);
        Collections.sort(prayNames, new PrayNameComparator());
        return prayNames;
    }

    private static class PrayNameComparator implements Comparator<PrayName> {
        @Override
        public int compare(PrayName o1, PrayName o2) {
            return Integer.valueOf(o1.getValue()).compareTo(Integer.valueOf(o2.getValue()));
        }
    }

} // PrayName
