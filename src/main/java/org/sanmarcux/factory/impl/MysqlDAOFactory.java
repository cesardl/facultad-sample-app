package org.sanmarcux.factory.impl;

import org.sanmarcux.dao.StudentDAO;
import org.sanmarcux.dao.TeacherDAO;
import org.sanmarcux.dao.impl.StudentDAOImpl;
import org.sanmarcux.dao.impl.TeacherDAOImpl;
import org.sanmarcux.factory.DAOFactory;

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
