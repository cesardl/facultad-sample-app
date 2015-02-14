package dao;

import beans.Teacher;
import java.util.List;

/**
 *
 * @author Cesardl
 */
public interface TeacherDAO extends DAO<Teacher> {

    List<Teacher> selectNames();

}
