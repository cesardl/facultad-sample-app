package org.sanmarcux.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sanmarcux.beans.Teacher;
import org.sanmarcux.dao.TeacherDAO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.List;

import static org.junit.Assert.*;
import static org.sanmarcux.PojoFake.*;

public class TeacherDAOImplTest {

    private TeacherDAO dao;
    private EmbeddedDatabase db;

    @Before
    public void setUp() {
        // creates an HSQL in-memory database populated from default scripts
        // classpath:schema.sql and classpath:data.sql
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .generateUniqueName(true)
                .addScript("data/script_teacher.sql")
                .build();

        dao = new TeacherDAOImpl(db);
    }

    @Test
    public void testSelectAll() {
        List<Teacher> result = dao.selectAll();
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
    }

    @Test
    public void testSelectByCode() {
        Teacher teacher = dao.selectByCode("212696");
        assertNotNull(teacher);
    }

    @Test
    public void testSelectNames() {
        List<Teacher> result = dao.selectNames();
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
    }

    @Test
    public void testInsert() {
        Teacher teacher = fakeTeacher();

        int result = dao.insert(teacher);
        assertEquals(1, result);

        Teacher entity = dao.selectByCode(CODE);
        assertNotNull(entity.getId());
        assertNotNull(entity.getCode());
        assertNotNull(entity.getNames());
        assertNotNull(entity.getEmail());
        assertNotNull(entity.getBirthday());

        assertEquals(CODE, entity.getCode());
        assertEquals(NAME, entity.getNames());
        assertEquals(EMAIL, entity.getEmail());
    }

    @Test
    public void testUpdate() {
        Teacher teacher = fakeTeacher();
        teacher.setId(1);
        teacher.setCode("212399");
        teacher.setNames(NAME);
        teacher.setEmail(EMAIL);

        int result = dao.update(teacher);
        assertEquals(1, result);

        Teacher entity = dao.selectByCode("212399");
        assertNotNull(entity.getNames());
        assertNotNull(entity.getEmail());
        assertEquals(NAME, entity.getNames());
        assertEquals(EMAIL, entity.getEmail());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testDelete() {
        Teacher teacher = new Teacher();
        teacher.setCode("212963");

        int result = dao.delete(teacher);
        assertEquals(1, result);

        dao.selectByCode("212963");
    }

    @Test
    public void testSelectIdByCode() {
        int result = dao.selectIdByCode("212456");
        assertEquals(3, result);
    }

    @After
    public void tearDown() {
        db.shutdown();
    }
}