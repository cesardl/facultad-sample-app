package org.sanmarcux.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sanmarcux.beans.Teacher;
import org.sanmarcux.controller.DialogAction;
import org.sanmarcux.controller.TeacherController;
import org.sanmarcux.util.FormSupport;
import org.sanmarcux.util.ResourceBundleHelper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.sanmarcux.PojoFake.fakeTeacher;

// Now that JDialogTeacher takes its dependencies via constructor (see
// JDialogFormBase), it's constructed directly with mocks - no Spring context
// or embedded DB needed for a validation-logic unit test. FormSupport is a
// real instance (not a mock): its regex is exactly what this test exercises
// for the email-format cases.
@RunWith(MockitoJUnitRunner.class)
public class JDialogTeacherTest {

    @Mock
    private TeacherController teacherController;
    @Mock
    private ResourceBundleHelper bundle;

    private JDialogTeacher dialog;

    @Before
    public void setup() {
        when(bundle.getString(anyString())).thenReturn("empty code");

        dialog = new JDialogTeacher(new FormSupport(), bundle, teacherController);
        dialog.setAction(DialogAction.INSERT);
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
        when(teacherController.existsCode("212963")).thenReturn(true);
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
