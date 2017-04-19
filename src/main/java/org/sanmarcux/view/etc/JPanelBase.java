package org.sanmarcux.view.etc;

import org.sanmarcux.controller.DialogAction;
import org.sanmarcux.util.DateFormatHelper;
import org.sanmarcux.util.FormSupport;
import org.sanmarcux.util.ResourceBundleHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;

/**
 * @param <T>
 * @author Cesardl
 */
public abstract class JPanelBase<T> extends javax.swing.JPanel {

    protected static final int BAD_ROW = -1;
    private static final long serialVersionUID = 8384336851786056495L;

    @Autowired
    protected DateFormatHelper dateFormatHelper;

    @Autowired
    protected transient ResourceBundleHelper bundle;

    @Autowired
    protected transient FormSupport formSupport;

    /**
     * @param entity the entity to be added.
     */
    protected abstract void addRow(T entity);

    /**
     * @param row    the row position.
     * @param entity the entity to be assigned.
     */
    protected abstract void setRowValues(int row, T entity);

    /**
     * @param row  the row position.
     * @param code the entity code.
     */
    protected abstract void deleteRow(int row, String code);

    /**
     * @return the dialog's parent.
     */
    public Frame getParentForDialog() {
        return (Frame) getRootPane().getParent();
    }

    /**
     * Display dialog
     */
    public void showDialog() {
        showDialog(DialogAction.INSERT, BAD_ROW, null);
    }

    /**
     * @param row  the row position.
     * @param code the entity code.
     */
    public void showDialog(int row, String code) {
        showDialog(DialogAction.UPDATE, row, code);
    }

    /**
     * @param dialogAction the dialog action.
     * @param row          the row position.
     * @param code         the entity code.
     */
    public abstract void showDialog(DialogAction dialogAction, int row, String code);

    /**
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
