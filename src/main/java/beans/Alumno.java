/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

/**
 *
 * @author cesardl
 */
public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id_alum;
    private String cod_alum;
    private String nom_alum;
    private int edad_alum;
    private char sexo_alum;
    private String direc_alum;
    private String telef_alum;
    private int profesor_id_prof;

    public Alumno(int id_alum, String cod_alum, String nom_alum, int edad_alum, char sexo_alum, String direc_alum, String telef_alum) {
        this.id_alum = id_alum;
        this.cod_alum = cod_alum;
        this.nom_alum = nom_alum;
        this.edad_alum = edad_alum;
        this.sexo_alum = sexo_alum;
        this.direc_alum = direc_alum;
        this.telef_alum = telef_alum;
    }

    public String getCod_alum() {
        return cod_alum;
    }

    public void setCod_alum(String cod_alum) {
        this.cod_alum = cod_alum;
    }

    public String getDirec_alum() {
        return direc_alum;
    }

    public void setDirec_alum(String direc_alum) {
        this.direc_alum = direc_alum;
    }

    public int getEdad_alum() {
        return edad_alum;
    }

    public void setEdad_alum(int edad_alum) {
        this.edad_alum = edad_alum;
    }

    public int getId_alum() {
        return id_alum;
    }

    public void setId_alum(int id_alum) {
        this.id_alum = id_alum;
    }

    public String getNom_alum() {
        return nom_alum;
    }

    public void setNom_alum(String nom_alum) {
        this.nom_alum = nom_alum;
    }

    public int getProfesor_id_prof() {
        return profesor_id_prof;
    }

    public void setProfesor_id_prof(int profesor_id_prof) {
        this.profesor_id_prof = profesor_id_prof;
    }

    public char getSexo_alum() {
        return sexo_alum;
    }

    public void setSexo_alum(char sexo_alum) {
        this.sexo_alum = sexo_alum;
    }

    public String getTelef_alum() {
        return telef_alum;
    }

    public void setTelef_alum(String telef_alum) {
        this.telef_alum = telef_alum;
    }
}
