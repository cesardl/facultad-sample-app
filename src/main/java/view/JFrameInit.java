package view;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import util.Utils;

/**
 * @author Cesardl
 */
public class JFrameInit extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public JFrameInit() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanelAlumno = new view.JPanelAlumno();
        jPanelProfesor = new view.JPanelProfesor();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuActions = new javax.swing.JMenu();
        jMenuAlumno = new javax.swing.JMenu();
        jMenuItemInsertAlumno = new javax.swing.JMenuItem();
        jMenuItemUpdateAlumno = new javax.swing.JMenuItem();
        jMenuItemDeleteAlumno = new javax.swing.JMenuItem();
        jMenuProfesor = new javax.swing.JMenu();
        jMenuItemInsertProfesor = new javax.swing.JMenuItem();
        jMenuItemUpdateProfesor = new javax.swing.JMenuItem();
        jMenuItemDeleteProfesor = new javax.swing.JMenuItem();
        jSeparator = new javax.swing.JPopupMenu.Separator();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItemAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplicacion con MySQL");

        jPanel.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane.addTab("Alumno", jPanelAlumno);
        jTabbedPane.addTab("Profesor", jPanelProfesor);

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenuBar.setBackground(new java.awt.Color(255, 255, 255));

        jMenuActions.setText("Acciones");

        jMenuAlumno.setText("Alumno");

        jMenuItemInsertAlumno.setText("Nuevo");
        jMenuItemInsertAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInsertAlumnoActionPerformed(evt);
            }
        });
        jMenuAlumno.add(jMenuItemInsertAlumno);

        jMenuItemUpdateAlumno.setText("Editar");
        jMenuItemUpdateAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUpdateAlumnoActionPerformed(evt);
            }
        });
        jMenuAlumno.add(jMenuItemUpdateAlumno);

        jMenuItemDeleteAlumno.setText("Borrar");
        jMenuItemDeleteAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDeleteAlumnoActionPerformed(evt);
            }
        });
        jMenuAlumno.add(jMenuItemDeleteAlumno);

        jMenuActions.add(jMenuAlumno);

        jMenuProfesor.setText("Profesor");

        jMenuItemInsertProfesor.setText("Nuevo");
        jMenuItemInsertProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInsertProfesorActionPerformed(evt);
            }
        });
        jMenuProfesor.add(jMenuItemInsertProfesor);

        jMenuItemUpdateProfesor.setText("Editar");
        jMenuItemUpdateProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUpdateProfesorActionPerformed(evt);
            }
        });
        jMenuProfesor.add(jMenuItemUpdateProfesor);

        jMenuItemDeleteProfesor.setText("Borrar");
        jMenuItemDeleteProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDeleteProfesorActionPerformed(evt);
            }
        });
        jMenuProfesor.add(jMenuItemDeleteProfesor);

        jMenuActions.add(jMenuProfesor);
        jMenuActions.add(jSeparator);

        jMenuItemExit.setText("Salir");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenuActions.add(jMenuItemExit);

        jMenuBar.add(jMenuActions);

        jMenuHelp.setText("Ayuda");

        jMenuItemAbout.setText("Acerca de");
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemAbout);

        jMenuBar.add(jMenuHelp);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemInsertAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInsertAlumnoActionPerformed
        jPanelAlumno.showDialog();
    }//GEN-LAST:event_jMenuItemInsertAlumnoActionPerformed

    private void jMenuItemUpdateAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUpdateAlumnoActionPerformed
        JTable target = jPanelAlumno.getTable();
        int row = target.getSelectedRow();

        if (row == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una fila de la tabla de alumnos", this.getTitle(), JOptionPane.WARNING_MESSAGE);
        } else {
            String code = String.valueOf(target.getValueAt(row, 0));
            jPanelAlumno.showDialog(row, code);
        }
    }//GEN-LAST:event_jMenuItemUpdateAlumnoActionPerformed

    private void jMenuItemDeleteAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDeleteAlumnoActionPerformed
        JTable target = jPanelAlumno.getTable();
        int row = target.getSelectedRow();

        if (row == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una fila de la tabla de alumnos", this.getTitle(), JOptionPane.WARNING_MESSAGE);
        } else {
            String code = String.valueOf(target.getValueAt(row, 0));
            jPanelAlumno.deleteRow(row, code);
        }
    }//GEN-LAST:event_jMenuItemDeleteAlumnoActionPerformed

    private void jMenuItemInsertProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInsertProfesorActionPerformed
        jPanelProfesor.showDialog();
    }//GEN-LAST:event_jMenuItemInsertProfesorActionPerformed

    private void jMenuItemUpdateProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUpdateProfesorActionPerformed
        JTable target = jPanelProfesor.getTable();
        int row = target.getSelectedRow();

        if (row == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una fila de la tabla de profesores", this.getTitle(), JOptionPane.WARNING_MESSAGE);
        } else {
            String code = String.valueOf(target.getValueAt(row, 0));
            jPanelProfesor.showDialog(row, code);
        }
    }//GEN-LAST:event_jMenuItemUpdateProfesorActionPerformed

    private void jMenuItemDeleteProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDeleteProfesorActionPerformed
        JTable target = jPanelProfesor.getTable();
        int row = target.getSelectedRow();

        if (row == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una fila de la tabla de profesores", this.getTitle(), JOptionPane.WARNING_MESSAGE);
        } else {
            String code = String.valueOf(target.getValueAt(row, 0));
            jPanelProfesor.deleteRow(row, code);
        }
    }//GEN-LAST:event_jMenuItemDeleteProfesorActionPerformed

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        int i = javax.swing.JOptionPane.showConfirmDialog(this, "Seguro que desesa salir?",
                this.getTitle(), javax.swing.JOptionPane.YES_NO_OPTION);
        if (i == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAboutActionPerformed
        JDialogAbout dialog = new JDialogAbout(this, true);
        Utils.installEscapeCloseOperation(dialog);
        Utils.installEnterCloseOperation(dialog);
        dialog.setVisible(true);
    }//GEN-LAST:event_jMenuItemAboutActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenuActions;
    private javax.swing.JMenu jMenuAlumno;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemDeleteAlumno;
    private javax.swing.JMenuItem jMenuItemDeleteProfesor;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemInsertAlumno;
    private javax.swing.JMenuItem jMenuItemInsertProfesor;
    private javax.swing.JMenuItem jMenuItemUpdateAlumno;
    private javax.swing.JMenuItem jMenuItemUpdateProfesor;
    private javax.swing.JMenu jMenuProfesor;
    private javax.swing.JPanel jPanel;
    private view.JPanelAlumno jPanelAlumno;
    private view.JPanelProfesor jPanelProfesor;
    private javax.swing.JPopupMenu.Separator jSeparator;
    private javax.swing.JTabbedPane jTabbedPane;
    // End of variables declaration//GEN-END:variables
}
