package beans;

import beans.etc.Gender;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Cesardl
 */
public class Student extends Person implements Serializable {

    private static final long serialVersionUID = 1L;
    private Gender sexo;
    private String direccion;
    private String telefono;
    private int profesor;

    public Student() {
        super();
    }

    public Student(int id, String codigo, String nombre, Date nacimiento, Gender sexo, String direccion, String telefono) {
        super(id, codigo, nombre, nacimiento);
        this.sexo = sexo;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Gender getSexo() {
        return sexo;
    }

    public void setSexo(Gender sexo) {
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
