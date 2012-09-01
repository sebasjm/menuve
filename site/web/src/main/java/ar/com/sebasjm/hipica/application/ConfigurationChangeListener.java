package ar.com.sebasjm.hipica.application;


public interface ConfigurationChangeListener {

    public void onChange(String key, String newValue, String oldValue, String propetyFileName);
    
}
