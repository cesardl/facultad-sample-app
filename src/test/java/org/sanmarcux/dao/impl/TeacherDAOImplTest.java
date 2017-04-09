package org.sanmarcux.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.sanmarcux.dao.TeacherDAO;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

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

    }

    @Test
    public void testSelectByCode() {

    }

    @Test
    public void testSelectNames() {

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
}