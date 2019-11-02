package org.sanmarcux;

import org.apache.log4j.Logger;
import org.sanmarcux.init.DatabaseConfig;
import org.sanmarcux.view.JFrameInit;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;

/**
 * @author Cesardl
 */
public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class)) {
                JFrameInit mainFrame = context.getBean(JFrameInit.class);

                javax.swing.SwingUtilities.invokeLater(() -> mainFrame.setVisible(true));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
