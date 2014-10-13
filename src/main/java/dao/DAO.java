package dao;

import java.util.List;

/**
 *
 * @author Cesardl
 * @param <T>
 */
public interface DAO<T> {

    List<T> seleccionarDatos();

}
