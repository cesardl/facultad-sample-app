package org.sanmarcux.view.etc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

/**
 * @author Cesardl
 */
public abstract class JDialogBase extends javax.swing.JDialog {

    private static final long serialVersionUID = -8362930735484980393L;

    private static final String DISPATCH_WINDOW_CLOSING_ACTION_MAP_KEY = JDialogBase.class.getCanonicalName() + ":WINDOW_CLOSING";

    private static final KeyStroke ESCAPE_STROKE = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
    private static final KeyStroke ENTER_STROKE = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);

    /**
     *
     */
    public void installEscapeCloseOperation() {
        JRootPane root = getRootPane();
        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ESCAPE_STROKE, DISPATCH_WINDOW_CLOSING_ACTION_MAP_KEY);

        root.getActionMap().put(DISPATCH_WINDOW_CLOSING_ACTION_MAP_KEY, new AbstractAction() {

            private static final long serialVersionUID = 4428246088355681817L;

            @Override
            public void actionPerformed(ActionEvent event) {
                dispatchEvent(new WindowEvent(
                        JDialogBase.this, WindowEvent.WINDOW_CLOSING
                ));
            }
        });
    }

    /**
     *
     */
    public void installEnterCloseOperation() {
        JRootPane root = getRootPane();
        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ENTER_STROKE, DISPATCH_WINDOW_CLOSING_ACTION_MAP_KEY);

        root.getActionMap().put(DISPATCH_WINDOW_CLOSING_ACTION_MAP_KEY, new AbstractAction() {

            private static final long serialVersionUID = -253299336339560997L;

            @Override
            public void actionPerformed(ActionEvent event) {
                dispatchEvent(new WindowEvent(
                        JDialogBase.this, WindowEvent.WINDOW_CLOSING
                ));
            }
        });
    }

}
