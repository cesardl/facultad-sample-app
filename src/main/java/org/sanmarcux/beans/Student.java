package org.sanmarcux.beans;

import org.sanmarcux.beans.etc.Gender;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Cesardl
 */
public class Student extends Person implements Serializable {

    private static final long serialVersionUID = 1L;
    private Gender gender;
    private String address;
    private String phone;
    private int teacherId;

    /**
     * Default constructor
     */
    public Student() {
        super();
        this.gender = Gender.MALE;
    }

    /**
     * Constructor class with fields.
     *
     * @param id       the id
     * @param code     the code
     * @param names    the name and last name
     * @param birthday the birthday date
     * @param gender   the gender
     * @param address  the address
     * @param phone    the phone number
     */
    public Student(int id, String code, String names, Date birthday, Gender gender, String address, String phone) {
        super(id, code, names, birthday);
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

}
