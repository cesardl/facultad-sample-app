package org.sanmarcux.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.sanmarcux.beans.Teacher;
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
import static org.sanmarcux.PojoFake.fakeTeacher;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, DatabaseTestConfig.class})
public class JDialogTeacherTest {

    @InjectMocks
    @Autowired
    private JDialogTeacher dialog;

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
        Teacher teacher = fakeTeacher();
        dialog.setEntity(teacher);

        assertTrue(dialog.validateData());
        verify(bundle, never()).getString(eq("app.warning.teacher.code.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.code.already.exists"));
        verify(bundle, never()).getString(eq("app.warning.teacher.name.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.birthday.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.email.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.email.wrong.format"));
    }

    @Test
    public void testValidateDataEmptyCode() {
        Teacher teacher = fakeTeacher();
        teacher.setCode("");
        dialog.setEntity(teacher);

        assertFalse(dialog.validateData());
        verify(bundle, atLeastOnce()).getString(eq("app.warning.teacher.code.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.code.already.exists"));
        verify(bundle, never()).getString(eq("app.warning.teacher.name.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.birthday.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.email.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.email.wrong.format"));
    }

    @Test
    public void testValidateDataExistsCode() {
        Teacher teacher = fakeTeacher();
        teacher.setCode("212963");
        dialog.setEntity(teacher);

        assertFalse(dialog.validateData());
        verify(bundle, never()).getString(eq("app.warning.teacher.code.empty"));
        verify(bundle, atLeastOnce()).getString(eq("app.warning.teacher.code.already.exists"));
        verify(bundle, never()).getString(eq("app.warning.teacher.name.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.birthday.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.email.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.email.wrong.format"));
    }

    @Test
    public void testValidateDataEmptyName() {
        Teacher teacher = fakeTeacher();
        teacher.setNames("");
        dialog.setEntity(teacher);

        assertFalse(dialog.validateData());
        verify(bundle, never()).getString(eq("app.warning.teacher.code.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.code.already.exists"));
        verify(bundle, atLeastOnce()).getString(eq("app.warning.teacher.name.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.birthday.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.email.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.email.wrong.format"));
    }

    @Test
    public void testValidateDataEmptyBirthday() {
        Teacher teacher = fakeTeacher();
        teacher.setBirthday(null);
        dialog.setEntity(teacher);

        assertFalse(dialog.validateData());
        verify(bundle, never()).getString(eq("app.warning.teacher.code.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.code.already.exists"));
        verify(bundle, never()).getString(eq("app.warning.teacher.name.empty"));
        verify(bundle, atLeastOnce()).getString(eq("app.warning.teacher.birthday.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.email.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.email.wrong.format"));
    }

    @Test
    public void testValidateDataEmptyEmail() {
        Teacher teacher = fakeTeacher();
        teacher.setEmail("");
        dialog.setEntity(teacher);

        assertFalse(dialog.validateData());
        verify(bundle, never()).getString(eq("app.warning.teacher.code.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.code.already.exists"));
        verify(bundle, never()).getString(eq("app.warning.teacher.name.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.birthday.empty"));
        verify(bundle, atLeastOnce()).getString(eq("app.warning.teacher.email.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.email.wrong.format"));
    }

    @Test
    public void testValidateDataWrongFormatEmail() {
        Teacher teacher = fakeTeacher();
        teacher.setEmail("anyEmail");
        dialog.setEntity(teacher);

        assertFalse(dialog.validateData());
        verify(bundle, never()).getString(eq("app.warning.teacher.code.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.code.already.exists"));
        verify(bundle, never()).getString(eq("app.warning.teacher.name.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.birthday.empty"));
        verify(bundle, never()).getString(eq("app.warning.teacher.email.empty"));
        verify(bundle, atLeastOnce()).getString(eq("app.warning.teacher.email.wrong.format"));
    }
}