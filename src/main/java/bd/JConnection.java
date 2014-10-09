package bd;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Conector para Mysql
 *
 * @author Cesardl
 */
public class JConnection {

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/facultad";
    private static final String user = "root";
    private static final String passwd = "root";

    private static Connection conexion;

    /**
     * Metodo que crea una conexion a la base de datos
     *
     * @return un objeto java.sql.Connection
     */
    public static Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                Class.forName(driver);
                conexion = DriverManager.getConnection(url, user, passwd);
            }
            return conexion;
        } catch (Exception e) {
            System.err.println("JConnection: Error al generar la conexion");
            e.printStackTrace();
            return null;
        }
    }
}
