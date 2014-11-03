package beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author cesardl
 */
public class Profesor extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    private String email;

    public Profesor() {
        super();
    }

    public Profesor(int id, String codigo, String nombre, Date nacimiento, String email) {
        super(id, codigo, nombre, nacimiento);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
