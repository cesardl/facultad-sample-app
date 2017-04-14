package org.sanmarcux.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sanmarcux.beans.Student;
import org.sanmarcux.beans.etc.Gender;
import org.sanmarcux.dao.StudentDAO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.List;

import static org.junit.Assert.*;
import static org.sanmarcux.PojoFake.*;

public class StudentDAOImplTest {

    private StudentDAO dao;
    private EmbeddedDatabase db;

    @Before
    public void setUp() {
        // creates an HSQL in-memory database populated from default scripts
        // classpath:schema.sql and classpath:data.sql
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .generateUniqueName(true)
                .addScript("data/script_student.sql")
                .build();

        dao = new StudentDAOImpl(db);
    }

    @Test
    public void testSelectAll() {
        List<Student> result = dao.selectAll();

        assertFalse(result.isEmpty());
        assertEquals(5, result.size());
    }

    @Test
    public void testSelectByCode() {
        Student result = dao.selectByCode("200004");
        assertNotNull(result);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testEmptyResultSelectByCode() {
        dao.selectByCode("xxxxx");
    }

    @Test
    public void testInsert() {
        Student student = fakeStudent();

        int result = dao.insert(student);
        assertEquals(1, result);

        Student entity = dao.selectByCode(CODE);
        assertNotNull(entity.getId());
        assertNotNull(entity.getCode());
        assertNotNull(entity.getGender());
        assertNotNull(entity.getBirthday());
        assertNotNull(entity.getNames());
        assertNotNull(entity.getAddress());

        assertEquals(CODE, entity.getCode());
        assertEquals(Gender.MALE, entity.getGender());
        assertEquals(NAME, entity.getNames());
        assertEquals(ADDRESS, entity.getAddress());
    }

    @Test
    public void testUpdate() {
        Student student = fakeStudent();
        student.setId(1);
        student.setCode("200004");
        student.setNames(NAME);
        student.setAddress(ADDRESS);
        student.setGender(Gender.FEMALE);

        int result = dao.update(student);
        assertEquals(1, result);

        Student entity = dao.selectByCode("200004");
        assertNotNull(entity.getNames());
        assertNotNull(entity.getAddress());
        assertNotNull(entity.getGender());
        assertEquals(NAME, entity.getNames());
        assertEquals(ADDRESS, entity.getAddress());
        assertEquals(Gender.FEMALE, entity.getGender());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testDelete() {
        Student student = new Student();
        student.setCode("200004");

        int result = dao.delete(student);
        assertEquals(1, result);

        dao.selectByCode("200004");
    }

    @Test
    public void testSelectIdByCode() {
        int result = dao.selectIdByCode("231123");
        assertEquals(5, result);
    }

    @After
    public void tearDown() {
        db.shutdown();
    }
}