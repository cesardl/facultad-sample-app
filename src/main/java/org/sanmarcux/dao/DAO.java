package org.sanmarcux.dao;

import java.util.List;

/**
 * @param <T>
 * @author Cesardl
 */
public interface DAO<T> {

    /**
     * @return all entities
     */
    List<T> selectAll();

    /**
     * @param code code to search
     * @return an entity
     */
    T selectByCode(String code);

    /**
     * @param entity entity to save
     * @return rows affected
     */
    int insert(T entity);

    /**
     * @param entity entity to update
     * @return rows affected
     */
    int update(T entity);

    /**
     * @param entity entity yo delete
     * @return rows affected
     */
    int delete(T entity);

    /**
     * @param code code to search
     * @return the id of entity
     */
    int selectIdByCode(String code);

}
