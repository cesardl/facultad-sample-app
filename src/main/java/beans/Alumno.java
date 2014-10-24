package beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author cesardl
 */
public class Alumno extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    private char sexo;
    private String direccion;
    private String telefono;
    private int profesor;

    public Alumno(int id, String codigo, String nombre, Date nacimiento, char sexo, String direccion, String telefono) {
        super(id, codigo, nombre, nacimiento);
        this.sexo = sexo;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getProfesor() {
        return profesor;
    }

    public void setProfesor(int profesor) {
        this.profesor = profesor;
    }

}
