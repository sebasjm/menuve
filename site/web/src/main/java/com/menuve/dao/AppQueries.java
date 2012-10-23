package com.menuve.dao;

import com.menuve.dao.api.Query;
import com.menuve.model.Menu;
import com.menuve.model.Pais;
import com.menuve.model.Resto;

/**
 *
 * @author sebasjm
 */
public enum AppQueries implements Query {
    search_pais(Pais.class),
    insert_pais(),
    search_resto(Resto.class),
    insert_resto(),
    search_menu(Menu.class),
    insert_menu(),
    ;

    private final Class clazz;
    private final String key;

    private AppQueries() {
        this(null);
    }

    private AppQueries(Class clazz) {
        this.clazz = clazz;
        this.key = "query." + name().replace('_', '.');
    }

    @Override
    public Class getResult() {
        return clazz;
    }

    @Override
    public String getKey() {
        return key;
    }
    
    
}
