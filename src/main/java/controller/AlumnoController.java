package controller;

import beans.Alumno;
import dao.AlumnoDAO;
import factory.DAOFactory;
import db.Database;
import java.util.List;

/**
 *
 * @author cesardl
 */
public class AlumnoController {

    public Object[][] dataAlumno() {
        DAOFactory factory = DAOFactory.getDAOFactory(Database.MYSQL);
        AlumnoDAO dao = factory.getAlumnoDAO();

        List<Alumno> alumnos = dao.seleccionarDatos();

        Object[][] rowData = new Object[alumnos.size()][6];

        for (int i = 0; i < alumnos.size(); i++) {
            Alumno alumno = alumnos.get(i);

            rowData[i][0] = alumno.getCodigo();
            rowData[i][1] = alumno.getNombre();
            rowData[i][2] = alumno.getEdad();
            rowData[i][3] = alumno.getSexo();
            rowData[i][4] = alumno.getDireccion();
            rowData[i][5] = alumno.getTelefono();
        }

        return rowData;
    }
}
