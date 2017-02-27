package org.sanmarcux.factory.impl;

import org.sanmarcux.dao.StudentDAO;
import org.sanmarcux.dao.TeacherDAO;
import org.sanmarcux.factory.DAOFactory;

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
