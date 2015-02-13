package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import beans.Teacher;
import static dao.DAO.STATE_ERROR;
import static dao.DAO.log;
import dao.TeacherDAO;
import factory.DAOFactory;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cesardl
 */
public class TeacherDAOImpl implements TeacherDAO {

    /**
     *
     * @return
     */
    @Override
    public List<Teacher> selectAll() {
        String sql = "SELECT id_prof, cod_prof, nom_prof, nacimiento_prof, email_prof FROM profesor";

        List<Teacher> profesores = new ArrayList<>();

        try (Connection connection = DAOFactory.createConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                profesores.add(new Teacher(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4),
                        resultSet.getString(5)));
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.error("Error al cargar los datos de los profesores", e);
            profesores = null;
        }

        return profesores;
    }

    @Override
    public Teacher selectByCode(String code) {
        String sql = "SELECT id_prof, cod_prof, nom_prof, nacimiento_prof, email_prof FROM profesor WHERE cod_prof = ?";

        Teacher profesor = null;

        try (Connection connection = DAOFactory.createConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, code);

            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    profesor = new Teacher(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDate(4),
                            resultSet.getString(5));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.error("Error al cargar los datos del profesor", e);
            profesor = null;
        }

        return profesor;
    }

    @Override
    public List<Teacher> selectNames() {
        String sql = "SELECT id_prof, nom_prof FROM profesor";

        List<Teacher> profesores = new ArrayList<>();

        try (Connection connection = DAOFactory.createConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Teacher p = new Teacher();
                p.setId(resultSet.getInt(1));
                p.setNombre(resultSet.getString(2));

                profesores.add(p);
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.error("Error al cargar los datos de los profesores", e);
            profesores = null;
        }

        return profesores;
    }

    @Override
    public int insert(Teacher entity) {
        String sql = "INSERT INTO profesor(id_prof, cod_prof, nom_prof, nacimiento_prof, email_prof) VALUES (?, ?, ?, ?, ?)";

        int state;

        try (Connection connection = DAOFactory.createConnection();
                PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getCodigo());
            ps.setString(3, entity.getNombre());
            ps.setDate(4, new Date(entity.getNacimiento().getTime()));
            ps.setString(5, entity.getEmail());

            state = ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            log.error("Error al insertar los datos del profesor", e);
            state = STATE_ERROR;
        }

        return state;
    }

    @Override
    public int update(Teacher entity) {
        String sql = "UPDATE profesor SET cod_prof = ?, nom_prof = ?, nacimiento_prof = ?, email_prof = ? WHERE id_prof = ?";

        int state;

        try (Connection connection = DAOFactory.createConnection();
                PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setString(1, entity.getCodigo());
            ps.setString(2, entity.getNombre());
            ps.setDate(3, new Date(entity.getNacimiento().getTime()));
            ps.setString(4, entity.getEmail());
            ps.setInt(5, entity.getId());

            state = ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            log.error("Error al actualizar los datos del profesor", e);
            state = STATE_ERROR;
        }

        return state;
    }

    @Override
    public int delete(Teacher entity) {
        String sql = "DELETE FROM profesor WHERE cod_prof = ?";

        int state;

        try (Connection connection = DAOFactory.createConnection();
                PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setString(1, entity.getCodigo());

            state = ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            log.error("Error al eliminar los datos del profesor", e);
            state = STATE_ERROR;
        }

        return state;
    }

    @Override
    public int selectIdByCode(String code) {
        String sql = "SELECT id_prof FROM profesor WHERE cod_prof = ?";

        int id = 0;

        try (Connection connection = DAOFactory.createConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, code);

            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.error("Error al cargar el id del profesor", e);
            id = STATE_ERROR;
        }

        return id;
    }

}
