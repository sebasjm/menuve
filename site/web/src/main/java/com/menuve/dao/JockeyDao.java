package com.menuve.dao;

import com.menuve.model.Pais;
import java.util.List;

/**
 *
 * @author sebasjm
 */
public interface JockeyDao {

    public List<Pais> search(String nombre, Integer categoria, Integer pais, Integer from, Integer to);

}
