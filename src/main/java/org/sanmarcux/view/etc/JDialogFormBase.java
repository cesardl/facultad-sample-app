package org.sanmarcux.view.etc;

import org.sanmarcux.controller.DialogAction;
import org.sanmarcux.controller.TeacherController;
import org.sanmarcux.util.ResourceBundleHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @param <T>
 * @author cesardiaz
 */
public abstract class JDialogFormBase<T> extends JDialogBase {

    private static final long serialVersionUID = -5992999043081036468L;

    @Autowired
    protected transient ResourceBundleHelper bundle;

    @Autowired
    protected transient TeacherController teacherController;

    protected transient T entity;

    protected String code;
    protected String names;
    protected Date birthday;

    protected DialogAction dialogAction;

    /**
     * @return true if the data is correct.
     */
    public abstract boolean validateData();

    /**
     * Update entity's data on view.
     */
    public abstract void setData();

    /**
     * @return the entity.
     */
    public abstract T getEntity();

    /**
     * @param entity the entity
     */
    public abstract void setEntity(T entity);

    /**
     * @param dialogAction an action, UPDATE or INSERT
     */
    public void setAction(DialogAction dialogAction) {
        this.dialogAction = dialogAction;
    }

    /**
     * Select text of the current text field.
     *
     * @param textField the text field.
     */
    public void selectTextField(javax.swing.JTextField textField) {
        textField.requestFocus();
        textField.selectAll();
    }

    /**
     * Make actions before close the dialog.
     */
    protected void preparingDialogClose() {
        entity = null;
    }
}
