package net.paissad.waqtsalat.ui.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.locks.ReentrantLock;

import net.paissad.eclipse.logger.ILogger;
import net.paissad.waqtsalat.locationsprovider.LocationsProviderFactory;
import net.paissad.waqtsalat.locationsprovider.LocationsProviderPlugin.LocationsProviderExtension;
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
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

public class LuceneUtil {

    private static final ILogger       logger             = WaqtSalatUIPlugin.getPlugin().getLogger();

    public static final String         FIELD_CITY_NAME    = "FIELD_CITY";                                    //$NON-NLS-1$
    public static final String         FIELD_COUNTRY_CODE = "FIELD_COUNTRY_CODE";                            //$NON-NLS-1$
    public static final String         FIELD_COUNTRY_NAME = "FIELD_COUNTRY_NAME";                            //$NON-NLS-1$
    public static final String         FIELD_REGION       = "FIELD_REGION";                                  //$NON-NLS-1$
    public static final String         FIELD_POSTAL_CODE  = "FIELD_POSTAL_CODE";                             //$NON-NLS-1$
    public static final String         FIELD_LATITUDE     = "FIELD_LATITUDE";                                //$NON-NLS-1$
    public static final String         FIELD_LONGITUDE    = "FIELD_LONGITUDE";                               //$NON-NLS-1$

    private static final IPath         INDEX_ROOT_PATH    = WaqtSalatUIPlugin.getPlugin().getStateLocation()
                                                                  .append("/index/");                        //$NON-NLS-1$

    private static final File          INDEX_STATUS_FILE  = new File(INDEX_ROOT_PATH.toFile(), "status.dat"); //$NON-NLS-1$

    private static final ReentrantLock STATUS_FILE_LOCK   = new ReentrantLock(true);

    public void createCitiesIndex(boolean force) throws Exception {
        IndexSearcher indexSearcher = null;
        IndexWriter indexWriter = null;
        try {
            long timeStart = System.currentTimeMillis();
            IndexWriterConfig config = new IndexWriterConfig(getVersion(), getAnalyzer());
            config.setOpenMode(OpenMode.CREATE_OR_APPEND);
            indexWriter = new IndexWriter(getIndexDirectory(), config);
            try {
                IndexReader indexReader = IndexReader.open(getIndexDirectory());
                indexSearcher = new IndexSearcher(indexReader);
            } catch (IndexNotFoundException e) {
                force = true;
            }

            if (force) {
                logger.info("Creating Lucene index for cities into '" + getIndexDirectory() + "'"); //$NON-NLS-1$

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
            closeQuietly(indexSearcher, indexWriter);
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
            closeQuietly(indexSearcher);
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
            File dir = INDEX_ROOT_PATH.append(providerID).toFile();
            return new NIOFSDirectory(dir);

        } catch (IOException ioe) {
            String errMsg = "Error while retrieving Lucene index directory : " + ioe.getMessage(); //$NON-NLS-1$
            logger.error(errMsg, ioe);
            throw new IOException(errMsg, ioe);
        }
    }

    private static void closeIndex() {
        try {
            closeQuietly(getIndexDirectory());
        } catch (IOException e) {
            logger.error("Error while closing Lucene index directory : " + e.getMessage(), e); //$NON-NLS-1$
        }
    }

    private static void closeQuietly(Closeable... closeables) {
        for (Closeable c : closeables) {
            try {
                c.close();
            } catch (Exception e) {
            }
        }
    }

    private static Collection<City> getCitiesFromCurrentProvider() {
        ILocationsProvider locationsProvider = WaqtSalatUIPlugin.getCurrentProviderExtension().getLocationsProvider();
        return new HashSet<City>(locationsProvider.getCities());
    }

    public static void initLuceneCitiesIndex() {
        LuceneUtil luceneUtil = new LuceneUtil();
        try {
            LocationsProviderExtension currentProviderExtension = WaqtSalatUIPlugin.getCurrentProviderExtension();
            if (currentProviderExtension != null) {
                boolean forceIndexCreation = !isIndexAlreadyInitialized(currentProviderExtension);
                luceneUtil.createCitiesIndex(forceIndexCreation);
                if (forceIndexCreation) {
                    appendInfoToStatusFile(currentProviderExtension);
                }
            } else {
                logger.warn("Lucene cities index not created due to unavailable locations provider."); //$NON-NLS-1$
            }
        } catch (Exception e) {
            logger.error("Error while creating Lucene cities index : " + e.getMessage(), e); //$NON-NLS-1$
        }
    }

    /**
     * @param providerExtension
     * @return <code>true</code> if the specified {@link LocationsProviderExtension} has it cities index already created
     *         completely. No update or re-creation would be needed, <code>false</code> otherwise.
     * @throws IOException
     */
    private static boolean isIndexAlreadyInitialized(final LocationsProviderExtension providerExtension)
            throws IOException {
        BufferedReader reader = null;
        try {
            if (INDEX_STATUS_FILE.isFile()) {
                reader = new BufferedReader(new FileReader(INDEX_STATUS_FILE), 8192);
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] splittedLine = line.split("\\s*=\\s*"); //$NON-NLS-1$
                    String providerID = splittedLine[0];
                    String version = splittedLine[1];
                    if (providerID != null && version != null) {
                        if (providerID.equals(providerExtension.getId())
                                && version.equals(providerExtension.getVersion().toString())) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } finally {
            closeQuietly(reader);
        }
    }

    private static void appendInfoToStatusFile(final LocationsProviderExtension providerExtension) throws IOException {

        Writer writer = null;
        STATUS_FILE_LOCK.lock();
        try {
            if (!INDEX_STATUS_FILE.isFile()) {
                INDEX_STATUS_FILE.createNewFile();
            }
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(INDEX_STATUS_FILE, true), "UTF-8")); //$NON-NLS-1$
            String newLine = providerExtension.getId() + " = " + providerExtension.getVersion().toString() + "\r\n";//$NON-NLS-1$ //$NON-NLS-2$
            writer.write(newLine);
            writer.flush();

        } finally {
            STATUS_FILE_LOCK.unlock();
            closeQuietly(writer);
        }
    }

}
