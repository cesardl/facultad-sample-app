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
    public List<Profesor> getAll() {
        String sql = "SELECT id_prof, cod_prof, nom_prof, nacimiento_prof, email_prof FROM profesor";

        List<Profesor> profesores = new ArrayList<>();

        try (Connection connection = DAOFactory.createConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                profesores.add(new Profesor(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4),
                        resultSet.getString(5)));
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.error("ProfesorDAO: Error al cargar los datos de los profesores", e);
        }

        return profesores;
    }

    @Override
    public List<Profesor> seleccionarNombresProfesor() {
        String sql = "SELECT id_prof, nom_prof FROM profesor";

        List<Profesor> profesores = new ArrayList<>();

        try (Connection connection = DAOFactory.createConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
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

    @Override
    public int insert(Profesor entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
