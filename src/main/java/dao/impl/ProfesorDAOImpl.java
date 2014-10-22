package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import beans.Profesor;
import dao.ProfesorDAO;
import factory.DAOFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cesardl
 */
public class ProfesorDAOImpl implements ProfesorDAO {

    /**
     *
     * @return
     */
    @Override
    public List<Profesor> seleccionarDatos() {
        String sql = "SELECT id_prof, cod_prof, nom_prof, nacimiento_prof, email_prof FROM profesor;";

        List<Profesor> profesores = new ArrayList<>();

        try (Connection connection = DAOFactory.createConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                //TODO Corregir campo nacimiento 
                profesores.add(new Profesor(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5)));
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.error("ProfesorDAO: Error al cargar los datos de los profesores", e);
        }

        return profesores;
    }

    @Override
    public List<Profesor> seleccionarNombresProfesor() {
        String sql = "SELECT id_prof, nom_prof , nacimiento_prof, email_prof FROM profesor;";

        List<Profesor> profesores = new ArrayList<>();

        try (Connection connection = DAOFactory.createConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                //TODO Corregir campo nacimiento
                Profesor p = new Profesor();
                p.setId(resultSet.getInt(1));
                p.setNombre(resultSet.getString(2));

                profesores.add(p);
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.error("ProfesorDAO: Error al cargar los datos de los profesores", e);
        }

        return profesores;
    }
}
