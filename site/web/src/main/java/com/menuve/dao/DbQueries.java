package ar.com.sebasjm.hipica.dao;

import ar.com.sebasjm.hipica.dao.api.Query;
import ar.com.sebasjm.hipica.model.aux.Pais;

/**
 *
 * @author sebasjm
 */
public enum DbQueries implements Query {
    search_jockey("SELECT nombre, codigo FROM aux_pais WHERE nombre like ?", Pais.class),
    insert_jockey("INSERT INTO aux_pais (nombre,codigo) VALUES (?,?)"),
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
