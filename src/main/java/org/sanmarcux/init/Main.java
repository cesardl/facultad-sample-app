package org.sanmarcux.init;

import javax.swing.UIManager;
import org.apache.log4j.Logger;
import org.sanmarcux.view.JFrameInit;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Cesardl
 */
public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        JFrameInit mainFrame = context.getBean(JFrameInit.class);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            javax.swing.SwingUtilities.invokeLater(() -> mainFrame.setVisible(true));
        } catch (ClassNotFoundException e) {
            LOG.error("Error with class", e);
        } catch (InstantiationException e) {
            LOG.error("Error while instantiation", e);
        } catch (IllegalAccessException e) {
            LOG.error("Error illegal access", e);
        } catch (javax.swing.UnsupportedLookAndFeelException e) {
            LOG.error("Error loading look and feel", e);
        }
    }

}
