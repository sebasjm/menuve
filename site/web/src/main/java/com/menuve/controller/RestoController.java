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
public class RestoController {
    
    @Autowired
    private DbDao dbDao;
    
    @RequestMapping(value = "/resto/search")
    public ModelAndView search(String name) {
        System.out.println("buscando por nombre: " + name);
        List result = dbDao.list(DbQueries.search_resto, name);
        return new JsonView(result);
    }

    @RequestMapping(value = "/resto/insert")
    public ModelAndView add(String name, String addr) {
        System.out.println("insertando nombre: " + name + " addr: " + addr);
        int result = dbDao.update(DbQueries.insert_resto, name, addr);
        return new JsonView(result);
    }
}
