/*
 * JPanelStudent.java
 *
 * Created on 21/03/2010, 10:10:18 PM
 */
package org.sanmarcux.view;

import org.apache.log4j.Logger;
import org.sanmarcux.beans.Student;
import org.sanmarcux.controller.DialogAction;
import org.sanmarcux.controller.StudentController;
import org.sanmarcux.view.etc.JPanelBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.swing.*;

/**
 * @author Cesardl
 * @see <a href='http://esus.com/detecting-double-click-row-jtable/'>Detecting a
 * double-click on a row in a JTable</a>
 */
@Controller
public class JPanelStudent extends JPanelBase<Student> {

    private static final long serialVersionUID = -3407749931422832063L;

    private static final Logger LOG = Logger.getLogger(JPanelStudent.class);

    @Autowired
    private StudentController studentController;
    @Autowired
    private JDialogStudent dialogStudent;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable table;
    private javax.swing.table.DefaultTableModel tableModel;

    /**
     * Creates new form JPanelStudent.
     */
//    public JPanelStudent() {
//        initComponents();
//    }

    /**
     * @return the table.
     */
    public JTable getTable() {
        return table;
    }

    @Override
    protected void addRow(Student entity) {
        tableModel.addRow(new Object[]{
                entity.getCode(),
                entity.getNames(),
                dateFormatHelper.format(entity.getBirthday()),
                entity.getGender().getValue(),
                entity.getAddress(),
                entity.getPhone()
        });
    }

    @Override
    protected void setRowValues(int row, Student entity) {
        tableModel.setValueAt(entity.getCode(), row, 0);
        tableModel.setValueAt(entity.getNames(), row, 1);
        tableModel.setValueAt(dateFormatHelper.format(entity.getBirthday()), row, 2);
        tableModel.setValueAt(entity.getGender().getValue(), row, 3);
        tableModel.setValueAt(entity.getAddress(), row, 4);
        tableModel.setValueAt(entity.getPhone(), row, 5);
    }

    @Override
    protected void deleteRow(int row, String code) {
        int i = JOptionPane.showConfirmDialog(this,
                resourceBundleHelper.getString("app.warning.student.delete"),
                resourceBundleHelper.getString("app.title"), JOptionPane.YES_NO_OPTION);

        if (i == JOptionPane.YES_OPTION) {
            boolean state = studentController.delete(code);

            String key;
            if (state) {
                key = "app.success.student.delete";
                LOG.info(key);
                Toast.makeText(getParentForDialog(), resourceBundleHelper.getString(key), Toast.Style.SUCCESS).display();
                tableModel.removeRow(row);
            } else {
                key = "app.error.student.delete";
                LOG.error(key);
                Toast.makeText(getParentForDialog(), resourceBundleHelper.getString(key), Toast.Style.ERROR).display();
            }
        }
    }

    @Override
    public void showDialog(DialogAction dialogAction, int row, String code) {
        if (DialogAction.UPDATE.equals(dialogAction) && (row == BAD_ROW || code == null)) {
            throw new UnsupportedOperationException("You can not perform this action");
        }

        // = new JDialogStudent(getParentForDialog());
        dialogStudent.setAction(dialogAction);
        dialogStudent.installEscapeCloseOperation();

        Student student;

        if (DialogAction.UPDATE.equals(dialogAction)) {
            student = studentController.getByCode(code);

            dialogStudent.setEntity(student);
        }

        dialogStudent.setVisible(true);

        student = dialogStudent.getEntity();
        if (student == null) {
            return;
        }

        switch (dialogAction) {
            case INSERT:
                addRow(student);
                break;

            case UPDATE:
                setRowValues(row, student);
                break;
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

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("view/Bundle"); // NOI18N
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        tableModel = new javax.swing.table.DefaultTableModel(
                studentController.getAll(),
                new String[]{
                        bundle.getString("dictionary.code"), bundle.getString("dictionary.names"), bundle.getString("dictionary.birthday"), bundle.getString("dictionary.sex"), bundle.getString("dictionary.address"), bundle.getString("dictionary.phone")
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, char.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        setBackground(new java.awt.Color(255, 255, 255));

        table.setModel(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        table.addKeyListener(new java.awt.event.KeyAdapter() {
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
                                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                .addGap(13, 13, 13))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        showDialogFromTable(evt);
    }//GEN-LAST:event_tableMouseClicked

    private void tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyPressed
        deleteFromTable(evt);
    }//GEN-LAST:event_tableKeyPressed
    // End of variables declaration//GEN-END:variables
}