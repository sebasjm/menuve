package com.menuve.controller;

import com.menuve.controller.view.JsonView;
import com.menuve.dao.AppQueries;
import com.menuve.dao.api.Dao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author sebasjm
 */
@Controller
public class PaisSearch {
    
    @Autowired
    private Dao dbDao;
    
    public ModelAndView delete(Integer id) {
        return new JsonView();
    }
    
    @RequestMapping(value="/home")
    public String edit(Integer id) {
        return "home";
    }
    
    @RequestMapping(value="/pais/search")
    public ModelAndView search(String nombre) {
        System.out.println("buscando por nombre: " + nombre);
        List result = dbDao.list(AppQueries.search_pais,nombre);
        return new JsonView(result);
    }
    
    @RequestMapping(value="/pais/insert")
    public ModelAndView add(String nombre, String codigo) {
        System.out.println("insertando nombre: " + nombre + " codigo: " + codigo);
        long result = dbDao.update(AppQueries.insert_pais, nombre, codigo);
        return new JsonView(result);
    }
}
