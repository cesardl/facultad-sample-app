package controller.impl;

import beans.Profesor;
import controller.Controller;
import dao.ProfesorDAO;
import factory.DAOFactory;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Cesardl
 */
public class ProfesorController implements Controller<Profesor> {

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
            rowData[i][2] = profesor.getNacimiento();
            rowData[i][3] = profesor.getEmail();
        }
        return rowData;
    }

    public Profesor[] nombresProfesor() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProfesorDAO dao = factory.getProfesorDAO();

        List<Profesor> profesores = dao.seleccionarNombresProfesor();

        return profesores.toArray(new Profesor[profesores.size()]);
    }

    @Override
    public boolean insert(Profesor entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
