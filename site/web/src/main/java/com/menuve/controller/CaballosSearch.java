package com.menuve.controller;

import com.menuve.controller.view.JsonView;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author sebasjm
 */
@Controller
public class CaballosSearch {
    
    public ModelAndView delete(Integer id) {
        return new JsonView();
    }
    
    public ModelAndView edit(Integer id) {
        return new JsonView();
    }
    
    public ModelAndView search(String nombre, Integer criador, Integer caballeriza, Integer sexo, Integer pelaje, Integer pais, Integer page) {
        return new JsonView();
    }
    
    public ModelAndView add() {
        return new JsonView();
    }
}
