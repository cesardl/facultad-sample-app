package factory.impl;

import dao.AlumnoDAO;
import dao.ProfesorDAO;
import dao.impl.AlumnoDAOImpl;
import dao.impl.ProfesorDAOImpl;
import factory.DAOFactory;

/**
 *
 * @author Cesardl
 */
public class MysqlDAOFactory extends DAOFactory {

    @Override
    public AlumnoDAO getAlumnoDAO() {
        return new AlumnoDAOImpl();
    }

    @Override
    public ProfesorDAO getProfesorDAO() {
        return new ProfesorDAOImpl();
    }

}
