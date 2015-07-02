package view.etc;

import controller.DialogAction;
import java.awt.Frame;
import javax.swing.JTable;
import util.DateFormatHelper;
import util.ResourceBundleHelper;

/**
 *
 * @author Cesardl
 * @param <T>
 */
public abstract class JPanelBase<T> extends javax.swing.JPanel {

    protected final DateFormatHelper dateFormatHelper = DateFormatHelper.getInstance();

    protected final ResourceBundleHelper resourceBundleHelper = ResourceBundleHelper.getInstance();

    public static final int BAD_ROW = -1;

    /**
     *
     * @param entity
     */
    protected abstract void addRow(T entity);

    /**
     *
     * @param row
     * @param entity
     */
    protected abstract void setRowValues(int row, T entity);

    /**
     *
     * @param row
     * @param code
     */
    protected abstract void deleteRow(int row, String code);

    /**
     *
     * @return
     */
    public Frame getParentForDialog() {
        return (Frame) getRootPane().getParent();
    }

    /**
     *
     */
    public void showDialog() {
        showDialog(DialogAction.INSERT, BAD_ROW, null);
    }

    /**
     *
     * @param row
     * @param code
     */
    public void showDialog(int row, String code) {
        showDialog(DialogAction.UPDATE, row, code);
    }

    /**
     *
     * @param dialogAction
     * @param row
     * @param code
     */
    public abstract void showDialog(DialogAction dialogAction, int row, String code);

    /**
     *
     * @param evt
     */
    public void showDialogFromTable(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            JTable target = (JTable) evt.getSource();

            int row = target.getSelectedRow();

            String code = String.valueOf(target.getValueAt(row, 0));
            showDialog(row, code);
        }
    }

    /**
     *
     * @param evt
     */
    public void deleteFromTable(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE) {
            JTable target = (JTable) evt.getSource();

            int row = target.getSelectedRow();

            String code = String.valueOf(target.getValueAt(row, 0));
            deleteRow(row, code);
        }
    }
}
