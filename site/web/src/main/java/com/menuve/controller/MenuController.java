package com.menuve.controller;

import com.menuve.controller.view.JsonView;
import com.menuve.dao.AppQueries;
import com.menuve.dao.api.Dao;
import com.menuve.model.Pais;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author sebasjm
 */
@Controller
public class MenuController {
    
    @Autowired
    private Dao dbDao;
    
    @RequestMapping(value = "/menu/search", method=RequestMethod.GET)
    public ModelAndView search(String nombre) {
        System.out.println("buscando por nombre: " + nombre);
        List result = dbDao.list(AppQueries.search_menu, nombre);
        return new JsonView(result);
    }

//    @RequestMapping(value = "/menu/insert")
    public ModelAndView add(String nombre, String codigo) {
        System.out.println("insertando nombre: " + nombre + " codigo: " + codigo);
        long result = dbDao.update(AppQueries.insert_menu, nombre, codigo);
        return new JsonView(result);
    }
    
}
