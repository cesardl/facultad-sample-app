/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Alumno;
import dao.AlumnoDAO;
import java.util.ArrayList;

/**
 *
 * @author cesardl
 */
public class AlumnoController {

    private AlumnoDAO dao = new AlumnoDAO();

    public Object[][] dataAlumno() {
        ArrayList<Alumno> alumnos = new ArrayList<Alumno>(dao.seleccionarDatos());

        Object[][] rowData = new Object[alumnos.size()][6];

        for (int i = 0; i < alumnos.size(); i++) {
            Alumno alumno = alumnos.get(i);
            
            rowData[i][0] = alumno.getCod_alum();
            rowData[i][1] = alumno.getNom_alum();
            rowData[i][2] = alumno.getEdad_alum();
            rowData[i][3] = alumno.getSexo_alum();
            rowData[i][4] = alumno.getDirec_alum();
            rowData[i][5] = alumno.getTelef_alum();
        }
        return rowData;
    }
}
