package beans;

import java.util.Date;

/**
 *
 * @author Cesardl
 */
public abstract class Person {

    protected int id;
    protected String codigo;
    protected String nombre;
    protected Date nacimiento;

    public Person() {
    }

    public Person(int id, String codigo, String nombre, Date nacimiento) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.nacimiento = nacimiento;
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

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

}
