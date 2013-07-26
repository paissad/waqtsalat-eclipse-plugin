/**
 */
package net.paissad.waqtsalat.locationsprovider;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see net.paissad.waqtsalat.locationsprovider.LocationsProviderFactory
 * @model kind="package"
 * @generated
 */
public interface LocationsProviderPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String                   eNAME                       = "locationsprovider";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String                   eNS_URI                     = "http://net.paissad.waqtsalat/locationsprovider/1.0";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String                   eNS_PREFIX                  = "locationsprovider";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    LocationsProviderPackage eINSTANCE                   = net.paissad.waqtsalat.locationsprovider.impl.LocationsProviderPackageImpl
                                                                 .init();

    /**
     * The meta object id for the '{@link net.paissad.waqtsalat.locationsprovider.impl.CountryImpl <em>Country</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see net.paissad.waqtsalat.locationsprovider.impl.CountryImpl
     * @see net.paissad.waqtsalat.locationsprovider.impl.LocationsProviderPackageImpl#getCountry()
     * @generated
     */
    int                      COUNTRY                     = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int                      COUNTRY__NAME               = 0;

    /**
     * The feature id for the '<em><b>Cities</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int                      COUNTRY__CITIES             = 1;

    /**
     * The feature id for the '<em><b>Code</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int                      COUNTRY__CODE               = 2;

    /**
     * The number of structural features of the '<em>Country</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int                      COUNTRY_FEATURE_COUNT       = 3;

    /**
     * The number of operations of the '<em>Country</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int                      COUNTRY_OPERATION_COUNT     = 0;

    /**
     * The meta object id for the '{@link net.paissad.waqtsalat.locationsprovider.impl.CityImpl <em>City</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see net.paissad.waqtsalat.locationsprovider.impl.CityImpl
     * @see net.paissad.waqtsalat.locationsprovider.impl.LocationsProviderPackageImpl#getCity()
     * @generated
     */
    int                      CITY                        = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int                      CITY__NAME                  = 0;

    /**
     * The feature id for the '<em><b>Country</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int                      CITY__COUNTRY               = 1;

    /**
     * The feature id for the '<em><b>Coordinates</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int                      CITY__COORDINATES           = 2;

    /**
     * The feature id for the '<em><b>Region</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int                      CITY__REGION                = 3;

    /**
     * The feature id for the '<em><b>Postal Code</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int                      CITY__POSTAL_CODE           = 4;

    /**
     * The number of structural features of the '<em>City</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int                      CITY_FEATURE_COUNT          = 5;

    /**
     * The number of operations of the '<em>City</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int                      CITY_OPERATION_COUNT        = 0;

    /**
     * The meta object id for the '{@link net.paissad.waqtsalat.locationsprovider.impl.CoordinatesImpl
     * <em>Coordinates</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see net.paissad.waqtsalat.locationsprovider.impl.CoordinatesImpl
     * @see net.paissad.waqtsalat.locationsprovider.impl.LocationsProviderPackageImpl#getCoordinates()
     * @generated
     */
    int                      COORDINATES                 = 2;

    /**
     * The feature id for the '<em><b>Latitude</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int                      COORDINATES__LATITUDE       = 0;

    /**
     * The feature id for the '<em><b>Longitude</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int                      COORDINATES__LONGITUDE      = 1;

    /**
     * The number of structural features of the '<em>Coordinates</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     * @ordered
     */
    int                      COORDINATES_FEATURE_COUNT   = 2;

    /**
     * The number of operations of the '<em>Coordinates</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int                      COORDINATES_OPERATION_COUNT = 0;

    /**
     * Returns the meta object for class '{@link net.paissad.waqtsalat.locationsprovider.api.Country <em>Country</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Country</em>'.
     * @see net.paissad.waqtsalat.locationsprovider.api.Country
     * @generated
     */
    EClass getCountry();

    /**
     * Returns the meta object for the attribute '{@link net.paissad.waqtsalat.locationsprovider.api.Country#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see net.paissad.waqtsalat.locationsprovider.api.Country#getName()
     * @see #getCountry()
     * @generated
     */
    EAttribute getCountry_Name();

    /**
     * Returns the meta object for the containment reference list '
     * {@link net.paissad.waqtsalat.locationsprovider.api.Country#getCities <em>Cities</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '<em>Cities</em>'.
     * @see net.paissad.waqtsalat.locationsprovider.api.Country#getCities()
     * @see #getCountry()
     * @generated
     */
    EReference getCountry_Cities();

    /**
     * Returns the meta object for the attribute '{@link net.paissad.waqtsalat.locationsprovider.api.Country#getCode
     * <em>Code</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Code</em>'.
     * @see net.paissad.waqtsalat.locationsprovider.api.Country#getCode()
     * @see #getCountry()
     * @generated
     */
    EAttribute getCountry_Code();

    /**
     * Returns the meta object for class '{@link net.paissad.waqtsalat.locationsprovider.api.City <em>City</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>City</em>'.
     * @see net.paissad.waqtsalat.locationsprovider.api.City
     * @generated
     */
    EClass getCity();

    /**
     * Returns the meta object for the attribute '{@link net.paissad.waqtsalat.locationsprovider.api.City#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see net.paissad.waqtsalat.locationsprovider.api.City#getName()
     * @see #getCity()
     * @generated
     */
    EAttribute getCity_Name();

    /**
     * Returns the meta object for the container reference '
     * {@link net.paissad.waqtsalat.locationsprovider.api.City#getCountry <em>Country</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Country</em>'.
     * @see net.paissad.waqtsalat.locationsprovider.api.City#getCountry()
     * @see #getCity()
     * @generated
     */
    EReference getCity_Country();

    /**
     * Returns the meta object for the reference '
     * {@link net.paissad.waqtsalat.locationsprovider.api.City#getCoordinates <em>Coordinates</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Coordinates</em>'.
     * @see net.paissad.waqtsalat.locationsprovider.api.City#getCoordinates()
     * @see #getCity()
     * @generated
     */
    EReference getCity_Coordinates();

    /**
     * Returns the meta object for the attribute '{@link net.paissad.waqtsalat.locationsprovider.api.City#getRegion
     * <em>Region</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Region</em>'.
     * @see net.paissad.waqtsalat.locationsprovider.api.City#getRegion()
     * @see #getCity()
     * @generated
     */
    EAttribute getCity_Region();

    /**
     * Returns the meta object for the attribute '{@link net.paissad.waqtsalat.locationsprovider.api.City#getPostalCode
     * <em>Postal Code</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Postal Code</em>'.
     * @see net.paissad.waqtsalat.locationsprovider.api.City#getPostalCode()
     * @see #getCity()
     * @generated
     */
    EAttribute getCity_PostalCode();

    /**
     * Returns the meta object for class '{@link net.paissad.waqtsalat.locationsprovider.api.Coordinates
     * <em>Coordinates</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Coordinates</em>'.
     * @see net.paissad.waqtsalat.locationsprovider.api.Coordinates
     * @generated
     */
    EClass getCoordinates();

    /**
     * Returns the meta object for the attribute '
     * {@link net.paissad.waqtsalat.locationsprovider.api.Coordinates#getLatitude <em>Latitude</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Latitude</em>'.
     * @see net.paissad.waqtsalat.locationsprovider.api.Coordinates#getLatitude()
     * @see #getCoordinates()
     * @generated
     */
    EAttribute getCoordinates_Latitude();

    /**
     * Returns the meta object for the attribute '
     * {@link net.paissad.waqtsalat.locationsprovider.api.Coordinates#getLongitude <em>Longitude</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Longitude</em>'.
     * @see net.paissad.waqtsalat.locationsprovider.api.Coordinates#getLongitude()
     * @see #getCoordinates()
     * @generated
     */
    EAttribute getCoordinates_Longitude();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    LocationsProviderFactory getLocationsProviderFactory();

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
         * The meta object literal for the '{@link net.paissad.waqtsalat.locationsprovider.impl.CountryImpl
         * <em>Country</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see net.paissad.waqtsalat.locationsprovider.impl.CountryImpl
         * @see net.paissad.waqtsalat.locationsprovider.impl.LocationsProviderPackageImpl#getCountry()
         * @generated
         */
        EClass     COUNTRY                = eINSTANCE.getCountry();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EAttribute COUNTRY__NAME          = eINSTANCE.getCountry_Name();

        /**
         * The meta object literal for the '<em><b>Cities</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference COUNTRY__CITIES        = eINSTANCE.getCountry_Cities();

        /**
         * The meta object literal for the '<em><b>Code</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EAttribute COUNTRY__CODE          = eINSTANCE.getCountry_Code();

        /**
         * The meta object literal for the '{@link net.paissad.waqtsalat.locationsprovider.impl.CityImpl <em>City</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see net.paissad.waqtsalat.locationsprovider.impl.CityImpl
         * @see net.paissad.waqtsalat.locationsprovider.impl.LocationsProviderPackageImpl#getCity()
         * @generated
         */
        EClass     CITY                   = eINSTANCE.getCity();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EAttribute CITY__NAME             = eINSTANCE.getCity_Name();

        /**
         * The meta object literal for the '<em><b>Country</b></em>' container reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CITY__COUNTRY          = eINSTANCE.getCity_Country();

        /**
         * The meta object literal for the '<em><b>Coordinates</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference CITY__COORDINATES      = eINSTANCE.getCity_Coordinates();

        /**
         * The meta object literal for the '<em><b>Region</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EAttribute CITY__REGION           = eINSTANCE.getCity_Region();

        /**
         * The meta object literal for the '<em><b>Postal Code</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EAttribute CITY__POSTAL_CODE      = eINSTANCE.getCity_PostalCode();

        /**
         * The meta object literal for the '{@link net.paissad.waqtsalat.locationsprovider.impl.CoordinatesImpl
         * <em>Coordinates</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see net.paissad.waqtsalat.locationsprovider.impl.CoordinatesImpl
         * @see net.paissad.waqtsalat.locationsprovider.impl.LocationsProviderPackageImpl#getCoordinates()
         * @generated
         */
        EClass     COORDINATES            = eINSTANCE.getCoordinates();

        /**
         * The meta object literal for the '<em><b>Latitude</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EAttribute COORDINATES__LATITUDE  = eINSTANCE.getCoordinates_Latitude();

        /**
         * The meta object literal for the '<em><b>Longitude</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EAttribute COORDINATES__LONGITUDE = eINSTANCE.getCoordinates_Longitude();

    }

} // LocationsProviderPackage
