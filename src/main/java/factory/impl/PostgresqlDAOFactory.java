package factory.impl;

import dao.StudentDAO;
import dao.TeacherDAO;
import factory.DAOFactory;

/**
 *
 * @author Cesardl
 */
public class PostgresqlDAOFactory extends DAOFactory {

    @Override
    public StudentDAO getStudentDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TeacherDAO getTeacherDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
