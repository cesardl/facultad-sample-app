package org.sanmarcux.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.sanmarcux.beans.Teacher;
import org.sanmarcux.controller.DialogAction;
import org.sanmarcux.controller.TeacherController;
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
import static org.sanmarcux.PojoFake.fakeTeacher;

// Now that JPanelTeacher takes its dependencies via constructor (see
// JPanelBase), it's constructed directly with mocks - no Spring context or
// embedded DB needed for a panel-logic unit test.
@RunWith(MockitoJUnitRunner.class)
public class JPanelTeacherTest {

    @Mock
    private TeacherController teacherController;

    @Mock
    private JDialogTeacher dialog;

    @Mock
    private DateFormatHelper dateFormatHelper;

    @Mock
    private ResourceBundleHelper bundle;

    @Mock
    private FormSupport formSupport;

    private JPanelTeacher panel;

    @Before
    public void setup() {
        when(teacherController.getAll()).thenReturn(new Object[0][5]);
        when(bundle.getString(anyString())).thenReturn("");

        panel = new JPanelTeacher(teacherController, dialog, dateFormatHelper, bundle, formSupport);
    }

    @Test
    public void testAddRow() {
        Date birthday = new Date();

        Teacher entity = new Teacher();
        entity.setBirthday(birthday);

        when(dateFormatHelper.format(Mockito.any(Date.class))).thenReturn("2017-abr-14");

        panel.addRow(entity);

        DefaultTableModel tableModel = (DefaultTableModel) panel.getTable().getModel();
        assertEquals(1, tableModel.getRowCount());
        assertEquals("2017-abr-14", tableModel.getValueAt(0, 2));
        assertEquals(0, tableModel.getValueAt(0, 4));
    }

    @Test
    public void testSetRowValues() {
        when(dateFormatHelper.format(Mockito.any(Date.class))).thenReturn("2017-abr-14");

        panel.addRow(fakeTeacher()); // seed row 0

        Teacher updated = fakeTeacher();
        updated.setCode("999999");
        updated.setNames("Updated Name");
        updated.setEmail("updated@example.org");
        updated.setAssignedStudents(3);

        panel.setRowValues(0, updated);

        DefaultTableModel tableModel = (DefaultTableModel) panel.getTable().getModel();
        assertEquals("999999", tableModel.getValueAt(0, 0));
        assertEquals("Updated Name", tableModel.getValueAt(0, 1));
        assertEquals("2017-abr-14", tableModel.getValueAt(0, 2));
        assertEquals("updated@example.org", tableModel.getValueAt(0, 3));
        assertEquals(3, tableModel.getValueAt(0, 4));
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

        panel.showDialog(DialogAction.UPDATE, 1, "212456");
        verify(dialog, times(1)).getEntity();
    }
}
