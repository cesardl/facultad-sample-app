package view.etc;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Cesardl
 */
public abstract class JDialogBase extends javax.swing.JDialog {

    private static final long serialVersionUID = 9011841899294587427L;

    private static final String DISPATCH_WINDOW_CLOSING_ACTION_MAP_KEY = JDialogBase.class.getCanonicalName() + ":WINDOW_CLOSING";

    private static final KeyStroke escapeStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
    private static final KeyStroke enterStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);

    /**
     *
     * @param parent the parent of this dialog.
     */
    public JDialogBase(Frame parent) {
        super(parent, true);
    }

    /**
     *
     */
    public void installEscapeCloseOperation() {
        JRootPane root = getRootPane();
        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeStroke, DISPATCH_WINDOW_CLOSING_ACTION_MAP_KEY);

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
        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(enterStroke, DISPATCH_WINDOW_CLOSING_ACTION_MAP_KEY);

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
