package controller;

import org.apache.log4j.Logger;

/**
 *
 * @author Cesardl
 * @param <T>
 */
public interface Controller<T> {

    Logger log = Logger.getLogger(Controller.class);

    Object[][] getAll();

    T getByCode(String code);

    boolean saveOrUpdate(T entity);
}
