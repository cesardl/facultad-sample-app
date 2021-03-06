package org.sanmarcux.controller.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.sanmarcux.beans.Teacher;
import org.sanmarcux.dao.impl.TeacherDAOImpl;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.sanmarcux.PojoFake.CODE;
import static org.sanmarcux.PojoFake.fakeTeacher;

@RunWith(MockitoJUnitRunner.class)
public class TeacherControllerImplTest {

    @InjectMocks
    private TeacherControllerImpl controller;

    @Mock
    private TeacherDAOImpl dao;

    @Test
    public void testGetByCode() {
        when(dao.selectByCode(anyString())).thenReturn(fakeTeacher());
        Teacher result = controller.getByCode("20102");
        assertNotNull(result);
    }

    @Test
    public void testSave() {
        when(dao.insert(any(Teacher.class))).thenReturn(1);

        Teacher entity = fakeTeacher();
        entity.setId(0);

        boolean result = controller.saveOrUpdate(entity);
        assertTrue(result);
    }

    @Test
    public void testUpdate() {
        when(dao.update(any(Teacher.class))).thenReturn(1);

        Teacher entity = fakeTeacher();

        boolean result = controller.saveOrUpdate(entity);
        assertTrue(result);
    }

    @Test
    public void testSaveOrUpdateFailed() {
        when(dao.update(any(Teacher.class))).thenReturn(0);

        Teacher entity = fakeTeacher();

        boolean result = controller.saveOrUpdate(entity);
        assertFalse(result);
    }

    @Test
    public void testDelete() {
        when(dao.delete(any(Teacher.class))).thenReturn(1);

        boolean result = controller.delete(CODE);
        assertTrue(result);
    }

    @Test
    public void testDeleteWithAssignedStudents() {
        when(dao.findAssignedStudents(anyString())).thenReturn(10);

        boolean result = controller.delete(CODE);
        assertFalse(result);
    }

    @Test
    public void testDeleteFailed() {
        when(dao.delete(any(Teacher.class))).thenReturn(0);

        boolean result = controller.delete(CODE);
        assertFalse(result);
    }
}
