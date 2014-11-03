package controller.impl;

import beans.Profesor;
import controller.ProfesorController;
import dao.DAO;
import dao.ProfesorDAO;
import factory.DAOFactory;
import java.util.List;
import util.Utils;

/**
 *
 * @author Cesardl
 */
public class ProfesorControllerImpl implements ProfesorController {

    @Override
    public Object[][] data() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProfesorDAO dao = factory.getProfesorDAO();

        List<Profesor> profesores = dao.getAll();

        Object[][] rowData = new Object[profesores.size()][4];

        for (int i = 0; i < profesores.size(); i++) {
            Profesor profesor = profesores.get(i);

            rowData[i][0] = profesor.getCodigo();
            rowData[i][1] = profesor.getNombre();
            rowData[i][2] = Utils.formatDate(profesor.getNacimiento());
            rowData[i][3] = profesor.getEmail();
        }
        return rowData;
    }

    @Override
    public Profesor[] nombresProfesor() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProfesorDAO dao = factory.getProfesorDAO();

        List<Profesor> profesores = dao.seleccionarNombresProfesor();

        return profesores.toArray(new Profesor[profesores.size()]);
    }

    @Override
    public boolean insert(Profesor entity) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProfesorDAO dao = factory.getProfesorDAO();

        int state = dao.insert(entity);

        switch (state) {
            case DAO.STATE_ERROR:
                return false;

            default:
                return true;
        }
    }

}
