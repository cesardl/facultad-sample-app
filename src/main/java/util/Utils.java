package util;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import view.JFrameInit;

/**
 * Clase con distintos metodos para gestionar la ventana.
 *
 * @author Cesardl
 */
public class Utils {

    public static final int ERROR_INT = -999;

    private static final KeyStroke ESCAPE_STROKE
            = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
    private static final KeyStroke ENTER_STROKE
            = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
    public static final String DISPATCH_WINDOW_CLOSING_ACTION_MAP_KEY
            = JFrameInit.class.getCanonicalName() + ":WINDOW_CLOSING";

    /**
     * Devuelve una cadena convertida en String, en caso de encontrarse un error
     * envia un codigo de error.
     *
     * @param s cadena que se va a convertir a entero
     * @return el valor de la cadena.
     */
    public static int aInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return ERROR_INT;
        }
    }

    /**
     * Convierte una cadena en una secuencia de palabras donde el primer
     * caracter se encuentra en mayusculas y los siguientes en minusculas,
     * separados cada uno por un espacio.
     *
     * @param s cadena a transformar
     * @return la cadena arreglada
     */
    public static String stringStandard(String s) {
        java.util.StringTokenizer st = new java.util.StringTokenizer(s, " ");
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            sb.append(convertirString(st.nextToken()));
            sb.append(" ");
        }
        return new String(sb).trim();
    }

    /**
     * Transforma el primer caracter de la cadena en mayuscula y los siguientes
     * en minusculas
     *
     * @param s a convertir
     * @return la cadena convertida
     */
    private static String convertirString(String s) {
        return String.valueOf(s.charAt(0)).toUpperCase()
                + s.substring(1).toLowerCase();
    }

    /**
     * Selecciona los elementos ingresados en una casilla de texto
     *
     * @param jTextField
     */
    public static void marcarTextField(javax.swing.JTextField jTextField) {
        jTextField.requestFocus();
        jTextField.selectAll();
    }

    /**
     * Muestra una alerta
     *
     * @param mensaje
     * @param tipoMensaje
     */
    public static void alert(String mensaje, int tipoMensaje) {
        javax.swing.JOptionPane.showMessageDialog(null, mensaje,
                "Aplicacion con MySQL", tipoMensaje);
    }

    /**
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        SimpleDateFormat dt = new SimpleDateFormat("dd/MMM/y");
        return dt.format(date);
    }

    /**
     *
     * @param dialog
     */
    public static void installEscapeCloseOperation(final JDialog dialog) {
        JRootPane root = dialog.getRootPane();
        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ESCAPE_STROKE, DISPATCH_WINDOW_CLOSING_ACTION_MAP_KEY);

        root.getActionMap().put(DISPATCH_WINDOW_CLOSING_ACTION_MAP_KEY, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                dialog.dispatchEvent(new WindowEvent(
                        dialog, WindowEvent.WINDOW_CLOSING
                ));
            }
        });
    }

    /**
     *
     * @param dialog
     */
    public static void installEnterCloseOperation(final JDialog dialog) {
        JRootPane root = dialog.getRootPane();
        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ENTER_STROKE, DISPATCH_WINDOW_CLOSING_ACTION_MAP_KEY);

        root.getActionMap().put(DISPATCH_WINDOW_CLOSING_ACTION_MAP_KEY, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                dialog.dispatchEvent(new WindowEvent(
                        dialog, WindowEvent.WINDOW_CLOSING
                ));
            }
        });
    }

}
