package com.menuve.dao.api;

import com.menuve.application.Config;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author sebasjm
 */
public class SpringDbDao extends JdbcTemplate implements Dao {

    private LazyClassMap map = new LazyClassMap();
    
    protected String getSql(Query query) {
        return Config.getQueries().getString( query.getKey() );
    }
    
    @Override
    public List list(Query query, Object ... args) {
        return this.query( getSql(query), args, map.get( query.getResult() ) );
    }

    @Override
    public long update(Query query, Object... args) {
        return this.update( getSql(query), args);
    }
    
    @Override
    public long insert(final Query query, final Object... args) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement( getSql(query) );
                for (int i = 0; i < args.length; i++) { ps.setObject(i+1, args[i]); }
                return ps;
            }
        }, keyHolder );
        return keyHolder.getKey().longValue();
    }
    
    private Pattern pattern_select = Pattern.compile("SELECT .* FROM");
    
    @Override
    public PagedResult pagedList(Query query, Integer page, Integer size, Object ... params) {
        Object args[] = new Object[params.length+2];
        System.arraycopy(params, 0, args, 0, params.length);
        args[args.length-2] = (page-1)*size;
        args[args.length-1] = size;
        final String sql = getSql(query);//.replaceAll("%user%", "'sebas'");
        String select_count = pattern_select.matcher(sql).replaceFirst("SELECT count(*) FROM");
        String select_limit = sql + " LIMIT ?, ?";
        return new PagedResult( 
            this.query      (select_limit, args, map.get( query.getResult() ) ),
            this.queryForInt(select_count, params),
            page,
            size
        );
    }

    @Override
    public <T> List<T> query(String sql, Object[] args, RowMapper<T> rowMapper) throws DataAccessException {
        System.out.println("QUERY '" +sql+ "' ARGS " + Arrays.deepToString(args));
        return super.query(sql, args, rowMapper);
    }

    @Override
    public int queryForInt(String sql, Object... args) throws DataAccessException {
        System.out.println("QUERY FOR INT '" +sql+ "' ARGS " + Arrays.deepToString(args));
        return super.queryForInt(sql, args);
    }
    
    @Override
    public int update(String sql, Object... args) throws DataAccessException {
        System.out.println("UPDATE '" +sql+ "' ARGS " + Arrays.deepToString(args));
        return super.update(sql, args);
    }

    public class LazyClassMap extends HashMap<Class,ClassRowMapper> {

        @Override
        public ClassRowMapper get(Object k) {
            Class key = (Class) k;
            if (!containsKey(key)) {
                put(key, new ClassRowMapper( key ) );
            }
            return super.get(key);
        }
        
    }
        
    
    public class ClassRowMapper implements RowMapper {

        private Class clazz;
        private Map<String,Field> fields = new HashMap<String,Field>();
        
        public ClassRowMapper(Class clazz) {
            this.clazz = clazz;
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                fields.put(field.getName(), field);
            }
        }
        
        public Object cast(Object o) {
            if ( Date.class.isAssignableFrom(o.getClass()) ) {
                return new Date( ((Date)o).getTime() );
            }
            if ( BigDecimal.class.isAssignableFrom(o.getClass()) ) {
                return ((BigDecimal)o).doubleValue();
            }
            return o;
        }
        
        public Object build(Object instance, String name, Object value) throws Exception {
            instance = instance == null ? clazz.getConstructor().newInstance() : instance;
            String[] names = name.split("_",2);
            Field field = fields.get( names[0] ); 
            if (field == null) throw new RuntimeException(String.format("Field %s doesnt exist in class %s ", names[0], clazz.getName()));
            value = buildAttribute(field, instance, names, value);
            field.set(instance, cast( value ) );
            return instance;
        }
        
        public Object buildAttribute(Field field, Object instance, String[] names, Object value) throws Exception {
            if (names.length == 1) return value;
            return map.get(field.getType()).build(field.get(instance), names[1], value );
        }
        
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            try {
                Object instance = null;
                for (int col = 1; col <= rs.getMetaData().getColumnCount(); col++) {
                    instance = build( instance, rs.getMetaData().getColumnLabel(col), rs.getObject(col) );
                }
                return instance;
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        
    }
    
}
