package org.sanmarcux.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.sanmarcux.beans.Student;
import org.sanmarcux.beans.etc.Gender;
import org.sanmarcux.controller.DialogAction;
import org.sanmarcux.controller.StudentController;
import org.sanmarcux.util.DateFormatHelper;
import org.sanmarcux.util.FormSupport;
import org.sanmarcux.util.ResourceBundleHelper;

import javax.swing.table.DefaultTableModel;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.sanmarcux.PojoFake.fakeStudent;

// Now that JPanelStudent takes its dependencies via constructor (see
// JPanelBase), it's constructed directly with mocks - no Spring context or
// embedded DB needed for a panel-logic unit test.
@RunWith(MockitoJUnitRunner.class)
public class JPanelStudentTest {

    @Mock
    private StudentController studentController;

    @Mock
    private JDialogStudent dialog;

    @Mock
    private DateFormatHelper dateFormatHelper;

    @Mock
    private ResourceBundleHelper bundle;

    @Mock
    private FormSupport formSupport;

    private JPanelStudent panel;

    @Before
    public void setup() {
        when(studentController.getAll()).thenReturn(new Object[0][6]);
        when(bundle.getString(anyString())).thenReturn("");

        panel = new JPanelStudent(studentController, dialog, dateFormatHelper, bundle, formSupport);
    }

    @Test
    public void testAddRow() {
        Date birthday = new Date();

        Student entity = new Student();
        entity.setBirthday(birthday);

        when(dateFormatHelper.format(Mockito.any(Date.class))).thenReturn("2017-abr-14");

        panel.addRow(entity);

        DefaultTableModel tableModel = (DefaultTableModel) panel.getTable().getModel();
        assertEquals(1, tableModel.getRowCount());
        assertEquals("2017-abr-14", tableModel.getValueAt(0, 2));
        assertEquals(Gender.MALE.getValue(), tableModel.getValueAt(0, 3));
    }

    @Test
    public void testSetRowValues() {
        when(dateFormatHelper.format(Mockito.any(Date.class))).thenReturn("2017-abr-14");

        panel.addRow(fakeStudent()); // seed row 0

        Student updated = fakeStudent();
        updated.setCode("999999");
        updated.setNames("Updated Name");
        updated.setAddress("Updated Address");
        updated.setPhone("000000000");
        updated.setGender(Gender.FEMALE);

        panel.setRowValues(0, updated);

        DefaultTableModel tableModel = (DefaultTableModel) panel.getTable().getModel();
        assertEquals("999999", tableModel.getValueAt(0, 0));
        assertEquals("Updated Name", tableModel.getValueAt(0, 1));
        assertEquals("2017-abr-14", tableModel.getValueAt(0, 2));
        assertEquals(Gender.FEMALE.getValue(), tableModel.getValueAt(0, 3));
        assertEquals("Updated Address", tableModel.getValueAt(0, 4));
        assertEquals("000000000", tableModel.getValueAt(0, 5));
    }

    @Test
    public void testDeleteRow() {
        // deleteRow() pops a blocking JOptionPane confirm dialog - not
        // exercised here, same limitation as before this refactor.
    }

    @Test
    public void testShowDialogForInsert() {
        doNothing().when(dialog).setVisible(true);

        panel.showDialog(DialogAction.INSERT, 1, null);
        verify(dialog, times(1)).getEntity();
    }

    @Test
    public void testShowDialogForUpdate() {
        doNothing().when(dialog).setVisible(true);

        panel.showDialog(DialogAction.UPDATE, 1, "200135");
        verify(dialog, times(1)).getEntity();
    }
}
