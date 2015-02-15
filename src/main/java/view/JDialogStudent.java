/*
 * JDialogStudent.java
 *
 * Created on 12/07/2009, 05:13:31 PM
 */
package view;

import beans.Student;
import beans.Teacher;
import beans.etc.Gender;
import controller.StudentController;
import controller.impl.StudentControllerImpl;
import util.Utils;
import view.etc.JDialogBase;

/**
 *
 * @author Cesardl
 */
public class JDialogStudent extends JDialogBase<Student> {

    private final StudentController studentController = new StudentControllerImpl();

    private String direction, phone;
    private Gender gender;

    /**
     * Creates new form JDialogAlumno.
     *
     * @param parent
     */
    public JDialogStudent(java.awt.Frame parent) {
        super(parent);
        initComponents();
    }

    @Override
    public boolean validateData() {
        code = textFieldCode.getText().trim();
        names = textFieldNames.getText().trim();
        birthday = dateChooserBirthday.getDate();
        gender = radioButtonMale.isSelected() ? Gender.MALE : Gender.FEMALE;
        direction = textFieldDirection.getText().trim();
        phone = textFieldPhone.getText().trim();

        if (code.length() == 0) {
            Utils.selectTextField(textFieldCode);
            return false;
        }
        if (studentController.existsCode(code) && entity == null) {
            Utils.selectTextField(textFieldCode);
            return false;
        }
        if (names.length() == 0) {
            Utils.selectTextField(textFieldNames);
            return false;
        }
        if (birthday == null) {
            dateChooserBirthday.requestFocusInWindow();
            return false;
        }
        if (direction.length() == 0) {
            Utils.selectTextField(textFieldDirection);
            return false;
        }

        return true;
    }

    @Override
    public void setData() {
        code = entity.getCode();
        names = entity.getNames();
        birthday = entity.getBirthday();
        gender = entity.getGender();
        direction = entity.getDirection();
        phone = entity.getTelefono();

        final int MAX_SIZE = comboBoxSelectTeacher.getItemCount();
        for (int i = 0; i < MAX_SIZE; i++) {
            Teacher p = (Teacher) comboBoxSelectTeacher.getItemAt(i);
            if (p.getId() == entity.getTeacherId()) {
                comboBoxSelectTeacher.setSelectedIndex(i);
                break;
            }
        }

        textFieldCode.setText(code);
        textFieldCode.setEnabled(false);
        textFieldNames.setText(names);
        dateChooserBirthday.setDate(birthday);
        if (Gender.MALE.equals(gender)) {
            radioButtonMale.setSelected(true);
        } else {
            radioButtonFemale.setSelected(true);
        }
        textFieldDirection.setText(direction);
        textFieldPhone.setText(phone);
    }

    @Override
    public Student getEntity() {
        return entity;
    }

    @Override
    public void setEntity(Student entity) {
        this.entity = entity;

        setData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        panel = new javax.swing.JPanel();
        labelCode = new javax.swing.JLabel();
        labelNames = new javax.swing.JLabel();
        labelBirthday = new javax.swing.JLabel();
        labelGender = new javax.swing.JLabel();
        labelDirection = new javax.swing.JLabel();
        labelPhone = new javax.swing.JLabel();
        labelSelectTeacher = new javax.swing.JLabel();
        textFieldCode = new javax.swing.JTextField();
        textFieldNames = new javax.swing.JTextField();
        dateChooserBirthday = new com.toedter.calendar.JDateChooser();
        radioButtonMale = new javax.swing.JRadioButton();
        radioButtonFemale = new javax.swing.JRadioButton();
        textFieldDirection = new javax.swing.JTextField();
        textFieldPhone = new javax.swing.JTextField();
        comboBoxSelectTeacher = new javax.swing.JComboBox();
        buttonAccept = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("view/Bundle"); // NOI18N
        setTitle(bundle.getString("app.title")); // NOI18N
        setResizable(false);

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("app.dialog.border.title"))); // NOI18N

        labelCode.setText(bundle.getString("dictionary.code")); // NOI18N

        labelNames.setText(bundle.getString("dictionary.names")); // NOI18N

        labelBirthday.setText(bundle.getString("dictionary.birthday")); // NOI18N

        labelGender.setText(bundle.getString("dictionary.sex")); // NOI18N

        labelDirection.setText(bundle.getString("dictionary.address")); // NOI18N

        labelPhone.setText(bundle.getString("dictionary.phone")); // NOI18N

        labelSelectTeacher.setText(bundle.getString("app.select.teacher")); // NOI18N

        buttonGroup.add(radioButtonMale);
        radioButtonMale.setSelected(true);
        radioButtonMale.setText(bundle.getString("dictionary.male")); // NOI18N

        buttonGroup.add(radioButtonFemale);
        radioButtonFemale.setText(bundle.getString("dictionary.female")); // NOI18N

        comboBoxSelectTeacher.setModel(new javax.swing.DefaultComboBoxModel(teacherController.getNames()));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelSelectTeacher, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(labelCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelDirection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelPhone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(labelNames, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldDirection, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dateChooserBirthday, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textFieldCode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                            .addComponent(textFieldNames, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                        .addGap(108, 108, 108))
                    .addComponent(comboBoxSelectTeacher, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(radioButtonMale)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioButtonFemale))
                            .addComponent(textFieldPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelCode)
                    .addComponent(textFieldCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(textFieldNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNames))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(dateChooserBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelBirthday))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(radioButtonFemale)
                    .addComponent(radioButtonMale)
                    .addComponent(labelGender))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelDirection)
                    .addComponent(textFieldDirection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(textFieldPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPhone))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(comboBoxSelectTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSelectTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonAccept.setText(bundle.getString("dictionary.accept")); // NOI18N
        buttonAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAcceptActionPerformed(evt);
            }
        });

        buttonCancel.setText(bundle.getString("dictionary.cancel")); // NOI18N
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonAccept, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCancel)
                    .addComponent(buttonAccept))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAcceptActionPerformed
        if (validateData()) {
            Teacher t = (Teacher) comboBoxSelectTeacher.getSelectedItem();
            int teacherId = t.getId();

            if (entity == null) {
                entity = new Student();
            }

            entity.setCode(code);
            entity.setNames(names);
            entity.setBirthday(birthday);
            entity.setGender(gender);
            entity.setDirection(direction);
            entity.setTelefono(phone);
            entity.setTeacherId(teacherId);

            boolean state = studentController.saveOrUpdate(entity);

            if (state) {
                dispose();
            } else {
                log.warn("Could not insert/update a student.");
            }
        }
    }//GEN-LAST:event_buttonAcceptActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        dispose();
    }//GEN-LAST:event_buttonCancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAccept;
    private javax.swing.JButton buttonCancel;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JComboBox comboBoxSelectTeacher;
    private com.toedter.calendar.JDateChooser dateChooserBirthday;
    private javax.swing.JLabel labelBirthday;
    private javax.swing.JLabel labelCode;
    private javax.swing.JLabel labelDirection;
    private javax.swing.JLabel labelGender;
    private javax.swing.JLabel labelNames;
    private javax.swing.JLabel labelPhone;
    private javax.swing.JLabel labelSelectTeacher;
    private javax.swing.JPanel panel;
    private javax.swing.JRadioButton radioButtonFemale;
    private javax.swing.JRadioButton radioButtonMale;
    private javax.swing.JTextField textFieldCode;
    private javax.swing.JTextField textFieldDirection;
    private javax.swing.JTextField textFieldNames;
    private javax.swing.JTextField textFieldPhone;
    // End of variables declaration//GEN-END:variables
}