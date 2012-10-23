package com.menuve.dao.api;

import java.util.List;

/**
 *
 * @author sebasjm
 */
public interface Dao {

    public List list(Query query, Object ... args);
    public PagedResult pagedList(Query query, Integer page, Integer size, Object ... args);
    public long update(Query query, Object ... args);
    public long insert(Query query, Object ... args);

}
