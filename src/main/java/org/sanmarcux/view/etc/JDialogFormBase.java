package org.sanmarcux.view.etc;

import org.apache.log4j.Logger;
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

	protected static final Logger LOG = Logger.getLogger(JDialogBase.class);

    @Autowired
    protected ResourceBundleHelper resourceBundleHelper;

    @Autowired
    protected TeacherController teacherController;

    protected T entity;

    protected String code;
    protected String names;
    protected Date birthday;

    protected DialogAction dialogAction;

    /**
     * @return true if the data is correct.
     */
    public abstract boolean validateData();

    /**
     *
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

}
