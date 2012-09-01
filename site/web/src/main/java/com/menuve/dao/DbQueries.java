package com.menuve.dao;

import com.menuve.dao.api.Query;
import com.menuve.model.Menu;
import com.menuve.model.Pais;
import com.menuve.model.Resto;

/**
 *
 * @author sebasjm
 */
public enum DbQueries implements Query {
    search_pais("SELECT nombre, codigo FROM aux_pais WHERE nombre like ?", Pais.class),
    insert_pais("INSERT INTO aux_pais (nombre,codigo) VALUES (?,?)"),
    search_resto("SELECT name, address, created_on as createdOn, logged_on as loggedOn, id FROM resto WHERE name like ?", Resto.class),
    insert_resto("INSERT INTO resto (name,address,created_on,logged_on,user_id) VALUES (?,?,now(),now(),1)"),
    search_menu("SELECT description, name FROM menu WHERE nombre like ?", Menu.class),
    insert_menu("INSERT INTO menu (description,name,id_resto,created_on) VALUES (?,?,?,now())"),
    ;

    private final String sql;
    private final Class clazz;

    private DbQueries(String sql) {
        this.sql = sql;
        this.clazz = null;
    }
    
    private DbQueries(String sql, Class clazz) {
        this.sql = sql;
        this.clazz = clazz;
    }

    @Override
    public String getSql() {
        return sql;
    }

    @Override
    public Class getResult() {
        return clazz;
    }
}
