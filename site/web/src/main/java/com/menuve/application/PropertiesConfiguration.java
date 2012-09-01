package ar.com.sebasjm.hipica.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Contains overall Application configuration. TODO this class should be
 * refactored in a way that we can have a "utility" class the handles all the
 * file loading (and reloading) and our ApplicationProperties on top of that
 * one.
 * 
 */
public class PropertiesConfiguration implements Configuration {

    private static final String LIST_DELIMITER = ",";
    
    /**
     * The prefix and suffix for constant names within property values
     */
    private static final String START_CONST = "{";
    private static final String END_CONST = "}";
      
    /**
     * The maximum depth for recursive substitution of constants within property
     * values (e.g., A={B} .. B={C} .. C={D} .. etc.)
     */
    private static final int MAX_SUBST_DEPTH = 5;
    
    private static final Log LOGGER = LogFactory.getLog(PropertiesConfiguration.class);

    private long refreshTimeMillis = 1 * 20 * 1000;
    
    /**
     * List of property files, that there are inyected or manual seted
     */
    private List<File> files = new ArrayList<File>();
    
    /**
     * This thread looks every {@refreshTimeMillis} ms if the file changes
     */
    private Timer changesMonitor;
    
    /**
     * List of change listeners files, that are called when a property file change or is added.
     */
    private List<ConfigurationChangeListener> changeListeners = new ArrayList<ConfigurationChangeListener>();
    
    /**
     * This are the properties that are only stored in the {@value #DEFAULT_PROPERTY_FILE}
     */
    private Properties staticProperties;
    
    /**
     * All the properties that are loaded from all the property files
     */
    private Properties properties;
    
    private long lastRefreshTimeMillis;

    protected Map<String, FileProperties> fileProperties = new HashMap<String, FileProperties>();

    public PropertiesConfiguration(String coreFile) {
        staticProperties = new Properties();
        staticProperties.putAll(loadCoreFile(coreFile));
        reloadProperties();
        initChangesMonitor();
    }
    
    private void initChangesMonitor() {
        
        changesMonitor = new Timer();
        
        TimerTask reloadTask = new TimerTask() {
            @Override
            public void run() {				
                PropertiesConfiguration.this.reloadProperties();		
            }
        };
        
        changesMonitor.schedule(reloadTask, refreshTimeMillis, refreshTimeMillis);
    }
    
    /**
     * This method doesn't throw any exception
     * 
     * @return if the properties where loaded ok
     */
    private synchronized void reloadProperties() {
        Properties props = new Properties();
        long start = System.currentTimeMillis();
        LOGGER.debug("Reloading Application Properties file...");
        try {
            props.putAll(staticProperties);

            if (this.properties != null && this.properties.size() > 0) {
                // reuse existing properties
                props.putAll(this.properties);
            }

            // get additional resources to load
            loadAdditionalFiles(props);
        } catch (Exception e) {
            LOGGER.error("Could not load project properties. ", e);
        }

        lastRefreshTimeMillis = System.currentTimeMillis();
        
        LOGGER.debug("Reloading Application Properties file... took: " + (lastRefreshTimeMillis - start) + "ms");
        this.properties = props;
    }
    
    private Properties loadCoreFile(String coreFile) {
        Properties coreProperties = new Properties();
        
        InputStream inputStream = null;
        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream(coreFile);
            coreProperties.load(inputStream);
        } catch (Exception e) {
            LOGGER.error("Could not find " + coreFile + " file. Check if the project has it included", e);
            throw new RuntimeException("Could not find " + coreFile + " file. Check if the project has it included", e);
        } finally {
            closeQuietly(inputStream);
        }

        coreProperties.putAll(System.getProperties());
        
        return coreProperties;
    }

    /**
     * What we do here is to load all aditional files defined in the main static
     * config file, with the key {@value #ADDITIONAL_PROPS_FILES_KEY}
     * 
     * @param props
     * @throws IOException
     */
    private void loadAdditionalFiles(Properties props) throws IOException {

        if (files == null || files.size() == 0)
            return;
        
        for (File file : files) {
            loadFileIfModified(file, props);
        }
    }
    
    /**
     * Loads a single properties file only if it's last modification time is
     * greater than the previous time the file was loaded, or if the file was
     * never loaded at all. Notice that for optimization's sake if a property is
     * removed from a file then it will NOT be removed from the in-memory
     * properties - a system restart will be needed. (if not we would have to
     * track where each property comes from)
     * 
     * @param file
     *            the file to process
     * @param props
     *            where tu put the file's properties
     *                
     */
    private void loadFileIfModified(File file, Properties props) {
        // two files with the same name in different folders
        // will not do the trick
        String filename = file.getAbsolutePath();
        long actualLastModified = file.lastModified();
        
        FileProperties fprops = fileProperties.get(filename);
        if (fprops != null && fprops.getLastModified() >= actualLastModified) {
            return;
        }
        
        LOGGER.info("Reloading file " + filename + " lastUpdate: " + actualLastModified);
        
        FileInputStream inputStream = null;
        try {
            
            inputStream = new FileInputStream(file);
            fprops = new FileProperties(inputStream, actualLastModified);
            fileProperties.put(filename, fprops);
            
            
            Set<Object> changedFileKeys = fprops.getProperties().keySet();
            
            //Check if any of the properties of the file are different than the old properties
            //or if there are new properties
            for(Object key : changedFileKeys ){	
                String strkey = key.toString();
                
                if( !fprops.getProperties().getProperty(strkey).equals(props.getProperty(strkey)) ){
                    notifyChange(strkey, fprops.getProperties().getProperty(strkey), props.getProperty(strkey), filename);
                }
            }
            
            props.putAll(fprops.getProperties());
            
        } catch (Exception e) {
            LOGGER.warn("Could not find " + file + " reloadable file. " + "Check if the project has it included", e);
        } finally {
            closeQuietly(inputStream);
        }
    }
    
    private void notifyChange(String key, String newValue, String oldValue, String propetyFileName){
        
        for( ConfigurationChangeListener listener : changeListeners ){
            listener.onChange(key, newValue, oldValue, propetyFileName);
        }
        
    }

    /**
     * The reload was replaced with a thread monitor.
     */
    @Deprecated
    public static void forcePropertiesReload(){
        // Do Nothing
    }
    
    public void setRefreshTimeMillis(long refreshTimeMillis) {
        this.refreshTimeMillis = refreshTimeMillis;
    }

    /**
     * Unconditionally close an <code>InputStream</code>.
     * <p>
     * Equivalent to {@link InputStream#close()}, except any exceptions will be
     * ignored. This is typically used in finally blocks.
     * 
     * @param input
     *            the InputStream to close, may be null or already closed
     */
    private void closeQuietly(InputStream input) {
        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }
    
    public void addChangeListener(ConfigurationChangeListener listener){
        changeListeners.add(listener);
    }
    
    public void removeChangeListener(ConfigurationChangeListener listener){
        changeListeners.remove(listener);
    }
    
    public void addPropertyFile(String path){
        addPropertyFile(new File(ClassLoader.getSystemResource(path).getPath()));
    }
    
    public void addPropertyFile(File file){
        files.add(file);
        reloadProperties();
    }
    
    public void setFiles(List<File> files) {
        this.files = files;
        reloadProperties();
    }
    
    public List<File> getFiles() {
        return files;
    }
    
    public Properties getProperties() {
        return properties;
    }

    public Configuration subset(String prefix) {		
        return this;
    }

    public boolean isEmpty() {
        return getProperties().isEmpty();
    }

    public boolean containsKey(String key) {
        return getProperties().containsKey(key);
    }
    
    public Object getPropertyValue(String key) {
        return getProperties().getProperty(key);
    }

    /**
     * This method call's the change listener, if the value of a key changes.
     * 
     * @param key
     * @param value
     */
    public void setProperty(String key, Object value) {		
        Object previousValue = getProperties().setProperty(key, value.toString());
        
        if( previousValue != null && !previousValue.equals(value) ) {
            notifyChange(key, value.toString(), previousValue.toString(), null);
        }
        
    }

    public void clearProperty(String key) {
        getProperties().remove(key);
    }

    public void clear() {
        getProperties().clear();
    }

    public Iterator<String> getKeys(String prefix) {
        Set<Object> keys = getProperties().keySet();
        Set<String> keysSubSet = new HashSet<String>(keys.size());
        
        for(Object key : keys){
            String strkey = key.toString();
            
            if( strkey.startsWith(prefix) ){
                keysSubSet.add(key.toString());
            }

        }
        
        return keysSubSet.iterator();
    }

    public Iterator<String> getKeys() {
        Set<Object> keys = getProperties().keySet();
        Set<String> keysSet = new HashSet<String>(keys.size());
        
        for(Object key : keys){
            keys.add(key.toString());
        }
        
        return keysSet.iterator();
    }

    public Properties getProperties(String key) {
        
        if( staticProperties.containsKey(key) ){
            return staticProperties;
        }
            
        for(FileProperties fileProp : fileProperties.values() ){
            
            if( fileProp.getProperties().containsKey(key) ){
                return fileProp.getProperties();
            }
            
        }
        
        return new Properties();
    }

    public boolean getBoolean(String key) {
        return Boolean.valueOf(getProperties().getProperty(key));
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        if( getProperties().containsKey(key) ){
            return getBoolean(key);
        }
        return defaultValue;
    }

    public Boolean getBoolean(String key, Boolean defaultValue) {
        return getBoolean(key, defaultValue);
    }

    public byte getByte(String key) {
        return Byte.valueOf(getProperties().getProperty(key));
    }

    public byte getByte(String key, byte defaultValue) {
        if( getProperties().containsKey(key) ){
            return getByte(key);
        }
        return defaultValue;
    }

    public Byte getByte(String key, Byte defaultValue) {
        return getByte(key, defaultValue);
    }

    public double getDouble(String key) {
        return Double.valueOf(getProperties().getProperty(key));
    }

    public double getDouble(String key, double defaultValue) {
        if( getProperties().containsKey(key) ){
            return getDouble(key);
        }
        return defaultValue;
    }

    public Double getDouble(String key, Double defaultValue) {
        return getDouble(key, defaultValue);
    }

    public float getFloat(String key) {
        return Float.valueOf(getProperties().getProperty(key));
    }

    public float getFloat(String key, float defaultValue) {
        if( getProperties().containsKey(key) ){
            return getFloat(key);
        }
        return defaultValue;
    }

    public Float getFloat(String key, Float defaultValue) {
        return getFloat(key, defaultValue);
    }

    public int getInt(String key) {
        return Integer.valueOf(getProperties().getProperty(key));
    }

    public int getInt(String key, int defaultValue) {
        if( getProperties().containsKey(key) ){
            return getInt(key);
        }
        return defaultValue;
    }

    public Integer getInteger(String key, Integer defaultValue) {
        return getInt(key, defaultValue);
    }

    public long getLong(String key) {
        return Long.valueOf(getProperties().getProperty(key));
    }

    public long getLong(String key, long defaultValue) {
        if( getProperties().containsKey(key) ){
            return getLong(key);
        }
        return defaultValue;
    }

    public Long getLong(String key, Long defaultValue) {
        return getLong(key, defaultValue);
    }

    public short getShort(String key) {
        return Short.valueOf(getProperties().getProperty(key));
    }

    public short getShort(String key, short defaultValue) {
        if( getProperties().containsKey(key) ){
            return getShort(key);
        }
        return defaultValue;
    }

    public Short getShort(String key, Short defaultValue) {
        return getShort(key, defaultValue);
    }

    public BigDecimal getBigDecimal(String key) {
        return BigDecimal.valueOf(getDouble(key));
    }

    public BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
        if( getProperties().containsKey(key) ){
            return getBigDecimal(key);
        }
        return defaultValue;
    }

    public BigInteger getBigInteger(String key) {
        return BigInteger.valueOf(getLong(key));
    }

    public BigInteger getBigInteger(String key, BigInteger defaultValue) {
        if( getProperties().containsKey(key) ){
            return getBigInteger(key);
        }
        return defaultValue;
    }

    public String getString(String key) {
        return getProperties().getProperty(key);
    }

    public String getString(String key, String defaultValue) {
        if( getProperties().containsKey(key) ){
            return getString(key);
        }
        return defaultValue;
    }

    public String[] getStringArray(String key) {
        return getStringArray(key, LIST_DELIMITER);
    }
    
    public String[] getStringArray(String key, String delimiter) {
        String prop = getString(key);
        if( prop != null ){
            return prop.split(delimiter);
        }
        return new String[0];
    }

    public List<String> getList(String key) {
        return Arrays.asList(getStringArray(key));
    }

    public List<String> getList(String key, String delimiter) {
        return Arrays.asList(getStringArray(key, delimiter));
    }
    
    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns
     * <code>null</code> if the property is not found. <b>Also if a property
     * name is present (between the proper separators) it will be replaced
     * recursively. It means that if the replaced property has a property name
     * like the first, it will be replaced too.
     * <p>
     * THIS METHOD IS SLOWER THAN THE OTHERS
     * </p>
     * </b>
     * 
     * @param key
     * @return
     */
    public String getPropertyRecursively(String key) {
        // Return the property value starting at level 0
        return getPropertyRecursively(key, 0);
    }	
    
    /**
     * Searches for the property with the specified
     * key in this property list. If the key is not
     * found in this property list, the default
     * property list, and its defaults, recursively,
     * are then checked. The method returns
     * <code>null</code> if the property is not found.
     *
     * <p>The level parameter specifies the current
     * level of recursive constant substitution. If
     * the requested property value includes a
     * constant, its value is substituted in place
     * through a recursive call to this method,
     * incrementing the level. Once level exceeds
     * MAX_SUBST_DEPTH, no further constant
     * substitutions are performed within the
     * current requested value.
     *
     * @param   key   the property key.
     * @param level  the level of recursion so far
     * @return  the value in this property list with
     * the specified key value.
     */
    private String getPropertyRecursively(String key, int level) {

       String value = getProperties().getProperty(key);
       if (value != null) {

          // Get the index of the first constant, if any
          int beginIndex = 0;
          int startName = value.indexOf(START_CONST, beginIndex);

          while (startName != -1) {
             if (level+1 > MAX_SUBST_DEPTH) {
                // Exceeded MAX_SUBST_DEPTH
                // Return the value as is
                return value;
             }

             int endName = value.indexOf(END_CONST, startName);
             if (endName == -1) {
                // Terminating symbol not found
                // Return the value as is
                return value;
             }

             String constName = value.substring(startName+1, endName);
             String constValue = getPropertyRecursively(constName, level+1);

             if (constValue == null) {
                // Property name not found
                // Return the value as is
                return value;
             }

             // Insert the constant value into the
             // original property value
             String newValue = (startName>0) ? value.substring(0, startName) : "";
             newValue += constValue;

             // Start checking for constants at this index
             beginIndex = newValue.length();

             // Append the remainder of the value
             newValue += value.substring(endName+1);

             value = newValue;

             // Look for the next constant
             startName = value.indexOf(START_CONST, beginIndex);
          }
       }

       // Return the value as is
       return value;
    }	
    
    private class FileProperties {
        
        private Properties properties = null;
        private long lastModified = 0;

        public FileProperties() {
            properties = new Properties();
        }
        
        public FileProperties(FileInputStream fileInputStream, long lastModified) throws IOException {
            this();
            properties.load( fileInputStream );
            this.lastModified = lastModified;
        }
        
        public Properties getProperties() {
            return properties;
        }
        
        public long getLastModified() {
            return lastModified;
        }
        
    }
}
