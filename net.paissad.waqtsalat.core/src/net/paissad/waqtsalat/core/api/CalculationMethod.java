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
 * <!-- begin-user-doc --> A representation of the literals of the enumeration ' <em><b>Calculation Method</b></em>',
 * and utility methods for working with them. <!-- end-user-doc -->
 * 
 * @see net.paissad.waqtsalat.core.WaqtSalatPackage#getCalculationMethod()
 * @model
 * @generated
 */
public enum CalculationMethod implements Enumerator {
    /**
     * The '<em><b>JAFARI</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #JAFARI_VALUE
     * @generated
     * @ordered
     */
    JAFARI(0, "JAFARI", "JAFARI"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>KARACHI</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #KARACHI_VALUE
     * @generated
     * @ordered
     */
    KARACHI(1, "KARACHI", "KARACHI"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>ISNA</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #ISNA_VALUE
     * @generated
     * @ordered
     */
    ISNA(2, "ISNA", "ISNA"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>MWL</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #MWL_VALUE
     * @generated
     * @ordered
     */
    MWL(3, "MWL", "MWL"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>MAKKAH</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #MAKKAH_VALUE
     * @generated
     * @ordered
     */
    MAKKAH(4, "MAKKAH", "MAKKAH"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>EGYPT</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #EGYPT_VALUE
     * @generated
     * @ordered
     */
    EGYPT(5, "EGYPT", "EGYPT"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>TEHRAN</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #TEHRAN_VALUE
     * @generated
     * @ordered
     */
    TEHRAN(6, "TEHRAN", "TEHRAN"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>CUSTOM</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #CUSTOM_VALUE
     * @generated
     * @ordered
     */
    CUSTOM(7, "CUSTOM", "CUSTOM"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>JAFARI</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>JAFARI</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #JAFARI
     * @model annotation="net.paissad.waqtsalat.core/calcmethod/1.0 description='Ithna Ashari'"
     * @generated
     * @ordered
     */
    public static final int                     JAFARI_VALUE  = 0;

    /**
     * The '<em><b>KARACHI</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>KARACHI</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #KARACHI
     * @model 
     *        annotation="net.paissad.waqtsalat.core/calcmethod/1.0 description='University of Islamic Sciences, Karachi'"
     * @generated
     * @ordered
     */
    public static final int                     KARACHI_VALUE = 1;

    /**
     * The '<em><b>ISNA</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>ISNA</b></em>' literal object isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #ISNA
     * @model annotation="net.paissad.waqtsalat.core/calcmethod/1.0 description='Islamic Society of North America'"
     * @generated
     * @ordered
     */
    public static final int                     ISNA_VALUE    = 2;

    /**
     * The '<em><b>MWL</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>MWL</b></em>' literal object isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #MWL
     * @model annotation="net.paissad.waqtsalat.core/calcmethod/1.0 description='Muslim World League'"
     * @generated
     * @ordered
     */
    public static final int                     MWL_VALUE     = 3;

    /**
     * The '<em><b>MAKKAH</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>MAKKAH</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #MAKKAH
     * @model annotation="net.paissad.waqtsalat.core/calcmethod/1.0 description='Umm al-Qura, Makkah'"
     * @generated
     * @ordered
     */
    public static final int                     MAKKAH_VALUE  = 4;

    /**
     * The '<em><b>EGYPT</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>EGYPT</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #EGYPT
     * @model annotation="net.paissad.waqtsalat.core/calcmethod/1.0 description='Egyptian General Authority of Survey'"
     * @generated
     * @ordered
     */
    public static final int                     EGYPT_VALUE   = 5;

    /**
     * The '<em><b>TEHRAN</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>TEHRAN</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #TEHRAN
     * @model annotation=
     *        "net.paissad.waqtsalat.core/calcmethod/1.0 description='Institute of Geophysics, University of Tehran'"
     * @generated
     * @ordered
     */
    public static final int                     TEHRAN_VALUE  = 6;

    /**
     * The '<em><b>CUSTOM</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>CUSTOM</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #CUSTOM
     * @model annotation="net.paissad.waqtsalat.core/calcmethod/1.0 description='Custom Setting'"
     * @generated
     * @ordered
     */
    public static final int                     CUSTOM_VALUE  = 7;

    /**
     * An array of all the '<em><b>Calculation Method</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     */
    private static final CalculationMethod[]    VALUES_ARRAY  = new CalculationMethod[] { JAFARI, KARACHI, ISNA, MWL,
            MAKKAH, EGYPT, TEHRAN, CUSTOM,                   };

    /**
     * A public read-only list of all the '<em><b>Calculation Method</b></em>' enumerators. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static final List<CalculationMethod> VALUES        = Collections.unmodifiableList(Arrays
                                                                      .asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Calculation Method</b></em>' literal with the specified literal value. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static CalculationMethod get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            CalculationMethod result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Calculation Method</b></em>' literal with the specified name. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static CalculationMethod getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            CalculationMethod result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Calculation Method</b></em>' literal with the specified integer value. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static CalculationMethod get(int value) {
        switch (value) {
            case JAFARI_VALUE:
                return JAFARI;
            case KARACHI_VALUE:
                return KARACHI;
            case ISNA_VALUE:
                return ISNA;
            case MWL_VALUE:
                return MWL;
            case MAKKAH_VALUE:
                return MAKKAH;
            case EGYPT_VALUE:
                return EGYPT;
            case TEHRAN_VALUE:
                return TEHRAN;
            case CUSTOM_VALUE:
                return CUSTOM;
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
    private CalculationMethod(int value, String name, String literal) {
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
     * @param calcMethod
     * @return The description of the the specified calculation method or <code>null</code> if no description is
     *         available from the annotation of the literal.
     */
    public static String getDescription(CalculationMethod calcMethod) {
        EEnumLiteral eEnumLiteral = WaqtSalatPackage.eINSTANCE.getCalculationMethod().getEEnumLiteral(
                calcMethod.getLiteral());
        EAnnotation eAnnotation = eEnumLiteral.getEAnnotation("net.paissad.waqtsalat.core/calcmethod/1.0"); //$NON-NLS-1$
        String description = null;
        if (eAnnotation != null) {
            description = eAnnotation.getDetails().get("description"); //$NON-NLS-1$
        }
        return description;
    }
} // CalculationMethod
