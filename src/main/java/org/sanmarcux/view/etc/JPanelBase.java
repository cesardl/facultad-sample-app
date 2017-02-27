package org.sanmarcux.view.etc;

import org.sanmarcux.controller.DialogAction;
import java.awt.Frame;
import javax.swing.JTable;
import org.sanmarcux.util.DateFormatHelper;
import org.sanmarcux.util.ResourceBundleHelper;

/**
 *
 * @author Cesardl
 * @param <T>
 */
public abstract class JPanelBase<T> extends javax.swing.JPanel {

    private static final long serialVersionUID = -8446015582022117195L;

    protected final DateFormatHelper dateFormatHelper = DateFormatHelper.getInstance();

    protected final ResourceBundleHelper resourceBundleHelper = ResourceBundleHelper.getInstance();

    public static final int BAD_ROW = -1;

    /**
     *
     * @param entity the entity to be added.
     */
    protected abstract void addRow(T entity);

    /**
     *
     * @param row the row position.
     * @param entity the entity to be assigned.
     */
    protected abstract void setRowValues(int row, T entity);

    /**
     *
     * @param row the row position.
     * @param code the entity code.
     */
    protected abstract void deleteRow(int row, String code);

    /**
     *
     * @return the dialog's parent.
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
     * @param row the row position.
     * @param code the entity code.
     */
    public void showDialog(int row, String code) {
        showDialog(DialogAction.UPDATE, row, code);
    }

    /**
     *
     * @param dialogAction the dialog action.
     * @param row the row position.
     * @param code the entity code.
     */
    public abstract void showDialog(DialogAction dialogAction, int row, String code);

    /**
     *
     * @param evt the mouse event.
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
     * @param evt the key event.
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
