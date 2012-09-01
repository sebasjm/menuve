package com.menuve.dao.api;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author sebasjm
 */
public class SpringDbDao extends JdbcTemplate implements DbDao {

    private LazyClassMap map = new LazyClassMap();
    
    @Override
    public List list(Query query, Object ... args) {
        return this.query(query.getSql(), args, map.get( query.getResult() ) );
    }

    @Override
    public int update(Query query, Object... args) {
        return this.update(query.getSql(), args);
    }

    public static class LazyClassMap extends HashMap<Class,ClassRowMapper> {

        @Override
        public ClassRowMapper get(Object k) {
            Class key = (Class) k;
            if (!containsKey(key)) {
                put(key, new ClassRowMapper( key ) );
            }
            return super.get(key);
        }
        
    }
    
    
    public static class ClassRowMapper implements RowMapper {

        private Class clazz;
        private Map<String,Field> fields = new HashMap<String,Field>();
        
        public ClassRowMapper(Class clazz) {
            this.clazz = clazz;
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                fields.put(field.getName(), field);
            }
        }
        
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            try {
                Object instance = clazz.getConstructor().newInstance();
                for (int col = 1; col <= rs.getMetaData().getColumnCount(); col++) {
                    Field field = fields.get( rs.getMetaData().getColumnLabel(col)); 
                    field.set(instance, rs.getObject(col) );
                }
                return instance;
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        
    }
    
}
