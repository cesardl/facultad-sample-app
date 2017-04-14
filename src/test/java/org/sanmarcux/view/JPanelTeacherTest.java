package org.sanmarcux.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.sanmarcux.beans.Teacher;
import org.sanmarcux.controller.DialogAction;
import org.sanmarcux.controller.TeacherController;
import org.sanmarcux.init.AppConfig;
import org.sanmarcux.init.DatabaseTestConfig;
import org.sanmarcux.util.DateFormatHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, DatabaseTestConfig.class})
public class JPanelTeacherTest {

    @InjectMocks
    @Autowired
    private JPanelTeacher panel;

    @Mock
    private DateFormatHelper dateFormatHelper;

    @Mock
    private TeacherController teacherController;

    @Mock
    private JDialogTeacher dialog;

    @Mock
    private javax.swing.table.DefaultTableModel tableModel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddRow() {
        Date birthday = new Date();

        Teacher entity = new Teacher();
        entity.setBirthday(birthday);

        when(dateFormatHelper.format(Mockito.any(Date.class))).thenReturn("2017-abr-14");

        panel.addRow(entity);

        verify(dateFormatHelper, atLeastOnce()).format(birthday);
        verify(tableModel).addRow(new Object[]{null, null, "2017-abr-14", null});
        verifyNoMoreInteractions(dateFormatHelper);
        verifyNoMoreInteractions(tableModel);
    }

    @Test
    public void testSetRowValues() {
        Date birthday = new Date();

        Teacher entity = new Teacher();
        entity.setBirthday(birthday);

        panel.setRowValues(1, entity);

        verify(tableModel, times(4)).setValueAt(anyObject(), anyInt(), anyInt());
    }

    @Test
    public void testDeleteRow() {

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