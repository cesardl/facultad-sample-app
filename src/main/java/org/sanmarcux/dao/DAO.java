package org.sanmarcux.dao;

import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Cesardl
 * @param <T>
 */
public interface DAO<T> {

    int STATE_ERROR = -1;

    List<T> selectAll();

    T selectByCode(String code);

    int insert(T entity);

    int update(T entity);

    int delete(T entity);

    int selectIdByCode(String code);

}
