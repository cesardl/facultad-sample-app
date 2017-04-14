package org.sanmarcux;

import org.sanmarcux.beans.Student;
import org.sanmarcux.beans.Teacher;

import java.util.Calendar;
import java.util.Date;

/**
 * Created on 14/04/2017.
 *
 * @author Cesardl
 */
public class PojoFake {

    public static final String CODE = "000001";
    public static final String NAME = "Fake name";
    public static final String ADDRESS = "Fake address";
    public static final String EMAIL = "fake@email.org";
    public static final Date BIRTHDAY;

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1988);
        calendar.set(Calendar.MONTH, Calendar.MAY);
        calendar.set(Calendar.DAY_OF_MONTH, 9);

        BIRTHDAY = calendar.getTime();
    }

    public static Student fakeStudent() {
        Student student = new Student();
        student.setCode(CODE);
        student.setNames(NAME);
        student.setBirthday(BIRTHDAY);
        student.setAddress(ADDRESS);
        return student;
    }

    public static Teacher fakeTeacher() {
        Teacher teacher = new Teacher();
        teacher.setCode(CODE);
        teacher.setNames(NAME);
        teacher.setBirthday(BIRTHDAY);
        teacher.setEmail(EMAIL);
        return teacher;
    }
}
