package org.sanmarcux.controller.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.sanmarcux.beans.Student;
import org.sanmarcux.dao.impl.StudentDAOImpl;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.sanmarcux.PojoFake.CODE;
import static org.sanmarcux.PojoFake.fakeStudent;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerImplTest {

    @InjectMocks
    private StudentControllerImpl controller;

    @Mock
    private StudentDAOImpl dao;

    @Test
    public void testGetByCode() {
        when(dao.selectByCode(anyString())).thenReturn(fakeStudent());
        Student result = controller.getByCode("20102");
        assertNotNull(result);
    }

    @Test
    public void testSave() {
        when(dao.insert(any(Student.class))).thenReturn(1);

        Student entity = fakeStudent();
        entity.setId(0);

        boolean result = controller.saveOrUpdate(entity);
        assertTrue(result);
    }

    @Test
    public void testUpdate() {
        when(dao.update(any(Student.class))).thenReturn(1);

        Student entity = fakeStudent();

        boolean result = controller.saveOrUpdate(entity);
        assertTrue(result);
    }

    @Test
    public void testSaveOrUpdateFailed() {
        when(dao.update(any(Student.class))).thenReturn(0);

        Student entity = fakeStudent();

        boolean result = controller.saveOrUpdate(entity);
        assertFalse(result);
    }

    @Test
    public void testDelete() {
        when(dao.delete(any(Student.class))).thenReturn(1);

        boolean result = controller.delete(CODE);
        assertTrue(result);
    }

    @Test
    public void testDeleteFailed() {
        when(dao.delete(any(Student.class))).thenReturn(0);

        boolean result = controller.delete(CODE);
        assertFalse(result);
    }
}
