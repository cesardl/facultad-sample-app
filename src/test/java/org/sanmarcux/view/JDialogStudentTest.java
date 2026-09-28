package org.sanmarcux.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sanmarcux.beans.Student;
import org.sanmarcux.beans.Teacher;
import org.sanmarcux.controller.DialogAction;
import org.sanmarcux.controller.StudentController;
import org.sanmarcux.controller.TeacherController;
import org.sanmarcux.util.ResourceBundleHelper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.sanmarcux.PojoFake.fakeStudent;

// Now that JDialogStudent takes its dependencies via constructor (see
// JDialogFormBase), it's constructed directly with mocks - no Spring context
// or embedded DB needed for a validation-logic unit test.
@RunWith(MockitoJUnitRunner.class)
public class JDialogStudentTest {

    @Mock
    private StudentController studentController;
    @Mock
    private ResourceBundleHelper bundle;
    @Mock
    private TeacherController teacherController;

    private JDialogStudent dialog;

    @Before
    public void setup() {
        when(teacherController.getNames()).thenReturn(new Teacher[0]);
        when(bundle.getString(anyString())).thenReturn("empty code");

        dialog = new JDialogStudent(studentController, bundle, teacherController);
        dialog.setAction(DialogAction.INSERT);
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
        when(studentController.existsCode("200004")).thenReturn(true);
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
