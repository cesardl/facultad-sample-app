/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import bd.JConnection;
import beans.Alumno;
import java.util.ArrayList;
import java.util.Collection;
import util.Utilities;

/**
 *
 * @author cesardl
 */
public class AlumnoDAO {

    //creacion de variables requeridas para la conexion
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public Collection<Alumno> seleccionarDatos() {
        String sql = "SELECT id_alum, cod_alum, nom_alum, edad_alum, sexo_alum, direc_alum, telef_alum FROM alumno;";

        Collection<Alumno> alumnos = new ArrayList<>();
        try {
            connection = JConnection.getConexion();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                alumnos.add(new Alumno(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5).charAt(0),
                        resultSet.getString(6),
                        resultSet.getString(7)));
            }
        } catch (SQLException sqle) {
            System.err.println("AlumnoDAO: Error al cargar los datos de alumnos");
            sqle.printStackTrace();
        } finally {
            Utilities.cerrarConexion(connection);
        }
        return alumnos;
    }
}
