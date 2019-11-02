package org.sanmarcux.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Cesardl
 */
public class Teacher extends Person implements Serializable {

    private static final long serialVersionUID = 1L;
    private String email;
    private Integer assignedStudents;

    /**
     * Default constructor
     */
    public Teacher() {
        super();
        assignedStudents = 0;
    }

    /**
     * Constructor class with fields.
     *
     * @param id               the id
     * @param code             the code
     * @param names            the name and last name
     * @param birthday         the birthday date
     * @param email            the email
     * @param assignedStudents the count of assigned students
     */
    public Teacher(Integer id, String code, String names, Date birthday, String email, Integer assignedStudents) {
        super(id, code, names, birthday);
        this.email = email;
        this.assignedStudents = assignedStudents;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAssignedStudents() {
        return assignedStudents;
    }

    public void setAssignedStudents(Integer assignedStudents) {
        this.assignedStudents = assignedStudents;
    }

    @Override
    public String toString() {
        return names;
    }

}
