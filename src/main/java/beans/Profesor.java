package beans;

import java.io.Serializable;

/**
 *
 * @author cesardl
 */
public class Profesor implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String codigo;
    private String nombre;
    private int edad;
    private String email;

    public Profesor() {
    }

    public Profesor(int id, String codigo, String nombre, int edad, String email) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
