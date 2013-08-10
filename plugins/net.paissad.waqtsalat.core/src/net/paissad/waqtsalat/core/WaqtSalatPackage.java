/**
 */
package net.paissad.waqtsalat.core;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see net.paissad.waqtsalat.core.WaqtSalatFactory
 * @model kind="package"
 * @generated
 */
public interface WaqtSalatPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String           eNAME                = "waqtsalat";                                                //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String           eNS_URI              = "http://net.paissad.waqtsalat/core/1.0";                    //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String           eNS_PREFIX           = "waqtsalat";                                                //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    WaqtSalatPackage eINSTANCE            = net.paissad.waqtsalat.core.impl.WaqtSalatPackageImpl.init();

    /**
     * The meta object id for the '{@link net.paissad.waqtsalat.core.impl.PrayImpl <em>Pray</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see net.paissad.waqtsalat.core.impl.PrayImpl
     * @see net.paissad.waqtsalat.core.impl.WaqtSalatPackageImpl#getPray()
     * @generated
     */
    int              PRAY                 = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int              PRAY__NAME           = 0;

    /**
     * The feature id for the '<em><b>Time</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int              PRAY__TIME           = 1;

    /**
     * The feature id for the '<em><b>Playing Adhan</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int              PRAY__PLAYING_ADHAN  = 2;

    /**
     * The feature id for the '<em><b>Adhan Player</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int              PRAY__ADHAN_PLAYER   = 3;

    /**
     * The number of structural features of the '<em>Pray</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int              PRAY_FEATURE_COUNT   = 4;

    /**
     * The number of operations of the '<em>Pray</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int              PRAY_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link net.paissad.waqtsalat.core.api.CalculationMethod <em>Calculation Method</em>}'
     * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see net.paissad.waqtsalat.core.api.CalculationMethod
     * @see net.paissad.waqtsalat.core.impl.WaqtSalatPackageImpl#getCalculationMethod()
     * @generated
     */
    int              CALCULATION_METHOD   = 1;

    /**
     * The meta object id for the '{@link net.paissad.waqtsalat.core.api.AdjustingMethod <em>Adjusting Method</em>}'
     * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see net.paissad.waqtsalat.core.api.AdjustingMethod
     * @see net.paissad.waqtsalat.core.impl.WaqtSalatPackageImpl#getAdjustingMethod()
     * @generated
     */
    int              ADJUSTING_METHOD     = 2;

    /**
     * The meta object id for the '{@link net.paissad.waqtsalat.core.api.PrayName <em>Pray Name</em>}' enum. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see net.paissad.waqtsalat.core.api.PrayName
     * @see net.paissad.waqtsalat.core.impl.WaqtSalatPackageImpl#getPrayName()
     * @generated
     */
    int              PRAY_NAME            = 3;

    /**
     * The meta object id for the '{@link net.paissad.waqtsalat.core.api.TimeFormat <em>Time Format</em>}' enum. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see net.paissad.waqtsalat.core.api.TimeFormat
     * @see net.paissad.waqtsalat.core.impl.WaqtSalatPackageImpl#getTimeFormat()
     * @generated
     */
    int              TIME_FORMAT          = 4;

    /**
     * The meta object id for the '{@link net.paissad.waqtsalat.core.api.JuristicMethod <em>Juristic Method</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see net.paissad.waqtsalat.core.api.JuristicMethod
     * @see net.paissad.waqtsalat.core.impl.WaqtSalatPackageImpl#getJuristicMethod()
     * @generated
     */
    int              JURISTIC_METHOD      = 5;

    /**
     * The meta object id for the '<em>Calendar</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see java.util.Calendar
     * @see net.paissad.waqtsalat.core.impl.WaqtSalatPackageImpl#getCalendar()
     * @generated
     */
    int              CALENDAR             = 6;

    /**
     * Returns the meta object for class ' {@link net.paissad.waqtsalat.core.api.Pray <em>Pray</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Pray</em>'.
     * @see net.paissad.waqtsalat.core.api.Pray
     * @generated
     */
    EClass getPray();

    /**
     * Returns the meta object for the attribute '{@link net.paissad.waqtsalat.core.api.Pray#getName <em>Name</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see net.paissad.waqtsalat.core.api.Pray#getName()
     * @see #getPray()
     * @generated
     */
    EAttribute getPray_Name();

    /**
     * Returns the meta object for the attribute '{@link net.paissad.waqtsalat.core.api.Pray#getTime <em>Time</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Time</em>'.
     * @see net.paissad.waqtsalat.core.api.Pray#getTime()
     * @see #getPray()
     * @generated
     */
    EAttribute getPray_Time();

    /**
     * Returns the meta object for the attribute '{@link net.paissad.waqtsalat.core.api.Pray#isPlayingAdhan
     * <em>Playing Adhan</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Playing Adhan</em>'.
     * @see net.paissad.waqtsalat.core.api.Pray#isPlayingAdhan()
     * @see #getPray()
     * @generated
     */
    EAttribute getPray_PlayingAdhan();

    /**
     * Returns the meta object for the attribute '{@link net.paissad.waqtsalat.core.api.Pray#getAdhanPlayer
     * <em>Adhan Player</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Adhan Player</em>'.
     * @see net.paissad.waqtsalat.core.api.Pray#getAdhanPlayer()
     * @see #getPray()
     * @generated
     */
    EAttribute getPray_AdhanPlayer();

    /**
     * Returns the meta object for enum '{@link net.paissad.waqtsalat.core.api.CalculationMethod
     * <em>Calculation Method</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Calculation Method</em>'.
     * @see net.paissad.waqtsalat.core.api.CalculationMethod
     * @generated
     */
    EEnum getCalculationMethod();

    /**
     * Returns the meta object for enum '{@link net.paissad.waqtsalat.core.api.AdjustingMethod
     * <em>Adjusting Method</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Adjusting Method</em>'.
     * @see net.paissad.waqtsalat.core.api.AdjustingMethod
     * @generated
     */
    EEnum getAdjustingMethod();

    /**
     * Returns the meta object for enum ' {@link net.paissad.waqtsalat.core.api.PrayName <em>Pray Name</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Pray Name</em>'.
     * @see net.paissad.waqtsalat.core.api.PrayName
     * @generated
     */
    EEnum getPrayName();

    /**
     * Returns the meta object for enum '{@link net.paissad.waqtsalat.core.api.TimeFormat <em>Time Format</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Time Format</em>'.
     * @see net.paissad.waqtsalat.core.api.TimeFormat
     * @generated
     */
    EEnum getTimeFormat();

    /**
     * Returns the meta object for enum '{@link net.paissad.waqtsalat.core.api.JuristicMethod <em>Juristic Method</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Juristic Method</em>'.
     * @see net.paissad.waqtsalat.core.api.JuristicMethod
     * @generated
     */
    EEnum getJuristicMethod();

    /**
     * Returns the meta object for data type '{@link java.util.Calendar <em>Calendar</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for data type '<em>Calendar</em>'.
     * @see java.util.Calendar
     * @model instanceClass="java.util.Calendar"
     * @generated
     */
    EDataType getCalendar();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    WaqtSalatFactory getWaqtSalatFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each operation of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link net.paissad.waqtsalat.core.impl.PrayImpl <em>Pray</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see net.paissad.waqtsalat.core.impl.PrayImpl
         * @see net.paissad.waqtsalat.core.impl.WaqtSalatPackageImpl#getPray()
         * @generated
         */
        EClass     PRAY                = eINSTANCE.getPray();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EAttribute PRAY__NAME          = eINSTANCE.getPray_Name();

        /**
         * The meta object literal for the '<em><b>Time</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EAttribute PRAY__TIME          = eINSTANCE.getPray_Time();

        /**
         * The meta object literal for the '<em><b>Playing Adhan</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PRAY__PLAYING_ADHAN = eINSTANCE.getPray_PlayingAdhan();

        /**
         * The meta object literal for the '<em><b>Adhan Player</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PRAY__ADHAN_PLAYER  = eINSTANCE.getPray_AdhanPlayer();

        /**
         * The meta object literal for the '{@link net.paissad.waqtsalat.core.api.CalculationMethod
         * <em>Calculation Method</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see net.paissad.waqtsalat.core.api.CalculationMethod
         * @see net.paissad.waqtsalat.core.impl.WaqtSalatPackageImpl#getCalculationMethod()
         * @generated
         */
        EEnum      CALCULATION_METHOD  = eINSTANCE.getCalculationMethod();

        /**
         * The meta object literal for the '{@link net.paissad.waqtsalat.core.api.AdjustingMethod
         * <em>Adjusting Method</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see net.paissad.waqtsalat.core.api.AdjustingMethod
         * @see net.paissad.waqtsalat.core.impl.WaqtSalatPackageImpl#getAdjustingMethod()
         * @generated
         */
        EEnum      ADJUSTING_METHOD    = eINSTANCE.getAdjustingMethod();

        /**
         * The meta object literal for the '{@link net.paissad.waqtsalat.core.api.PrayName <em>Pray Name</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see net.paissad.waqtsalat.core.api.PrayName
         * @see net.paissad.waqtsalat.core.impl.WaqtSalatPackageImpl#getPrayName()
         * @generated
         */
        EEnum      PRAY_NAME           = eINSTANCE.getPrayName();

        /**
         * The meta object literal for the '{@link net.paissad.waqtsalat.core.api.TimeFormat <em>Time Format</em>}'
         * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see net.paissad.waqtsalat.core.api.TimeFormat
         * @see net.paissad.waqtsalat.core.impl.WaqtSalatPackageImpl#getTimeFormat()
         * @generated
         */
        EEnum      TIME_FORMAT         = eINSTANCE.getTimeFormat();

        /**
         * The meta object literal for the '{@link net.paissad.waqtsalat.core.api.JuristicMethod
         * <em>Juristic Method</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see net.paissad.waqtsalat.core.api.JuristicMethod
         * @see net.paissad.waqtsalat.core.impl.WaqtSalatPackageImpl#getJuristicMethod()
         * @generated
         */
        EEnum      JURISTIC_METHOD     = eINSTANCE.getJuristicMethod();

        /**
         * The meta object literal for the '<em>Calendar</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see java.util.Calendar
         * @see net.paissad.waqtsalat.core.impl.WaqtSalatPackageImpl#getCalendar()
         * @generated
         */
        EDataType  CALENDAR            = eINSTANCE.getCalendar();

    }

} // WaqtSalatPackage
