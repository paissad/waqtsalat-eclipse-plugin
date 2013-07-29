package net.paissad.waqtsalat.ui.util;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.waqtsalat.locationsprovider.LocationsProviderFactory;
import net.paissad.waqtsalat.locationsprovider.api.City;
import net.paissad.waqtsalat.locationsprovider.api.Coordinates;
import net.paissad.waqtsalat.locationsprovider.api.Country;
import net.paissad.waqtsalat.locationsprovider.api.ILocationsProvider;
import net.paissad.waqtsalat.ui.WaqtSalatUIPlugin;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexNotFoundException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.util.Version;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

public class LuceneUtil {

    public static final String   FIELD_CITY_NAME    = "FIELD_CITY";                             //$NON-NLS-1$
    public static final String   FIELD_COUNTRY_CODE = "FIELD_COUNTRY_CODE";                     //$NON-NLS-1$
    public static final String   FIELD_COUNTRY_NAME = "FIELD_COUNTRY_NAME";                     //$NON-NLS-1$
    public static final String   FIELD_REGION       = "FIELD_REGION";                           //$NON-NLS-1$
    public static final String   FIELD_POSTAL_CODE  = "FIELD_POSTAL_CODE";                      //$NON-NLS-1$
    public static final String   FIELD_LATITUDE     = "FIELD_LATITUDE";                         //$NON-NLS-1$
    public static final String   FIELD_LONGITUDE    = "FIELD_LONGITUDE";                        //$NON-NLS-1$

    private static final ILogger logger             = WaqtSalatUIPlugin.getPlugin().getLogger();

    public void createCitiesIndex(boolean force) throws Exception {
        IndexSearcher indexSearcher = null;
        try {
            long timeStart = System.currentTimeMillis();
            logger.info("Creating Lucene index for cities into '" + getIndexDirectory() + "'"); //$NON-NLS-1$
            IndexWriterConfig config = new IndexWriterConfig(getVersion(), getAnalyzer());
            config.setOpenMode(OpenMode.CREATE_OR_APPEND);
            IndexWriter indexWriter = new IndexWriter(getIndexDirectory(), config);
            try {
                IndexReader indexReader = IndexReader.open(getIndexDirectory());
                indexSearcher = new IndexSearcher(indexReader);
            } catch (IndexNotFoundException e) {
                force = true;
            }

            if (force) {

                indexWriter.deleteAll();
                for (City city : getCitiesFromCurrentProvider()) {
                    Document doc = new Document();
                    doc.add(new Field(FIELD_CITY_NAME, city.getName(), Field.Store.YES, Field.Index.ANALYZED));
                    doc.add(new Field(FIELD_COUNTRY_CODE, city.getCountry().getCode(), Field.Store.YES, Field.Index.NO));
                    doc.add(new Field(FIELD_COUNTRY_NAME, city.getCountry().getName(), Field.Store.YES,
                            Field.Index.ANALYZED));
                    doc.add(new Field(FIELD_REGION, city.getRegion(), Field.Store.YES, Field.Index.ANALYZED));
                    doc.add(new Field(FIELD_POSTAL_CODE, city.getPostalCode(), Field.Store.YES, Field.Index.ANALYZED));
                    doc.add(new Field(FIELD_LATITUDE, city.getCoordinates().getLatitude() + "", Field.Store.YES,
                            Field.Index.NO));
                    doc.add(new Field(FIELD_LONGITUDE, city.getCoordinates().getLongitude() + "", Field.Store.YES,
                            Field.Index.NO));
                    indexWriter.addDocument(doc);
                }
                indexWriter.commit();
                indexWriter.close(true);
                long timeStop = System.currentTimeMillis();
                logger.info("Lucene index for cities created successfuly in " + (int) (timeStop - timeStart) / 1000 + " seconds."); //$NON-NLS-1$
            } else {
                // The index exist, and force is 'false'
                // do nothing ...
            }

        } catch (Exception e) {
            String errMsg = "Error while creating Lucene index for cities : " + e.getMessage(); //$NON-NLS-1$
            logger.error(errMsg, e);
            throw new Exception(errMsg, e);

        } finally {
            closeIndex();
            try {
                indexSearcher.close();
            } catch (Exception e) {
            }
        }
    }

    public static Collection<City> searchByPostalCode(final String queryStr) throws Exception {
        return search(queryStr, FIELD_POSTAL_CODE);
    }

    public static Collection<City> searchByRegion(final String queryStr) throws Exception {
        return search(queryStr, FIELD_REGION);
    }

    public static Collection<City> searchByCountryName(final String queryStr) throws Exception {
        return search(queryStr, FIELD_COUNTRY_NAME);
    }

    public static Collection<City> searchByCityName(final String queryStr) throws Exception {
        return search(queryStr, FIELD_CITY_NAME);
    }

    private static Collection<City> search(final String queryStr, String defaultField) throws Exception {
        IndexSearcher indexSearcher = null;
        try {
            final EList<City> cities = new BasicEList<City>();
            int hitsPerPage = 500;
            Query query = new QueryParser(getVersion(), defaultField, getAnalyzer()).parse(queryStr);
            IndexReader indexReader = IndexReader.open(getIndexDirectory());
            indexSearcher = new IndexSearcher(indexReader);
            TopScoreDocCollector docCollector = TopScoreDocCollector.create(hitsPerPage, true);
            indexSearcher.search(query, docCollector);
            ScoreDoc[] hits = docCollector.topDocs().scoreDocs;

            for (final ScoreDoc hit : hits) {
                City city = LocationsProviderFactory.eINSTANCE.createCity();
                Country country = LocationsProviderFactory.eINSTANCE.createCountry();
                Coordinates coordinates = LocationsProviderFactory.eINSTANCE.createCoordinates();

                int docID = hit.doc;
                Document doc = indexSearcher.doc(docID);

                country.setName(doc.get(FIELD_COUNTRY_NAME));
                country.setCode(doc.get(FIELD_COUNTRY_CODE));

                city.setName(doc.get(FIELD_CITY_NAME));
                city.setRegion(doc.get(FIELD_REGION));
                city.setPostalCode(doc.get(FIELD_POSTAL_CODE));

                coordinates.setLatitude(Float.valueOf(doc.get(FIELD_LATITUDE)));
                coordinates.setLongitude(Float.valueOf(doc.get(FIELD_LONGITUDE)));

                city.setCountry(country);
                city.setCoordinates(coordinates);
                country.getCities().add(city);

                cities.add(city);
            }

            return cities;

        } catch (Exception e) {
            String errMsg = "Error while searching cities from Lucene index database ... : " + e.getMessage();
            logger.error(errMsg, e);
            throw new Exception(errMsg, e);

        } finally {
            closeIndex();
            if (indexSearcher != null) {
                try {
                    indexSearcher.close();
                } catch (IOException e) {
                }
            }
        }
    }

    private static Analyzer getAnalyzer() {
        return new StandardAnalyzer(getVersion());
    }

    private static Version getVersion() {
        return Version.LUCENE_35;
    }

    private static Directory getIndexDirectory() throws IOException {
        try {
            String providerID = WaqtSalatUIPlugin.getCurrentProviderExtension().getId();
            File dir = WaqtSalatUIPlugin.getPlugin().getStateLocation().append("/index/").append(providerID).toFile(); //$NON-NLS-1$
            return new NIOFSDirectory(dir);

        } catch (IOException ioe) {
            String errMsg = "Error while retrieving Lucene index directory : " + ioe.getMessage(); //$NON-NLS-1$
            logger.error(errMsg, ioe);
            throw new IOException(errMsg, ioe);
        }
    }

    private static void closeIndex() {
        try {
            if (getIndexDirectory() != null) {
                getIndexDirectory().close();
            }
        } catch (IOException e) {
            logger.error("Error while closing Lucene index directory : " + e.getMessage(), e); //$NON-NLS-1$
        }
    }

    private static Collection<City> getCitiesFromCurrentProvider() {
        ILocationsProvider locationsProvider = WaqtSalatUIPlugin.getCurrentProviderExtension().getLocationsProvider();
        return new HashSet<City>(locationsProvider.getCities());
    }

}
