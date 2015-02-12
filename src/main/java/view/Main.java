package view;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.log4j.Logger;

// TODO mensajes de error en formularios
// TODO Internationalizacion (english - spanish)
// TODO Confirmacion al borrar registro

/**
 *
 * @author Cesardl
 */
public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            javax.swing.SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    new JFrameInit().setVisible(true);
                }
            });
        } catch (ClassNotFoundException e) {
            log.error("Error with class", e);
        } catch (InstantiationException e) {
            log.error("Error while instantiation", e);
        } catch (IllegalAccessException e) {
            log.error("Error illegal access", e);
        } catch (UnsupportedLookAndFeelException e) {
            log.error("Error loading look and feel", e);
        }
    }

}
