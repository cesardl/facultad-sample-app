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
 * @author Cesardl
 * @param <T>
 */
public abstract class JDialogBase<T> extends javax.swing.JDialog {

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
     * @param parent
     */
    public JDialogBase(Frame parent) {
        super(parent, true);
    }

    /**
     *
     * @return
     */
    public abstract boolean validateData();

    /**
     *
     */
    public abstract void setData();

    /**
     *
     * @return
     */
    public abstract T getEntity();

    /**
     *
     * @param entity
     */
    public abstract void setEntity(T entity);

    public void setAction(DialogAction dialogAction) {
        this.dialogAction = dialogAction;
    }

}
