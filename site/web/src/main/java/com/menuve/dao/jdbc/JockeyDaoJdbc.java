package ar.com.sebasjm.hipica.dao.jdbc;

import ar.com.sebasjm.hipica.dao.DbQueries;
import ar.com.sebasjm.hipica.dao.JockeyDao;
import ar.com.sebasjm.hipica.dao.api.DbDao;
import ar.com.sebasjm.hipica.model.adm.Jockey;
import java.util.List;

/**
 *
 * @author sebasjm
 */
public class JockeyDaoJdbc implements JockeyDao {
    
    private DbDao dbDao;
    
    @Override
    public List<Jockey> search(String nombre, Integer categoria, Integer pais, Integer from, Integer to) {
        return dbDao.list( DbQueries.search_jockey, nombre, categoria, pais, from, to);
    }

}
