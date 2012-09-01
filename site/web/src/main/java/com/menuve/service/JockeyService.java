package ar.com.sebasjm.hipica.service;

import ar.com.sebasjm.hipica.dao.DbQueries;
import ar.com.sebasjm.hipica.dao.api.DbDao;
import ar.com.sebasjm.hipica.model.adm.Jockey;
import java.util.List;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 *
 * @author sebasjm
 */
public class JockeyService {

    private DbDao dbDao;
    
    public List<Jockey> search(Integer userId, String nombre, Integer categoria, Integer pais, Integer from, Integer to) {
        return dbDao.list( DbQueries.search_jockey, userId, nombre, categoria, pais, from, to);
    }
    
}
