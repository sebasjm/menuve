package com.menuve.controller;

import com.menuve.controller.view.JsonView;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author sebasjm
 */
@Controller
public class CaballerizasEdit {
    
    public ModelAndView save(Integer id) {
        return new JsonView();
    }
    
    public ModelAndView cancel() {
        return new JsonView();
    }
    
    public ModelAndView changePic() {
        return new JsonView();
    }
    
    public ModelAndView removePic() {
        return new JsonView();
    }
}
