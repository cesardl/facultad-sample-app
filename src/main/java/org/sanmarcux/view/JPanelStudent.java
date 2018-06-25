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
import org.sanmarcux.view.etc.Toast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;

/**
 * @author Cesardl
 * @see <a href='http://esus.com/detecting-double-click-row-jtable/'>Detecting a
 * double-click on a row in a JTable</a>
 */
@Component
public class JPanelStudent extends JPanelBase<Student> {

    private static final long serialVersionUID = -9152034385377244343L;

    private static final Logger LOG = Logger.getLogger(JPanelStudent.class);

    @Autowired
    private transient StudentController studentController;
    @Autowired
    private JDialogStudent dialogStudent;
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
                bundle.getString("app.warning.student.delete"),
                bundle.getString("app.title"), JOptionPane.YES_NO_OPTION);

        if (i == JOptionPane.YES_OPTION) {
            boolean state = studentController.delete(code);

            String key;
            if (state) {
                key = "app.success.student.delete";
                LOG.info(key);
                Toast.makeText(getParentForDialog(), bundle.getString(key), Toast.Style.SUCCESS).display();
                tableModel.removeRow(row);
            } else {
                key = "app.error.student.delete";
                LOG.error(key);
                Toast.makeText(getParentForDialog(), bundle.getString(key), Toast.Style.ERROR).display();
            }
        }
    }

    @Override
    public void showDialog(DialogAction dialogAction, int row, String code) {
        LOG.info("Opening student dialog for " + dialogAction);

        Student student;
        if (DialogAction.UPDATE.equals(dialogAction)) {
            student = studentController.getByCode(code);
        } else {
            student = new Student();
            student.setCode(formSupport.generateRandomCode());
        }

        dialogStudent.setAction(dialogAction);
        dialogStudent.setEntity(student);
        dialogStudent.installEscapeCloseOperation();
        dialogStudent.setVisible(true);

        student = dialogStudent.getEntity();
        if (student == null) {
            return;
        }

        if (DialogAction.UPDATE.equals(dialogAction)) {
            setRowValues(row, student);
        } else {
            addRow(student);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @PostConstruct
    private void initComponents() {

        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        tableModel = new javax.swing.table.DefaultTableModel(
                studentController.getAll(),
                new String[]{
                        bundle.getString("dictionary.code"), bundle.getString("dictionary.names"), bundle.getString("dictionary.birthday"), bundle.getString("dictionary.sex"), bundle.getString("dictionary.address"), bundle.getString("dictionary.phone")
                }
        ) {
            private static final long serialVersionUID = -7145731512253630297L;
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, char.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
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
