package org.sanmarcux.beans;

import java.util.Date;

/**
 * @author Cesardl
 */
public class Person {

    protected int id;
    protected String code;
    protected String names;
    protected Date birthday;

    /**
     * Default constructor
     */
    public Person() {
        id = -1;
    }

    /**
     * Constructor class with fields.
     *
     * @param id the id
     * @param code the code
     * @param names the name and last name
     * @param birthday the birthday date
     */
    public Person(int id, String code, String names, Date birthday) {
        this.id = id;
        this.code = code;
        this.names = names;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}
