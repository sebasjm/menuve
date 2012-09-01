package ar.com.sebasjm.hipica.helper;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author sebasjm
 */
public class JsonHelper {
    private static final ObjectMapper mapper = new ObjectMapper();
    
    public static String toJsonString(Object model) {
        try {
            return mapper.writeValueAsString(model);
        } catch (IOException ex) {
            Logger.getLogger(JsonHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
}
