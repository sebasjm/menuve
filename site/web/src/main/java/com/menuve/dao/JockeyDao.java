package ar.com.sebasjm.hipica.dao;

import ar.com.sebasjm.hipica.model.adm.Jockey;
import java.util.List;

/**
 *
 * @author sebasjm
 */
public interface JockeyDao {

    public List<Jockey> search(String nombre, Integer categoria, Integer pais, Integer from, Integer to);

}
