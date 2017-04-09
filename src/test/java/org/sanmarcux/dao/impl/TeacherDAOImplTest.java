package org.sanmarcux.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sanmarcux.beans.Teacher;
import org.sanmarcux.dao.TeacherDAO;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

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
        assertEquals(5, result.size());
    }

    @Test
    public void testSelectByCode() {
        Teacher teacher = dao.selectByCode("212399");
        assertNotNull(teacher);
    }

    @Test
    public void testSelectNames() {
        List<Teacher> result = dao.selectNames();
        assertFalse(result.isEmpty());
        assertEquals(5, result.size());
    }

    @Test
    public void testInsert() {

    }

    @Test
    public void testUpdate() {

    }

    @Test
    public void testDelete() {

    }

    @Test
    public void testSelectIdByCode() {

    }

    @After
    public void tearDown() {
        db.shutdown();
    }
}