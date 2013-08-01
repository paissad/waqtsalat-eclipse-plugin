/**
 */
package net.paissad.waqtsalat.core.impl;

import java.util.Calendar;
import net.paissad.waqtsalat.core.WaqtSalatFactory;
import net.paissad.waqtsalat.core.WaqtSalatPackage;

import net.paissad.waqtsalat.core.api.AdjustingMethod;
import net.paissad.waqtsalat.core.api.CalculationMethod;
import net.paissad.waqtsalat.core.api.JuristicMethod;
import net.paissad.waqtsalat.core.api.Pray;
import net.paissad.waqtsalat.core.api.PrayName;
import net.paissad.waqtsalat.core.api.TimeFormat;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class WaqtSalatPackageImpl extends EPackageImpl implements WaqtSalatPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass    prayEClass             = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum     calculationMethodEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum     adjustingMethodEEnum   = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum     prayNameEEnum          = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum     timeFormatEEnum        = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum     juristicMethodEEnum    = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType calendarEDataType      = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
     * performs initialization of the package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see net.paissad.waqtsalat.core.WaqtSalatPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private WaqtSalatPackageImpl() {
        super(eNS_URI, WaqtSalatFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>
     * This method is used to initialize {@link WaqtSalatPackage#eINSTANCE} when that field is accessed. Clients should
     * not invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static WaqtSalatPackage init() {
        if (isInited) return (WaqtSalatPackage) EPackage.Registry.INSTANCE.getEPackage(WaqtSalatPackage.eNS_URI);

        // Obtain or create and register package
        WaqtSalatPackageImpl theWaqtSalatPackage = (WaqtSalatPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof WaqtSalatPackageImpl ? EPackage.Registry.INSTANCE
                .get(eNS_URI) : new WaqtSalatPackageImpl());

        isInited = true;

        // Create package meta-data objects
        theWaqtSalatPackage.createPackageContents();

        // Initialize created meta-data
        theWaqtSalatPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theWaqtSalatPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(WaqtSalatPackage.eNS_URI, theWaqtSalatPackage);
        return theWaqtSalatPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getPray() {
        return prayEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPray_Name() {
        return (EAttribute) prayEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPray_Time() {
        return (EAttribute) prayEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getCalculationMethod() {
        return calculationMethodEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getAdjustingMethod() {
        return adjustingMethodEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getPrayName() {
        return prayNameEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getTimeFormat() {
        return timeFormatEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getJuristicMethod() {
        return juristicMethodEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EDataType getCalendar() {
        return calendarEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public WaqtSalatFactory getWaqtSalatFactory() {
        return (WaqtSalatFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to have no affect on any invocation but
     * its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        prayEClass = createEClass(PRAY);
        createEAttribute(prayEClass, PRAY__NAME);
        createEAttribute(prayEClass, PRAY__TIME);

        // Create enums
        calculationMethodEEnum = createEEnum(CALCULATION_METHOD);
        adjustingMethodEEnum = createEEnum(ADJUSTING_METHOD);
        prayNameEEnum = createEEnum(PRAY_NAME);
        timeFormatEEnum = createEEnum(TIME_FORMAT);
        juristicMethodEEnum = createEEnum(JURISTIC_METHOD);

        // Create data types
        calendarEDataType = createEDataType(CALENDAR);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This method is guarded to have no affect on any
     * invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes, features, and operations; add parameters
        initEClass(prayEClass, Pray.class, "Pray", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getPray_Name(),
                this.getPrayName(),
                "name", null, 0, 1, Pray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getPray_Time(),
                this.getCalendar(),
                "time", null, 0, 1, Pray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        // Initialize enums and add enum literals
        initEEnum(calculationMethodEEnum, CalculationMethod.class, "CalculationMethod"); //$NON-NLS-1$
        addEEnumLiteral(calculationMethodEEnum, CalculationMethod.JAFARI);
        addEEnumLiteral(calculationMethodEEnum, CalculationMethod.KARACHI);
        addEEnumLiteral(calculationMethodEEnum, CalculationMethod.ISNA);
        addEEnumLiteral(calculationMethodEEnum, CalculationMethod.MWL);
        addEEnumLiteral(calculationMethodEEnum, CalculationMethod.MAKKAH);
        addEEnumLiteral(calculationMethodEEnum, CalculationMethod.EGYPT);
        addEEnumLiteral(calculationMethodEEnum, CalculationMethod.TEHRAN);
        addEEnumLiteral(calculationMethodEEnum, CalculationMethod.CUSTOM);

        initEEnum(adjustingMethodEEnum, AdjustingMethod.class, "AdjustingMethod"); //$NON-NLS-1$
        addEEnumLiteral(adjustingMethodEEnum, AdjustingMethod.NONE);
        addEEnumLiteral(adjustingMethodEEnum, AdjustingMethod.MIDNIGHT);
        addEEnumLiteral(adjustingMethodEEnum, AdjustingMethod.ONE_SEVENTH);
        addEEnumLiteral(adjustingMethodEEnum, AdjustingMethod.ANGLE_BASED);

        initEEnum(prayNameEEnum, PrayName.class, "PrayName"); //$NON-NLS-1$
        addEEnumLiteral(prayNameEEnum, PrayName.FADJR);
        addEEnumLiteral(prayNameEEnum, PrayName.SUNRISE);
        addEEnumLiteral(prayNameEEnum, PrayName.DHUHR);
        addEEnumLiteral(prayNameEEnum, PrayName.ASR);
        addEEnumLiteral(prayNameEEnum, PrayName.SUNSET);
        addEEnumLiteral(prayNameEEnum, PrayName.MAGHRIB);
        addEEnumLiteral(prayNameEEnum, PrayName.ISHA);

        initEEnum(timeFormatEEnum, TimeFormat.class, "TimeFormat"); //$NON-NLS-1$
        addEEnumLiteral(timeFormatEEnum, TimeFormat.TIME_24);
        addEEnumLiteral(timeFormatEEnum, TimeFormat.TIME_12);
        addEEnumLiteral(timeFormatEEnum, TimeFormat.TIME_12_NS);
        addEEnumLiteral(timeFormatEEnum, TimeFormat.FLOATING);

        initEEnum(juristicMethodEEnum, JuristicMethod.class, "JuristicMethod"); //$NON-NLS-1$
        addEEnumLiteral(juristicMethodEEnum, JuristicMethod.SHAFII);
        addEEnumLiteral(juristicMethodEEnum, JuristicMethod.HANAFI);

        // Initialize data types
        initEDataType(calendarEDataType, Calendar.class, "Calendar", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // net.paissad.waqtsalat.core/calcmethod/1.0
        create_1Annotations();
        // net.paissad.waqtsalat.core/adjustingmethod/1.0
        create_1_1Annotations();
    }

    /**
     * Initializes the annotations for <b>net.paissad.waqtsalat.core/calcmethod/1.0</b>. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected void create_1Annotations() {
        String source = "net.paissad.waqtsalat.core/calcmethod/1.0"; //$NON-NLS-1$		
        addAnnotation(calculationMethodEEnum.getELiterals().get(0), source, new String[] {
                "description", "Ithna Ashari" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(calculationMethodEEnum.getELiterals().get(1), source, new String[] {
                "description", "University of Islamic Sciences, Karachi" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(calculationMethodEEnum.getELiterals().get(2), source, new String[] {
                "description", "Islamic Society of North America" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(calculationMethodEEnum.getELiterals().get(3), source, new String[] {
                "description", "Muslim World League" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(calculationMethodEEnum.getELiterals().get(4), source, new String[] {
                "description", "Umm al-Qura, Makkah" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(calculationMethodEEnum.getELiterals().get(5), source, new String[] {
                "description", "Egyptian General Authority of Survey" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(calculationMethodEEnum.getELiterals().get(6), source, new String[] {
                "description", "Institute of Geophysics, University of Tehran" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(calculationMethodEEnum.getELiterals().get(7), source, new String[] {
                "description", "Custom Setting" //$NON-NLS-1$ //$NON-NLS-2$
        });
    }

    /**
     * Initializes the annotations for <b>net.paissad.waqtsalat.core/adjustingmethod/1.0</b>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void create_1_1Annotations() {
        String source = "net.paissad.waqtsalat.core/adjustingmethod/1.0"; //$NON-NLS-1$										
        addAnnotation(adjustingMethodEEnum.getELiterals().get(0), source, new String[] {
                "description", "No adjustments" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(adjustingMethodEEnum.getELiterals().get(1), source, new String[] {
                "description", "The middle of the night method" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(adjustingMethodEEnum.getELiterals().get(2), source, new String[] {
                "description", "The 1/7th of the night method" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(adjustingMethodEEnum.getELiterals().get(3), source, new String[] {
                "description", "The angle-based method (recommended)" //$NON-NLS-1$ //$NON-NLS-2$
        });
    }

} // WaqtSalatPackageImpl
