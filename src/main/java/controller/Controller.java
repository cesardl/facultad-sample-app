package controller;

import org.apache.log4j.Logger;

/**
 *
 * @author Cesardl
 * @param <T>
 */
public interface Controller<T> {

    Logger log = Logger.getLogger(Controller.class);

    Object[][] data();

    boolean insert(T entity);
}
