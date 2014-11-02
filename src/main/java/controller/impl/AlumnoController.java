package controller.impl;

import beans.Alumno;
import controller.Controller;
import dao.AlumnoDAO;
import dao.DAO;
import factory.DAOFactory;
import java.util.List;
import util.Utils;

/**
 *
 * @author cesardl
 */
public class AlumnoController implements Controller<Alumno> {

    @Override
    public Object[][] data() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        AlumnoDAO dao = factory.getAlumnoDAO();

        List<Alumno> alumnos = dao.getAll();

        Object[][] rowData = new Object[alumnos.size()][6];

        for (int i = 0; i < alumnos.size(); i++) {
            Alumno alumno = alumnos.get(i);

            rowData[i][0] = alumno.getCodigo();
            rowData[i][1] = alumno.getNombre();
            rowData[i][2] = Utils.formatDate(alumno.getNacimiento());
            rowData[i][3] = alumno.getSexo();
            rowData[i][4] = alumno.getDireccion();
            rowData[i][5] = alumno.getTelefono();
        }

        return rowData;
    }

    @Override
    public boolean insert(Alumno entity) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        AlumnoDAO dao = factory.getAlumnoDAO();

        int state = dao.insert(entity);

        switch (state) {
            case DAO.STATE_ERROR:
                return false;

            default:
                return true;
        }
    }
}
