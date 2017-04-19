package org.sanmarcux.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.sanmarcux.beans.Student;
import org.sanmarcux.controller.DialogAction;
import org.sanmarcux.init.AppConfig;
import org.sanmarcux.init.DatabaseTestConfig;
import org.sanmarcux.util.ResourceBundleHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.sanmarcux.PojoFake.fakeStudent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, DatabaseTestConfig.class})
public class JDialogStudentTest {

    @InjectMocks
    @Autowired
    private JDialogStudent dialog;

    @Spy
    private ResourceBundleHelper bundle = mock(ResourceBundleHelper.class);

    @Before
    public void setup() {
        dialog.setAction(DialogAction.INSERT);
        MockitoAnnotations.initMocks(this);

        when(bundle.getString(anyString())).thenReturn("empty code");
    }

    @Test
    public void testValidateDataSuccess() {
        Student student = fakeStudent();
        dialog.setEntity(student);

        assertTrue(dialog.validateData());
        verify(bundle, never()).getString(eq("app.warning.student.code.empty"));
        verify(bundle, never()).getString(eq("app.warning.student.code.already.exists"));
        verify(bundle, never()).getString(eq("app.warning.student.name.empty"));
        verify(bundle, never()).getString(eq("app.warning.student.birthday.empty"));
        verify(bundle, never()).getString(eq("app.warning.student.address.empty"));
    }

    @Test
    public void testValidateDataEmptyCode() {
        Student student = fakeStudent();
        student.setCode("");
        dialog.setEntity(student);

        assertFalse(dialog.validateData());
        verify(bundle, atLeastOnce()).getString(eq("app.warning.student.code.empty"));
        verify(bundle, never()).getString(eq("app.warning.student.code.already.exists"));
        verify(bundle, never()).getString(eq("app.warning.student.name.empty"));
        verify(bundle, never()).getString(eq("app.warning.student.birthday.empty"));
        verify(bundle, never()).getString(eq("app.warning.student.address.empty"));
    }

    @Test
    public void testValidateDataExistsCode() {
        Student student = fakeStudent();
        student.setCode("200004");
        dialog.setEntity(student);

        assertFalse(dialog.validateData());
        verify(bundle, never()).getString(eq("app.warning.student.code.empty"));
        verify(bundle, atLeastOnce()).getString(eq("app.warning.student.code.already.exists"));
        verify(bundle, never()).getString(eq("app.warning.student.name.empty"));
        verify(bundle, never()).getString(eq("app.warning.student.birthday.empty"));
        verify(bundle, never()).getString(eq("app.warning.student.address.empty"));
    }

    @Test
    public void testValidateDataEmptyName() {
        Student student = fakeStudent();
        student.setNames("");
        dialog.setEntity(student);

        assertFalse(dialog.validateData());
        verify(bundle, never()).getString(eq("app.warning.student.code.empty"));
        verify(bundle, never()).getString(eq("app.warning.student.code.already.exists"));
        verify(bundle, atLeastOnce()).getString(eq("app.warning.student.name.empty"));
        verify(bundle, never()).getString(eq("app.warning.student.birthday.empty"));
        verify(bundle, never()).getString(eq("app.warning.student.address.empty"));
    }

    @Test
    public void testValidateDataEmptyBirthday() {
        Student student = fakeStudent();
        student.setBirthday(null);
        dialog.setEntity(student);

        assertFalse(dialog.validateData());
        verify(bundle, never()).getString(eq("app.warning.student.code.empty"));
        verify(bundle, never()).getString(eq("app.warning.student.code.already.exists"));
        verify(bundle, never()).getString(eq("app.warning.student.name.empty"));
        verify(bundle, atLeastOnce()).getString(eq("app.warning.student.birthday.empty"));
        verify(bundle, never()).getString(eq("app.warning.student.address.empty"));
    }

    @Test
    public void testValidateDataEmptyAddress() {
        Student student = fakeStudent();
        student.setAddress("");
        dialog.setEntity(student);

        assertFalse(dialog.validateData());
        verify(bundle, never()).getString(eq("app.warning.student.code.empty"));
        verify(bundle, never()).getString(eq("app.warning.student.code.already.exists"));
        verify(bundle, never()).getString(eq("app.warning.student.name.empty"));
        verify(bundle, never()).getString(eq("app.warning.student.birthday.empty"));
        verify(bundle, atLeastOnce()).getString(eq("app.warning.student.address.empty"));
    }
}