/**
 */
package net.paissad.waqtsalat.core;

import java.util.Calendar;
import net.paissad.waqtsalat.core.api.AdjustingMethod;
import net.paissad.waqtsalat.core.api.CalculationMethod;
import net.paissad.waqtsalat.core.api.JuristicMethod;
import net.paissad.waqtsalat.core.api.Pray;
import net.paissad.waqtsalat.core.api.PrayName;
import net.paissad.waqtsalat.core.api.TimeFormat;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 * 
 * @see net.paissad.waqtsalat.core.WaqtSalatPackage
 * @generated
 */
public interface WaqtSalatFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    WaqtSalatFactory eINSTANCE = net.paissad.waqtsalat.core.impl.WaqtSalatFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Pray</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Pray</em>'.
     * @generated
     */
    Pray createPray();

    /**
     * Returns an instance of data type '<em>Calculation Method</em>' corresponding the given literal. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param literal a literal of the data type.
     * @return a new instance value of the data type.
     * @generated
     */
    CalculationMethod createCalculationMethod(String literal);

    /**
     * Returns a literal representation of an instance of data type '<em>Calculation Method</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param instanceValue an instance value of the data type.
     * @return a literal representation of the instance value.
     * @generated
     */
    String convertCalculationMethod(CalculationMethod instanceValue);

    /**
     * Returns an instance of data type '<em>Adjusting Method</em>' corresponding the given literal. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param literal a literal of the data type.
     * @return a new instance value of the data type.
     * @generated
     */
    AdjustingMethod createAdjustingMethod(String literal);

    /**
     * Returns a literal representation of an instance of data type '<em>Adjusting Method</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param instanceValue an instance value of the data type.
     * @return a literal representation of the instance value.
     * @generated
     */
    String convertAdjustingMethod(AdjustingMethod instanceValue);

    /**
     * Returns an instance of data type '<em>Pray Name</em>' corresponding the given literal. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param literal a literal of the data type.
     * @return a new instance value of the data type.
     * @generated
     */
    PrayName createPrayName(String literal);

    /**
     * Returns a literal representation of an instance of data type '<em>Pray Name</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param instanceValue an instance value of the data type.
     * @return a literal representation of the instance value.
     * @generated
     */
    String convertPrayName(PrayName instanceValue);

    /**
     * Returns an instance of data type '<em>Time Format</em>' corresponding the given literal. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param literal a literal of the data type.
     * @return a new instance value of the data type.
     * @generated
     */
    TimeFormat createTimeFormat(String literal);

    /**
     * Returns a literal representation of an instance of data type '<em>Time Format</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param instanceValue an instance value of the data type.
     * @return a literal representation of the instance value.
     * @generated
     */
    String convertTimeFormat(TimeFormat instanceValue);

    /**
     * Returns an instance of data type '<em>Juristic Method</em>' corresponding the given literal. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param literal a literal of the data type.
     * @return a new instance value of the data type.
     * @generated
     */
    JuristicMethod createJuristicMethod(String literal);

    /**
     * Returns a literal representation of an instance of data type '<em>Juristic Method</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param instanceValue an instance value of the data type.
     * @return a literal representation of the instance value.
     * @generated
     */
    String convertJuristicMethod(JuristicMethod instanceValue);

    /**
     * Returns an instance of data type '<em>Calendar</em>' corresponding the given literal. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param literal a literal of the data type.
     * @return a new instance value of the data type.
     * @generated
     */
    Calendar createCalendar(String literal);

    /**
     * Returns a literal representation of an instance of data type '<em>Calendar</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param instanceValue an instance value of the data type.
     * @return a literal representation of the instance value.
     * @generated
     */
    String convertCalendar(Calendar instanceValue);

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the package supported by this factory.
     * @generated
     */
    WaqtSalatPackage getWaqtSalatPackage();

} // WaqtSalatFactory
