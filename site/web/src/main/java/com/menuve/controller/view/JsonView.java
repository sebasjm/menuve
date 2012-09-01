package com.menuve.controller.view;

import com.menuve.helper.JsonHelper;
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
