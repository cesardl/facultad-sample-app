/*
 * JDialogStudent.java
 *
 * Created on 12/07/2009, 05:13:31 PM
 */
package org.sanmarcux.view;

import org.sanmarcux.beans.Student;
import org.sanmarcux.beans.Teacher;
import org.sanmarcux.beans.etc.Gender;
import org.sanmarcux.controller.DialogAction;
import org.sanmarcux.controller.StudentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sanmarcux.view.etc.JDialogFormBase;

import javax.annotation.PostConstruct;

/**
 *
 * @author Cesardl
 */
@Component
public class JDialogStudent extends JDialogFormBase<Student> {

    private static final long serialVersionUID = -4130243854869964348L;

    @Autowired
    private StudentController studentController;

    private String address, phone;
    private Gender gender;

    @Override
    public boolean validateData() {
        code = textFieldCode.getText().trim();
        names = textFieldNames.getText().trim();
        birthday = dateChooserBirthday.getDate();
        gender = radioButtonMale.isSelected() ? Gender.MALE : Gender.FEMALE;
        address = textFieldAddress.getText().trim();
        phone = textFieldPhone.getText().trim();

        String key;
        if (code.length() == 0) {
            key = "app.warning.student.code.empty";
            LOG.warn(key);
            Toast.makeText(this, resourceBundleHelper.getString(key), Toast.Style.WARNING).display();
            selectTextField(textFieldCode);
            return false;
        }
        if (DialogAction.INSERT.equals(dialogAction)) {
            if (studentController.existsCode(code)) {
                key = "app.warning.student.code.already.exists";
                LOG.warn(key);
                Toast.makeText(this, resourceBundleHelper.getString(key), Toast.Style.WARNING).display();
                selectTextField(textFieldCode);
                return false;
            }
        }
        if (names.length() == 0) {
            key = "app.warning.student.name.empty";
            LOG.warn(key);
            Toast.makeText(this, resourceBundleHelper.getString(key), Toast.Style.WARNING).display();
            selectTextField(textFieldNames);
            return false;
        }
        if (birthday == null) {
            key = "app.warning.student.birthday.empty";
            LOG.warn(key);
            Toast.makeText(this, resourceBundleHelper.getString(key), Toast.Style.WARNING).display();
            dateChooserBirthday.requestFocusInWindow();
            return false;
        }
        if (address.length() == 0) {
            key = "app.warning.student.address.empty";
            LOG.warn(key);
            Toast.makeText(this, resourceBundleHelper.getString(key), Toast.Style.WARNING).display();
            selectTextField(textFieldAddress);
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
        address = entity.getAddress();
        phone = entity.getPhone();

        int size = comboBoxSelectTeacher.getItemCount();
        for (int i = 0; i < size; i++) {
            Teacher p = comboBoxSelectTeacher.getItemAt(i);
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
        textFieldAddress.setText(address);
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @PostConstruct
    private void initComponents() {

        javax.swing.ButtonGroup buttonGroup = new javax.swing.ButtonGroup();
        javax.swing.JPanel panel = new javax.swing.JPanel();
        javax.swing.JLabel labelCode = new javax.swing.JLabel();
        javax.swing.JLabel labelNames = new javax.swing.JLabel();
        javax.swing.JLabel labelBirthday = new javax.swing.JLabel();
        javax.swing.JLabel labelGender = new javax.swing.JLabel();
        javax.swing.JLabel labelAddress = new javax.swing.JLabel();
        javax.swing.JLabel labelPhone = new javax.swing.JLabel();
        javax.swing.JLabel labelSelectTeacher = new javax.swing.JLabel();
        textFieldCode = new javax.swing.JTextField();
        textFieldNames = new javax.swing.JTextField();
        dateChooserBirthday = new com.toedter.calendar.JDateChooser();
        radioButtonMale = new javax.swing.JRadioButton();
        radioButtonFemale = new javax.swing.JRadioButton();
        textFieldAddress = new javax.swing.JTextField();
        textFieldPhone = new javax.swing.JTextField();
        comboBoxSelectTeacher = new javax.swing.JComboBox<>();
        javax.swing.JButton buttonAccept = new javax.swing.JButton();
        javax.swing.JButton buttonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("view/Bundle"); // NOI18N
        setTitle(bundle.getString("app.title")); // NOI18N
        setResizable(false);

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("app.dialog.border.title"))); // NOI18N

        labelCode.setText(bundle.getString("dictionary.code")); // NOI18N

        labelNames.setText(bundle.getString("dictionary.names")); // NOI18N

        labelBirthday.setText(bundle.getString("dictionary.birthday")); // NOI18N

        labelGender.setText(bundle.getString("dictionary.sex")); // NOI18N

        labelAddress.setText(bundle.getString("dictionary.address")); // NOI18N

        labelPhone.setText(bundle.getString("dictionary.phone")); // NOI18N

        labelSelectTeacher.setText(bundle.getString("app.select.teacher")); // NOI18N

        buttonGroup.add(radioButtonMale);
        radioButtonMale.setSelected(true);
        radioButtonMale.setText(bundle.getString("dictionary.male")); // NOI18N

        buttonGroup.add(radioButtonFemale);
        radioButtonFemale.setText(bundle.getString("dictionary.female")); // NOI18N

        comboBoxSelectTeacher.setModel(new javax.swing.DefaultComboBoxModel<>(teacherController.getNames()));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelSelectTeacher, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(labelCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelPhone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(labelNames, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
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
                    .addComponent(labelAddress)
                    .addComponent(textFieldAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(textFieldPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPhone))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(comboBoxSelectTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSelectTeacher))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonAccept.setText(bundle.getString("dictionary.accept")); // NOI18N
        buttonAccept.addActionListener(JDialogStudent.this::buttonAcceptActionPerformed);

        buttonCancel.setText(bundle.getString("dictionary.cancel")); // NOI18N
        buttonCancel.addActionListener(JDialogStudent.this::buttonCancelActionPerformed);

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
        setModal(true);
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
            entity.setAddress(address);
            entity.setPhone(phone);
            entity.setTeacherId(teacherId);

            boolean state = studentController.saveOrUpdate(entity);

            String key;
            if (state) {
                key = "app.success.student.save";
                LOG.info(key);
                Toast.makeText(this, resourceBundleHelper.getString(key), Toast.Style.SUCCESS).display();
                dispose();
            } else {
                key = "app.error.student.save";
                LOG.error(key);
                Toast.makeText(this, resourceBundleHelper.getString(key), Toast.Style.ERROR).display();
            }
        }
    }//GEN-LAST:event_buttonAcceptActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        dispose();
    }//GEN-LAST:event_buttonCancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Teacher> comboBoxSelectTeacher;
    private com.toedter.calendar.JDateChooser dateChooserBirthday;
    private javax.swing.JRadioButton radioButtonFemale;
    private javax.swing.JRadioButton radioButtonMale;
    private javax.swing.JTextField textFieldAddress;
    private javax.swing.JTextField textFieldCode;
    private javax.swing.JTextField textFieldNames;
    private javax.swing.JTextField textFieldPhone;
    // End of variables declaration//GEN-END:variables
}