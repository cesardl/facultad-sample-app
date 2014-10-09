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
public class Profesor implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id_prof;
    private String cod_prof;
    private String nom_prof;
    private int edad_prof;
    private String email_prof;

    public Profesor() {
    }

    public Profesor(int id_prof, String cod_prof, String nom_prof, int edad_prof, String email_prof) {
        this.id_prof = id_prof;
        this.cod_prof = cod_prof;
        this.nom_prof = nom_prof;
        this.edad_prof = edad_prof;
        this.email_prof = email_prof;
    }

    public String getCod_prof() {
        return cod_prof;
    }

    public void setCod_prof(String cod_prof) {
        this.cod_prof = cod_prof;
    }

    public int getEdad_prof() {
        return edad_prof;
    }

    public void setEdad_prof(int edad_prof) {
        this.edad_prof = edad_prof;
    }

    public String getEmail_prof() {
        return email_prof;
    }

    public void setEmail_prof(String email_prof) {
        this.email_prof = email_prof;
    }

    public int getId_prof() {
        return id_prof;
    }

    public void setId_prof(int id_prof) {
        this.id_prof = id_prof;
    }

    public String getNom_prof() {
        return nom_prof;
    }

    public void setNom_prof(String nom_prof) {
        this.nom_prof = nom_prof;
    }
}

