package view;

/**
 *
 * @author Cesardl
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new JFrameInit().setVisible(true);
            }
        });
    }

}
