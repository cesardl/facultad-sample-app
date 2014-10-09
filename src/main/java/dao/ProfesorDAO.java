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
import beans.Profesor;
import java.util.ArrayList;
import java.util.Collection;
import util.Utilities;

/**
 *
 * @author cesardl
 */
public class ProfesorDAO {
    //creacion de variables requeridas para la conexion

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public Collection<Profesor> seleccionarDatos() {
        String sql = "SELECT id_prof, cod_prof, nom_prof, edad_prof, email_prof FROM profesor;";

        Collection<Profesor> profesores = new ArrayList<>();
        try {
            connection = JConnection.getConexion();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                profesores.add(new Profesor(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5)));
            }
        } catch (SQLException sqle) {
            System.err.println("ProfesorDAO: Error al cargar los datos de los profesores");
            sqle.printStackTrace();
        } finally {
            Utilities.cerrarConexion(connection);
        }
        return profesores;
    }

    public Collection<Profesor> seleccionarNombresProfesor() {
        String sql = "SELECT id_prof, nom_prof , edad_prof, email_prof FROM profesor;";

        Collection<Profesor> profesores = new ArrayList<Profesor>();
        try {
            connection = JConnection.getConexion();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Profesor p = new Profesor();
                p.setId_prof(resultSet.getInt(1));
                p.setNom_prof(resultSet.getString(2));

                profesores.add(p);
            }
        } catch (SQLException sqle) {
            System.err.println("ProfesorDAO: Error al cargar los datos de los profesores");
            sqle.printStackTrace();
        } finally {
            Utilities.cerrarConexion(connection);
        }
        return profesores;
    }
}
