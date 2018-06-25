package org.sanmarcux.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Cesardl
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Integer id;
    protected String code;
    protected String names;
    protected Date birthday;

    /**
     * Default constructor
     */
    public Person() {
        id = 0;
    }

    /**
     * Constructor class with fields.
     *
     * @param id       the id
     * @param code     the code
     * @param names    the name and last name
     * @param birthday the birthday date
     */
    public Person(Integer id, String code, String names, Date birthday) {
        this.id = id;
        this.code = code;
        this.names = names;
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public Date getBirthday() {
        if (birthday == null) {
            return null;
        } else {
            return new Date(birthday.getTime());
        }
    }

    public void setBirthday(Date birthday) {
        if (birthday == null) {
            this.birthday = null;
        } else {
            this.birthday = new Date(birthday.getTime());
        }
    }

}
