package beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Cesardl
 */
public class Teacher extends Person implements Serializable {

    private static final long serialVersionUID = 1L;
    private String email;

    public Teacher() {
        super();
    }

    public Teacher(int id, String code, String names, Date birthday, String email) {
        super(id, code, names, birthday);
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
        return names;
    }

}
