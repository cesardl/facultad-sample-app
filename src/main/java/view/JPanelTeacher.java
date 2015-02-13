/*
 * JPanelTeacher.java
 *
 * Created on 21/03/2010, 10:10:27 PM
 */
package view;

import beans.Teacher;
import controller.DialogAction;
import controller.TeacherController;
import controller.impl.TeacherControllerImpl;
import javax.swing.JTable;
import util.Utils;
import view.etc.JPanelBase;

/**
 *
 * @author Cesardl
 */
public class JPanelTeacher extends JPanelBase<Teacher> {

    private final TeacherController profesorController = new TeacherControllerImpl();

    /**
     * Creates new form JPanelProfesor
     */
    public JPanelTeacher() {
        initComponents();
    }

    /**
     *
     * @return
     */
    public JTable getTable() {
        return table;
    }

    @Override
    protected void addRow(Teacher entity) {
        tableModel.addRow(new Object[]{
            entity.getCodigo(),
            entity.getNombre(),
            Utils.formatDate(entity.getNacimiento()),
            entity.getEmail()
        });
    }

    @Override
    protected void setRowValues(int row, Teacher entity) {
        tableModel.setValueAt(entity.getCodigo(), row, 0);
        tableModel.setValueAt(entity.getNombre(), row, 1);
        tableModel.setValueAt(Utils.formatDate(entity.getNacimiento()), row, 2);
        tableModel.setValueAt(entity.getEmail(), row, 3);
    }

    @Override
    protected void deleteRow(int row, String code) {
        boolean state = profesorController.delete(code);
        if (state) {
            tableModel.removeRow(row);
        }
    }

    @Override
    public void showDialog(DialogAction dialogAction, int row, String code) {
        if (DialogAction.UPDATE.equals(dialogAction) && (row == BAD_ROW || code == null)) {
            throw new UnsupportedOperationException("You can not perform this action");
        }

        JDialogTeacher dialogProfesor = new JDialogTeacher(getParentForDialog());
        Utils.installEscapeCloseOperation(dialogProfesor);

        Teacher profesor;

        if (DialogAction.UPDATE.equals(dialogAction)) {
            profesor = profesorController.getByCode(code);
            dialogProfesor.setEntity(profesor);
        }

        dialogProfesor.setVisible(true);

        profesor = dialogProfesor.getEntity();
        if (profesor == null) {
            return;
        }

        switch (dialogAction) {
            case INSERT:
                addRow(profesor);
                break;

            case UPDATE:
                setRowValues(row, profesor);
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
    private void initComponents() {

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("view/Bundle"); // NOI18N
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        tableModel = new javax.swing.table.DefaultTableModel(
            profesorController.getAll(),
            new String [] {
                bundle.getString("dictionary.code"), bundle.getString("dictionary.names"), bundle.getString("dictionary.birthday"), bundle.getString("dictionary.email")
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    private javax.swing.table.DefaultTableModel tableModel;
    // End of variables declaration//GEN-END:variables
}
