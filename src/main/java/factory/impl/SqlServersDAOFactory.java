package factory.impl;

import dao.StudentDAO;
import dao.TeacherDAO;
import factory.DAOFactory;

/**
 *
 * @author Cesardl
 */
public class SqlServersDAOFactory extends DAOFactory {

    @Override
    public StudentDAO getAlumnoDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TeacherDAO getProfesorDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
