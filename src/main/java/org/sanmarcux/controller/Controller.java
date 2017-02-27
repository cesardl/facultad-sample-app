package org.sanmarcux.controller;

/**
 * @param <T>
 * @author Cesardl
 */
public interface Controller<T> {

    Object[][] getAll();

    T getByCode(String code);

    boolean saveOrUpdate(T entity);

    boolean delete(String code);

    boolean existsCode(String code);

}
