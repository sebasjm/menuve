package com.menuve.application;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author sebasjm
 */
public class Config {
    
    public static ResourceBundle getQueries() {
        return getResourceBundle("queries");
    }
    
    public static ResourceBundle getSettings() {
        return getResourceBundle("settings");
    }
    
    private static ResourceBundle getResourceBundle(String name) {
        return ResourceBundle.getBundle(name, new ResourceBundle.Control() {
            @Override
            public long getTimeToLive(String baseName, Locale locale) {
                return 1;
            }

            @Override
            public boolean needsReload(String baseName, Locale locale, String format, ClassLoader loader, ResourceBundle bundle, long loadTime) {
                return true;
            }
        });
    }

}
