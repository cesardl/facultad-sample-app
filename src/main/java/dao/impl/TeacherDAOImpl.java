package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import beans.Teacher;
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

        List<Teacher> teachers = new ArrayList<>();

        try (Connection c = DAOFactory.createConnection();
                Statement st = c.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                teachers.add(new Teacher(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getString(5)));
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.error("Error al cargar los datos de los profesores", e);
            teachers = null;
        }

        return teachers;
    }

    @Override
    public Teacher selectByCode(String code) {
        String sql = "SELECT id_prof, cod_prof, nom_prof, nacimiento_prof, email_prof FROM profesor WHERE cod_prof = ?";

        Teacher teacher = null;

        try (Connection c = DAOFactory.createConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, code);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    teacher = new Teacher(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4),
                            rs.getString(5));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.error("Error al cargar los datos del profesor", e);
            teacher = null;
        }

        return teacher;
    }

    @Override
    public List<Teacher> selectNames() {
        String sql = "SELECT id_prof, nom_prof FROM profesor";

        List<Teacher> teachers = new ArrayList<>();

        try (Connection c = DAOFactory.createConnection();
                Statement st = c.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Teacher p = new Teacher();
                p.setId(rs.getInt(1));
                p.setNames(rs.getString(2));

                teachers.add(p);
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.error("Error al cargar los datos de los profesores", e);
            teachers = null;
        }

        return teachers;
    }

    @Override
    public int insert(Teacher entity) {
        String sql = "INSERT INTO profesor(id_prof, cod_prof, nom_prof, nacimiento_prof, email_prof) VALUES (?, ?, ?, ?, ?)";

        int state;

        try (Connection c = DAOFactory.createConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getCode());
            ps.setString(3, entity.getNames());
            ps.setDate(4, new Date(entity.getBirthday().getTime()));
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

        try (Connection c = DAOFactory.createConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, entity.getCode());
            ps.setString(2, entity.getNames());
            ps.setDate(3, new Date(entity.getBirthday().getTime()));
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

        try (Connection c = DAOFactory.createConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, entity.getCode());

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

        try (Connection c = DAOFactory.createConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, code);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.error("Error al cargar el id del profesor", e);
            id = STATE_ERROR;
        }

        return id;
    }

}
