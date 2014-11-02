/*
 * JPanelAlumno.java
 *
 * Created on 21/03/2010, 10:10:18 PM
 */
package view;

import beans.Alumno;
import controller.impl.AlumnoController;
import util.Utils;

/**
 *
 * @author cesardl
 */
public class JPanelAlumno extends javax.swing.JPanel {

    private final AlumnoController alumnoController = new AlumnoController();

    /**
     * Creates new form JPanelAlumno
     */
    public JPanelAlumno() {
        initComponents();
    }

    public void addRow(Alumno alumno) {
        tableModel.addRow(new Object[]{
            alumno.getCodigo(),
            alumno.getNombre(),
            Utils.formatDate(alumno.getNacimiento()),
            alumno.getSexo(),
            alumno.getDireccion(),
            alumno.getTelefono()
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        tableModel = new javax.swing.table.DefaultTableModel(
            alumnoController.data(),
            new String [] {
                "Codigo", "Nombre", "Edad", "Sexo", "Direccion", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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

        jTable.setModel(tableModel);
        jTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane.setViewportView(jTable);

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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable jTable;
    private javax.swing.table.DefaultTableModel tableModel;
    // End of variables declaration//GEN-END:variables
}
