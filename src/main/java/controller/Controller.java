package controller;

import org.apache.log4j.Logger;
import util.DateFormatHelper;

/**
 *
 * @author Cesardl
 * @param <T>
 */
public interface Controller<T> {

    DateFormatHelper dateFormatHelper = DateFormatHelper.getInstance();

    Logger log = Logger.getLogger(Controller.class);

    Object[][] getAll();

    T getByCode(String code);

    boolean saveOrUpdate(T entity);

    boolean delete(String code);

    boolean existsCode(String code);

}
