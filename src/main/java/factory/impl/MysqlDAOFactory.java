package factory.impl;

import dao.StudentDAO;
import dao.TeacherDAO;
import dao.impl.StudentDAOImpl;
import dao.impl.TeacherDAOImpl;
import factory.DAOFactory;

/**
 *
 * @author Cesardl
 */
public class MysqlDAOFactory extends DAOFactory {

    @Override
    public StudentDAO getStudentDAO() {
        return new StudentDAOImpl();
    }

    @Override
    public TeacherDAO getTeacherDAO() {
        return new TeacherDAOImpl();
    }

}
