package view.etc;

import controller.DialogAction;
import controller.TeacherController;
import controller.impl.TeacherControllerImpl;
import java.awt.Frame;
import java.util.Date;
import org.apache.log4j.Logger;
import util.ResourceBundleHelper;

/**
 *
 * @author cesardiaz
 * @param <T>
 */
public abstract class JDialogFormBase<T> extends JDialogBase {

    private static final long serialVersionUID = -2649937183651508059L;

    protected static final Logger log = Logger.getLogger(JDialogBase.class);

    protected final ResourceBundleHelper resourceBundleHelper = ResourceBundleHelper.getInstance();

    protected final TeacherController teacherController = new TeacherControllerImpl();

    protected T entity;

    protected String code, names;
    protected Date birthday;

    protected DialogAction dialogAction;

    /**
     *
     * @param parent the parent of this dialog.
     */
    public JDialogFormBase(Frame parent) {
        super(parent);
    }

    /**
     *
     * @return true if the data is correct.
     */
    public abstract boolean validateData();

    /**
     *
     */
    public abstract void setData();

    /**
     *
     * @return the entity.
     */
    public abstract T getEntity();

    /**
     *
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
