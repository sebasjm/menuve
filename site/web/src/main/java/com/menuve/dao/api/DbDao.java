package ar.com.sebasjm.hipica.dao.api;

import java.util.List;

/**
 *
 * @author sebasjm
 */
public interface DbDao {

    public List list(Query query, Object ... args);
    public int update(Query query, Object ... args);

}
