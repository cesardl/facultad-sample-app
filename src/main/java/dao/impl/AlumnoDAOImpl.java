package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import beans.Alumno;
import beans.etc.Gender;
import dao.AlumnoDAO;
import factory.DAOFactory;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cesardl
 */
public class AlumnoDAOImpl implements AlumnoDAO {

    @Override
    public List<Alumno> getAll() {
        String sql = "SELECT id_alum, cod_alum, nom_alum, nacimiento_alum, sexo_alum, direc_alum, telef_alum FROM alumno";

        List<Alumno> alumnos = new ArrayList<>();

        try (Connection connection = DAOFactory.createConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                alumnos.add(new Alumno(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4),
                        Gender.valueOf(resultSet.getString(5)),
                        resultSet.getString(6),
                        resultSet.getString(7)));
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.error("AlumnoDAO: Error al cargar los datos de alumnos", e);
        }

        return alumnos;
    }

    @Override
    public int insert(Alumno entity) {
        String sql = "INSERT INTO alumno(id_alum, cod_alum, nom_alum, nacimiento_alum, sexo_alum, direc_alum, telef_alum, profesor_id_prof) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        int state;
        
        try (Connection connection = DAOFactory.createConnection();
                PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getCodigo());
            ps.setString(3, entity.getNombre());
            ps.setDate(4, new Date(entity.getNacimiento().getTime()));
            ps.setString(5, entity.getSexo().name());
            ps.setString(6, entity.getDireccion());
            ps.setString(7, entity.getTelefono());
            ps.setInt(8, entity.getProfesor());
            
            state = ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            log.error("AlumnoDAO: Error al insertar los datos de alumnos", e);
            state = STATE_ERROR;
        }

        return state;
    }
}
