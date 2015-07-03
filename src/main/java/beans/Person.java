package beans;

import java.util.Date;

/**
 *
 * @author Cesardl
 */
public class Person {

    protected int id;
    protected String code;
    protected String names;
    protected Date birthday;

    public Person() {
    }

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
