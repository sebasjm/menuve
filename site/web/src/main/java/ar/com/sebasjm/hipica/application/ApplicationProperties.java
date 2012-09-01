package ar.com.sebasjm.hipica.application;

import java.io.File;
import java.util.List;

/**
 * Contains overall Application configuration. TODO this class should be
 * refactored in a way that we can have a "utility" class the handles all the
 * file loading (and reloading) and our ApplicationProperties on top of that
 * one.
 * 
 */
public class ApplicationProperties {

    private static final String DEFAULT_PROPERTY_FILE = "project.properties";
    
    private static final String CURRENCY_CODE = "application.currency.default";

    private static final String APPLICATION_NAME_KEY = "application.name";

    private static final String APPLICATION_CODE_KEY = "application.code";

    private static final String SERVER_NAME = "app.serverName";
    
    private static ApplicationProperties INSTANCE;
    
    private PropertiesConfiguration configuration;
    
    private ApplicationProperties() {
        
        synchronized (ApplicationProperties.class) {
            if (INSTANCE == null) {
                INSTANCE = this;
                configuration = new PropertiesConfiguration(DEFAULT_PROPERTY_FILE);
            }
        }
        
    }
    
    public static ApplicationProperties getInstance() {
        if (INSTANCE == null) {
            return new ApplicationProperties();
        }
                
        return INSTANCE;
    }
    
    public PropertiesConfiguration getConfiguration() {
        return configuration;
    }
    
    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. <b>The method returns
     * the String "null" if the property is not found.</b>
     * 
     * @param key
     *            the property key.
     * @return the value in this property list with the specified key value.
     */
    public static String getProperty(String key) {
        return getInstance().getConfiguration().getString(key);
    }
    
    public static void setProperty(String key, Object value) {
        getInstance().getConfiguration().setProperty(key, value);
    }

    public static Integer getIntProperty(String key) {
        return getInstance().getConfiguration().getInt(key);
    }

    public static Double getDoubleProperty(String key) {
        return getInstance().getConfiguration().getDouble(key);
    }

    public static Float getFloatProperty(String key) {
        return getInstance().getConfiguration().getFloat(key);
    }

    public static boolean getBooleanProperty(String key) {
        return getInstance().getConfiguration().getBoolean(key);
    }

    public static String[] getStringArrayProperty(String key) {
        return getInstance().getConfiguration().getStringArray(key);
    }
    
    public static String[] getStringArrayProperty(String key, String delimiter) {
        return getInstance().getConfiguration().getStringArray(key, delimiter);
    }
    
    public static List<String> getListProperty(String key) {
        return getInstance().getConfiguration().getList(key);
    }
    
    public static List<String> getListProperty(String key, String delimiter) {
        return getInstance().getConfiguration().getList(key, delimiter);
    }

    
    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns
     * <code>null</code> if the property is not found.
     * 
     * @param key
     *            the property key.
     * @return the value in this property list with the specified key value.
     */
    public static String getStringProperty(String key) {
        return getInstance().getConfiguration().getString(key);
    }

    public static String getStringProperty(String key, String defaultValue) {
        return getInstance().getConfiguration().getString(key, defaultValue);
    }

    public static String getApplicationName() {
        return getProperty(APPLICATION_NAME_KEY);
    }

    public static String getApplicationCode() {
        return getProperty(APPLICATION_CODE_KEY);
    }

    public static String getServerName() {
        return getProperty(SERVER_NAME);
    }

    public static void setRefreshTimeMillis(long refreshTimeMillis) {
        ApplicationProperties.getInstance().getConfiguration().setRefreshTimeMillis(refreshTimeMillis);
    }

    public static String getPropertyRecursively(String key) {
        return ApplicationProperties.getInstance().getConfiguration().getPropertyRecursively(key);
    }
    
    /**
     * Returns the default configured currency for the current game. It must be
     * an ISO4217 code
     * 
     * @return
     */
    public static String getDefaultCurrency() {
        String currencyCode = getProperty(CURRENCY_CODE);
        return currencyCode;
    }
    
//    public static void addChangeListener(ConfigurationChangeListener listener){
//        getInstance().getConfiguration().addChangeListener(listener);
//    }
//    
//    public static void removeChangeListener(ConfigurationChangeListener listener){
//        getInstance().getConfiguration().removeChangeListener(listener);
//    }
    
    /**
     * Lets add a specific key value to the current properties context.
     * 
     * @param key
     *            Key to identify the value. Example: "feature.area.description"
     * @param value
     *            Value contained in the key. Example: "8"
     */
    public static void addProperty(String key, String value) {
        ApplicationProperties.getInstance().getConfiguration().getProperties().put(key, value);
    }
    
    public static void addPropertyFile(String path){
        getInstance().getConfiguration().addPropertyFile(path);
    }
    
    public static void addPropertyFile(File file){
        getInstance().getConfiguration().addPropertyFile(file);
    }
    
    public static void setFiles(List<File> files) {
        getInstance().getConfiguration().setFiles(files);
    }
    
}
