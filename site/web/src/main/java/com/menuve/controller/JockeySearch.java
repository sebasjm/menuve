package com.menuve.controller;

import com.menuve.controller.view.JsonView;
import com.menuve.dao.DbQueries;
import com.menuve.dao.api.DbDao;
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
public class JockeySearch {
    
    @Autowired
    private DbDao dbDao;
    
    public ModelAndView delete(Integer id) {
        return new JsonView();
    }
    
    public ModelAndView edit(Integer id) {
        return new JsonView();
    }
    
    @RequestMapping(value="/jockey/search")
    public ModelAndView search(String nombre) {
        System.out.println("buscando por nombre: " + nombre);
        List result = dbDao.list(DbQueries.search_jockey,nombre);
        return new JsonView(result);
    }
    
    @RequestMapping(value="/jockey/insert")
    public ModelAndView add(String nombre, String codigo) {
        System.out.println("insertando nombre: " + nombre + " codigo: " + codigo);
        int result = dbDao.update(DbQueries.insert_jockey, nombre, codigo);
        return new JsonView(result);
    }
}
