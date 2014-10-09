/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Profesor;
import dao.ProfesorDAO;
import java.util.ArrayList;

/**
 *
 * @author cesardl
 */
public class ProfesorController {

    ProfesorDAO dao = new ProfesorDAO();

    public Object[][] dataProfesor() {
        ArrayList<Profesor> profesores = new ArrayList<Profesor>(dao.seleccionarDatos());

        Object[][] rowData = new Object[profesores.size()][4];

        for (int i = 0; i < profesores.size(); i++) {
            Profesor profesor = profesores.get(i);

            rowData[i][0] = profesor.getCod_prof();
            rowData[i][1] = profesor.getNom_prof();
            rowData[i][2] = profesor.getEdad_prof();
            rowData[i][3] = profesor.getEmail_prof();
        }
        return rowData;
    }

    public String[] nombresProfesor() {
        ArrayList<Profesor> profesores = new ArrayList<Profesor>(dao.seleccionarNombresProfesor());

        String[] nomProfes = new String[profesores.size()];
        for (int i = 0; i < profesores.size(); i++) {
            Profesor profesor = profesores.get(i);
            nomProfes[i] = profesor.getNom_prof();
        }
        return nomProfes;
    }
}
