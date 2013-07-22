/**
 */
package net.paissad.waqtsalat.core.impl;

import net.paissad.waqtsalat.core.WaqtSalatFactory;
import net.paissad.waqtsalat.core.WaqtSalatPackage;

import net.paissad.waqtsalat.core.api.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class WaqtSalatFactoryImpl extends EFactoryImpl implements WaqtSalatFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static WaqtSalatFactory init() {
        try {
            WaqtSalatFactory theWaqtSalatFactory = (WaqtSalatFactory) EPackage.Registry.INSTANCE
                    .getEFactory(WaqtSalatPackage.eNS_URI);
            if (theWaqtSalatFactory != null) {
                return theWaqtSalatFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new WaqtSalatFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public WaqtSalatFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case WaqtSalatPackage.PRAY:
                return createPray();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case WaqtSalatPackage.CALCULATION_METHOD:
                return createCalculationMethodFromString(eDataType, initialValue);
            case WaqtSalatPackage.ADJUSTING_METHOD:
                return createAdjustingMethodFromString(eDataType, initialValue);
            case WaqtSalatPackage.PRAY_NAME:
                return createPrayNameFromString(eDataType, initialValue);
            case WaqtSalatPackage.TIME_FORMAT:
                return createTimeFormatFromString(eDataType, initialValue);
            case WaqtSalatPackage.JURISTIC_METHOD:
                return createJuristicMethodFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException(
                        "The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case WaqtSalatPackage.CALCULATION_METHOD:
                return convertCalculationMethodToString(eDataType, instanceValue);
            case WaqtSalatPackage.ADJUSTING_METHOD:
                return convertAdjustingMethodToString(eDataType, instanceValue);
            case WaqtSalatPackage.PRAY_NAME:
                return convertPrayNameToString(eDataType, instanceValue);
            case WaqtSalatPackage.TIME_FORMAT:
                return convertTimeFormatToString(eDataType, instanceValue);
            case WaqtSalatPackage.JURISTIC_METHOD:
                return convertJuristicMethodToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException(
                        "The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Pray createPray() {
        PrayImpl pray = new PrayImpl();
        return pray;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public CalculationMethod createCalculationMethod(String literal) {
        CalculationMethod result = CalculationMethod.get(literal);
        if (result == null)
            throw new IllegalArgumentException(
                    "The value '" + literal + "' is not a valid enumerator of '" + WaqtSalatPackage.Literals.CALCULATION_METHOD.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public CalculationMethod createCalculationMethodFromString(EDataType eDataType, String initialValue) {
        return createCalculationMethod(initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertCalculationMethod(CalculationMethod instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertCalculationMethodToString(EDataType eDataType, Object instanceValue) {
        return convertCalculationMethod((CalculationMethod) instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public AdjustingMethod createAdjustingMethod(String literal) {
        AdjustingMethod result = AdjustingMethod.get(literal);
        if (result == null)
            throw new IllegalArgumentException(
                    "The value '" + literal + "' is not a valid enumerator of '" + WaqtSalatPackage.Literals.ADJUSTING_METHOD.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public AdjustingMethod createAdjustingMethodFromString(EDataType eDataType, String initialValue) {
        return createAdjustingMethod(initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertAdjustingMethod(AdjustingMethod instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertAdjustingMethodToString(EDataType eDataType, Object instanceValue) {
        return convertAdjustingMethod((AdjustingMethod) instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PrayName createPrayName(String literal) {
        PrayName result = PrayName.get(literal);
        if (result == null)
            throw new IllegalArgumentException(
                    "The value '" + literal + "' is not a valid enumerator of '" + WaqtSalatPackage.Literals.PRAY_NAME.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PrayName createPrayNameFromString(EDataType eDataType, String initialValue) {
        return createPrayName(initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertPrayName(PrayName instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertPrayNameToString(EDataType eDataType, Object instanceValue) {
        return convertPrayName((PrayName) instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TimeFormat createTimeFormat(String literal) {
        TimeFormat result = TimeFormat.get(literal);
        if (result == null)
            throw new IllegalArgumentException(
                    "The value '" + literal + "' is not a valid enumerator of '" + WaqtSalatPackage.Literals.TIME_FORMAT.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TimeFormat createTimeFormatFromString(EDataType eDataType, String initialValue) {
        return createTimeFormat(initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertTimeFormat(TimeFormat instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertTimeFormatToString(EDataType eDataType, Object instanceValue) {
        return convertTimeFormat((TimeFormat) instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public JuristicMethod createJuristicMethod(String literal) {
        JuristicMethod result = JuristicMethod.get(literal);
        if (result == null)
            throw new IllegalArgumentException(
                    "The value '" + literal + "' is not a valid enumerator of '" + WaqtSalatPackage.Literals.JURISTIC_METHOD.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public JuristicMethod createJuristicMethodFromString(EDataType eDataType, String initialValue) {
        return createJuristicMethod(initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertJuristicMethod(JuristicMethod instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertJuristicMethodToString(EDataType eDataType, Object instanceValue) {
        return convertJuristicMethod((JuristicMethod) instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public WaqtSalatPackage getWaqtSalatPackage() {
        return (WaqtSalatPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static WaqtSalatPackage getPackage() {
        return WaqtSalatPackage.eINSTANCE;
    }

} // WaqtSalatFactoryImpl
