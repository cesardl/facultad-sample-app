/*
 * JPanelAlumno.java
 *
 * Created on 21/03/2010, 10:10:18 PM
 */
package view;

import beans.Alumno;
import controller.DialogAction;
import controller.AlumnoController;
import controller.impl.AlumnoControllerImpl;
import javax.swing.JTable;
import util.Utils;
import view.etc.JPanelBase;

/**
 *
 * @author Cesardl
 *
 * @see
 * <a href='http://esus.com/detecting-double-click-row-jtable/'>Detecting a
 * double-click on a row in a JTable</a>
 */
public class JPanelAlumno extends JPanelBase<Alumno> {

    private final AlumnoController alumnoController = new AlumnoControllerImpl();

    /**
     * Creates new form JPanelAlumno
     */
    public JPanelAlumno() {
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
    protected void addRow(Alumno entity) {
        tableModel.addRow(new Object[]{
            entity.getCodigo(),
            entity.getNombre(),
            Utils.formatDate(entity.getNacimiento()),
            entity.getSexo().getValue(),
            entity.getDireccion(),
            entity.getTelefono()
        });
    }

    @Override
    protected void setRowValues(int row, Alumno entity) {
        tableModel.setValueAt(entity.getCodigo(), row, 0);
        tableModel.setValueAt(entity.getNombre(), row, 1);
        tableModel.setValueAt(Utils.formatDate(entity.getNacimiento()), row, 2);
        tableModel.setValueAt(entity.getSexo().getValue(), row, 3);
        tableModel.setValueAt(entity.getDireccion(), row, 4);
        tableModel.setValueAt(entity.getTelefono(), row, 5);
    }

    @Override
    protected void deleteRow(int row, String code) {
        boolean state = alumnoController.delete(code);
        if (state) {
            tableModel.removeRow(row);
        }
    }

    @Override
    public void showDialog(DialogAction dialogAction, int row, String code) {
        if (DialogAction.UPDATE.equals(dialogAction) && (row == BAD_ROW || code == null)) {
            throw new UnsupportedOperationException("You can not perform this action");
        }

        JDialogAlumno dialogAlumno = new JDialogAlumno(getParentForDialog());
        Utils.installEscapeCloseOperation(dialogAlumno);

        Alumno alumno;

        if (DialogAction.UPDATE.equals(dialogAction)) {
            alumno = alumnoController.getByCode(code);

            dialogAlumno.setEntity(alumno);
        }

        dialogAlumno.setVisible(true);

        alumno = dialogAlumno.getEntity();
        if (alumno == null) {
            return;
        }

        switch (dialogAction) {
            case INSERT:
                addRow(alumno);
                break;

            case UPDATE:
                setRowValues(row, alumno);
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
        jScrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        tableModel = new javax.swing.table.DefaultTableModel(
            alumnoController.getAll(),
            new String [] {
                bundle.getString("dictionary.code"), bundle.getString("dictionary.names"), bundle.getString("dictionary.birthday"), bundle.getString("dictionary.sex"), bundle.getString("dictionary.address"), bundle.getString("dictionary.phone")
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, char.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
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
        jScrollPane.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        showDialogFromTable(evt);
    }//GEN-LAST:event_tableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable table;
    private javax.swing.table.DefaultTableModel tableModel;
    // End of variables declaration//GEN-END:variables
}
