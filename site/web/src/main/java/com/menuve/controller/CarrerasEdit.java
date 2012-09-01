package com.menuve.controller;

import com.menuve.controller.view.JsonView;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author sebasjm
 */
@Controller
public class CarrerasEdit {
    
    public ModelAndView ok(Integer id) {
        return new JsonView();
    }
    
    public ModelAndView cancel() {
        return new JsonView();
    }
    
    public ModelAndView addSingleBet() {
        return new JsonView();
    }
    
    public ModelAndView saveSingleBet(Integer id, Integer tipo, Double base, String notas) {
        return new JsonView();
    }
    
    public ModelAndView removeSingleBet(Integer id) {
        return new JsonView();
    }
    
    public ModelAndView addComplexBet() {
        return new JsonView();
    }
    
    public ModelAndView saveComplexBet(Integer id, Integer tipo, Double base, String notas) {
        return new JsonView();
    }
    
    public ModelAndView removeComplexBet(Integer id) {
        return new JsonView();
    }
    
    public ModelAndView savePrices(Double primero, Double segundo, Double tercero, Double cuarto, Double quinto, Double sexto, Double septimo, Double extra, String notas) {
        return new JsonView();
    }
    
    public ModelAndView saveRaceInfo(Integer hipodromo, Integer reunion, Date fecha, 
            String nombre, Integer pista, Integer categoria, String categoriaLarga, 
            Boolean oficial, Boolean visible, Boolean gateras) {
        return new JsonView();
    }
    
    public ModelAndView addRacer() {
        return new JsonView();
    }
    
    public ModelAndView editRacer(Integer id) {
        return new JsonView();
    }
    
    public ModelAndView removeRacer(Integer id) {
        return new JsonView();
    }
    
}
