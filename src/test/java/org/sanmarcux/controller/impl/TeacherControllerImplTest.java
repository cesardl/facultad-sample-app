package org.sanmarcux.controller.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sanmarcux.beans.Teacher;
import org.sanmarcux.dao.impl.TeacherDAOImpl;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
    public void testSaveOrUpdateDuplicateCodeReturnsFalse() {
        // corner case: another insert took the same code between the
        // dialog's existsCode() check and this saveOrUpdate() call
        when(dao.insert(any(Teacher.class))).thenThrow(new DataIntegrityViolationException("unique constraint"));

        Teacher entity = fakeTeacher();
        entity.setId(0);

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
