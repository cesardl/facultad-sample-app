package org.sanmarcux.controller;

/**
 * @param <T>
 * @author Cesardl
 */
public interface Controller<T> {

    /**
     * @return matrix data for JTable
     */
    Object[][] getAll();

    /**
     * @param code code for search
     * @return an entity
     */
    T getByCode(String code);

    /**
     * @param entity state of the operation
     * @return if end successfully
     */
    boolean saveOrUpdate(T entity);

    /**
     * @param code code of the entity
     * @return if end successfully
     */
    boolean delete(String code);

    /**
     * @param code code for search
     * @return if code exists on database
     */
    boolean existsCode(String code);

}
