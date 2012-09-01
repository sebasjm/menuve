package ar.com.sebasjm.hipica.controller.view;

import ar.com.sebasjm.hipica.helper.JsonHelper;
import java.util.HashMap;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author sebasjm
 */
public class JsonView extends ModelAndView {
    
    public JsonView(final Object model) {
        super("format/json", new HashMap<String, Object>(){{
            put("json", JsonHelper.toJsonString(model) );
        }});
    }
    
    public JsonView() {
        super("format/json", new HashMap<String, Object>(){{
            put("json", "");
        }});
    }
    
    
}
