package com.menuve.helper;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author sebasjm
 */
public class JsonHelper {
    private static final ObjectMapper mapper = new ObjectMapper();
    
    static {
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, true);
        mapper.configure(SerializationConfig.Feature.AUTO_DETECT_GETTERS, true);
        mapper.configure(SerializationConfig.Feature.AUTO_DETECT_IS_GETTERS, true);
        mapper.configure(SerializationConfig.Feature.AUTO_DETECT_FIELDS, true);
        mapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
    }
    
    public static String toJsonString(Object model) {
        try {
            return mapper.writeValueAsString(model);
        } catch (IOException ex) {
            Logger.getLogger(JsonHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
}
