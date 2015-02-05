package dao;

import beans.Profesor;
import java.util.List;

/**
 *
 * @author Cesardl
 */
public interface ProfesorDAO extends DAO<Profesor> {

    List<Profesor> selectNames();

}
