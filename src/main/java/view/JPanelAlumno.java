/*
 * JPanelAlumno.java
 *
 * Created on 21/03/2010, 10:10:18 PM
 */
package view;

import beans.Alumno;
import controller.Action;
import controller.AlumnoController;
import controller.impl.AlumnoControllerImpl;
import java.awt.Frame;
import javax.swing.JTable;
import util.Utils;

/**
 *
 * @author cesardl
 *
 * @see
 * <a href='http://esus.com/detecting-double-click-row-jtable/'>Detecting a
 * double-click on a row in a JTable</a>
 */
public class JPanelAlumno extends javax.swing.JPanel {

    private final AlumnoController alumnoController = new AlumnoControllerImpl();

    /**
     * Creates new form JPanelAlumno
     */
    public JPanelAlumno() {
        initComponents();
    }

    public JTable getTable() {
        return table;
    }

    /**
     *
     * @param alumno
     */
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
     *
     * @param row
     * @param alumno
     */
    public void setRowValues(int row, Alumno alumno) {
        tableModel.setValueAt(alumno.getCodigo(), row, 0);
        tableModel.setValueAt(alumno.getNombre(), row, 1);
        tableModel.setValueAt(Utils.formatDate(alumno.getNacimiento()), row, 2);
        tableModel.setValueAt(alumno.getSexo(), row, 3);
        tableModel.setValueAt(alumno.getDireccion(), row, 4);
        tableModel.setValueAt(alumno.getTelefono(), row, 5);
    }

    /**
     *
     * @param parent
     * @param row
     * @param code
     */
    public void showDialogForUpdate(Frame parent, int row, String code) {
        Alumno alumno = alumnoController.getByCode(code);

        JDialogAlumno dialogAlumno = new JDialogAlumno(parent, true);
        dialogAlumno.setAlumno(alumno);
        dialogAlumno.setVisible(true);

        alumno = dialogAlumno.getAlumno();
        //FIXME status of update
        Action action = dialogAlumno.getAction();
        if (action != null && dialogAlumno.getAction().equals(Action.UPDATE)) {
            setRowValues(row, alumno);
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

        jScrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        tableModel = new javax.swing.table.DefaultTableModel(
            alumnoController.getAll(),
            new String [] {
                "Codigo", "Nombre", "Nacimiento", "Sexo", "Direccion", "Telefono"
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

        table.setModel(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable table;
    private javax.swing.table.DefaultTableModel tableModel;
    // End of variables declaration//GEN-END:variables
}
