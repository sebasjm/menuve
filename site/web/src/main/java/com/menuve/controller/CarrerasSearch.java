package ar.com.sebasjm.hipica.controller;

import ar.com.sebasjm.hipica.controller.view.JsonView;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author sebasjm
 */
@Controller
public class CarrerasSearch {
    
    public ModelAndView delete(Integer id) {
        return new JsonView();
    }
    
    public ModelAndView edit(Integer id) {
        return new JsonView();
    }
    
    public ModelAndView search(Integer hipodromo, Date fecha, Integer page) {
        return new JsonView();
    }
    
    public ModelAndView result(Integer id) {
        return new JsonView();
    }
    
    public ModelAndView add() {
        return new JsonView();
    }
}
