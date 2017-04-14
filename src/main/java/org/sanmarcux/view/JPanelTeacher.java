/*
 * JPanelTeacher.java
 *
 * Created on 21/03/2010, 10:10:27 PM
 */
package org.sanmarcux.view;

import org.apache.log4j.Logger;
import org.sanmarcux.beans.Teacher;
import org.sanmarcux.controller.DialogAction;
import org.sanmarcux.controller.TeacherController;
import org.sanmarcux.view.etc.JPanelBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;

/**
 * @author Cesardl
 */
@Component
public class JPanelTeacher extends JPanelBase<Teacher> {

    private static final long serialVersionUID = -7175649245125570810L;

    private static final Logger LOG = Logger.getLogger(JPanelTeacher.class);

    @Autowired
    private transient TeacherController teacherController;
    @Autowired
    private JDialogTeacher dialogTeacher;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable table;
    private javax.swing.table.DefaultTableModel tableModel;

    /**
     * @return the table.
     */
    public JTable getTable() {
        return table;
    }

    @Override
    protected void addRow(Teacher entity) {
        tableModel.addRow(new Object[]{
                entity.getCode(),
                entity.getNames(),
                dateFormatHelper.format(entity.getBirthday()),
                entity.getEmail()
        });
    }

    @Override
    protected void setRowValues(int row, Teacher entity) {
        tableModel.setValueAt(entity.getCode(), row, 0);
        tableModel.setValueAt(entity.getNames(), row, 1);
        tableModel.setValueAt(dateFormatHelper.format(entity.getBirthday()), row, 2);
        tableModel.setValueAt(entity.getEmail(), row, 3);
    }

    @Override
    protected void deleteRow(int row, String code) {
        int i = JOptionPane.showConfirmDialog(this,
                bundle.getString("app.warning.teacher.delete"),
                bundle.getString("app.title"), JOptionPane.YES_NO_OPTION);

        if (i == JOptionPane.YES_OPTION) {
            boolean state = teacherController.delete(code);

            String key;
            if (state) {
                key = "app.success.teacher.delete";
                LOG.info(key);
                Toast.makeText(getParentForDialog(), bundle.getString(key), Toast.Style.SUCCESS).display();
                tableModel.removeRow(row);
            } else {
                key = "app.error.teacher.delete";
                LOG.error(key);
                Toast.makeText(getParentForDialog(), bundle.getString(key), Toast.Style.ERROR).display();
            }
        }
    }

    @Override
    public void showDialog(DialogAction dialogAction, int row, String code) {
        LOG.info("Opening teacher dialog for " + dialogAction);

        Teacher teacher;
        if (DialogAction.UPDATE.equals(dialogAction)) {
            teacher = teacherController.getByCode(code);
        } else {
            teacher = new Teacher();
            teacher.setCode(formSupport.generateRandomCode());
        }

        dialogTeacher.setAction(dialogAction);
        dialogTeacher.setEntity(teacher);
        dialogTeacher.installEscapeCloseOperation();
        dialogTeacher.setVisible(true);

        teacher = dialogTeacher.getEntity();
        if (teacher == null) {
            return;
        }

        if (DialogAction.UPDATE.equals(dialogAction)) {
            setRowValues(row, teacher);
        } else {
            addRow(teacher);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @PostConstruct
    private void initComponents() {

        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        tableModel = new javax.swing.table.DefaultTableModel(
                teacherController.getAll(),
                new String[]{
                        bundle.getString("dictionary.code"), bundle.getString("dictionary.names"), bundle.getString("dictionary.birthday"), bundle.getString("dictionary.email")
                }
        ) {
            private static final long serialVersionUID = 8018108352536896160L;
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        setBackground(new java.awt.Color(255, 255, 255));

        table.setModel(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }
        });
        table.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableKeyPressed(evt);
            }
        });
        scrollPane.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMousePressed
        showDialogFromTable(evt);
    }//GEN-LAST:event_tableMousePressed

    private void tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyPressed
        deleteFromTable(evt);
    }//GEN-LAST:event_tableKeyPressed
    // End of variables declaration//GEN-END:variables
}
