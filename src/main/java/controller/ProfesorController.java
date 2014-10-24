package controller;

import beans.Profesor;
import dao.ProfesorDAO;
import factory.DAOFactory;
import java.util.List;

/**
 *
 * @author cesardl
 */
public class ProfesorController {

    public Object[][] dataProfesor() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProfesorDAO dao = factory.getProfesorDAO();

        List<Profesor> profesores = dao.seleccionarDatos();

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

    public String[] nombresProfesor() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProfesorDAO dao = factory.getProfesorDAO();

        List<Profesor> profesores = dao.seleccionarNombresProfesor();

        String[] nomProfes = new String[profesores.size()];
        for (int i = 0; i < profesores.size(); i++) {
            Profesor profesor = profesores.get(i);
            nomProfes[i] = profesor.getNombre();
        }
        return nomProfes;
    }
}
