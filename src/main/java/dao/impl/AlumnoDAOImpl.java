package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import beans.Alumno;
import dao.AlumnoDAO;
import factory.DAOFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cesardl
 */
public class AlumnoDAOImpl implements AlumnoDAO {

    @Override
    public List<Alumno> seleccionarDatos() {
        String sql = "SELECT id_alum, cod_alum, nom_alum, edad_alum, sexo_alum, direc_alum, telef_alum FROM alumno;";

        List<Alumno> alumnos = new ArrayList<>();

        try (Connection connection = DAOFactory.createConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

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
        } catch (ClassNotFoundException | SQLException e) {
            log.error("AlumnoDAO: Error al cargar los datos de alumnos", e);
        }

        return alumnos;
    }
}
