package dao;

import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Cesardl
 * @param <T>
 */
public interface DAO<T> {

    Logger log = Logger.getLogger(DAO.class);

    List<T> seleccionarDatos();

}
