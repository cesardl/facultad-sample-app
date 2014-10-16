package factory.impl;

import dao.AlumnoDAO;
import dao.ProfesorDAO;
import factory.DAOFactory;

/**
 *
 * @author Cesardl
 */
public class OracleDAOFactory extends DAOFactory {

    @Override
    public AlumnoDAO getAlumnoDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProfesorDAO getProfesorDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
