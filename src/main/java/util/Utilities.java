package util;

/**
 * Clase con distintos metodos para gestionar la ventana.
 *
 * @author cesardl
 */
public class Utilities {

    public static final int ERROR_INT = -999;

    /**
     * M&eacute;todo que devuelve una cadena convertida en String, en caso de
     * encontrarse un error envia un codigo de error.
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
     * M&eacute;todo que convierte una cadena en una secuencia de palabras donde
     * el primer caracter se encuentra en mayusculas y los siguientes en
     * minusculas, separados cada uno por un espacio.
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

}
