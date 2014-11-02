package dao;

import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Cesardl
 * @param <T>
 */
public interface DAO<T> {

    int STATE_ERROR = -1;
    
    Logger log = Logger.getLogger(DAO.class);

    List<T> getAll();

    int insert(T entity);
    
}
