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
    private Gender gender;
    private String direction;
    private String phone;
    private int teacherId;

    public Student() {
        super();
    }

    public Student(int id, String code, String names, Date birthday, Gender sexo, String direccion, String telefono) {
        super(id, code, names, birthday);
        this.gender = sexo;
        this.direction = direccion;
        this.phone = telefono;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getTelefono() {
        return phone;
    }

    public void setTelefono(String telefono) {
        this.phone = telefono;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

}
