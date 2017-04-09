package org.sanmarcux.dao.impl;

import org.apache.log4j.Logger;
import org.sanmarcux.beans.Teacher;
import org.sanmarcux.dao.TeacherDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author Cesardl
 */
@Repository
public class TeacherDAOImpl implements TeacherDAO {

    private static final Logger LOG = Logger.getLogger(TeacherDAOImpl.class);

    private JdbcTemplate jdbcTemplate;

    public TeacherDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Teacher> selectAll() {
        String sql = "SELECT id_prof, cod_prof, nom_prof, nacimiento_prof, email_prof FROM profesor";

        List<Teacher> teachers = jdbcTemplate.query(sql, (resultSet, rowNum) -> new Teacher(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getDate(4),
                resultSet.getString(5)));
        LOG.info(String.format("Getting teacher %s", teachers));
        return teachers;
    }

    @Override
    public Teacher selectByCode(String code) {
        String sql = "SELECT id_prof, cod_prof, nom_prof, nacimiento_prof, email_prof FROM profesor WHERE cod_prof = ?";

        Teacher teacher = jdbcTemplate.queryForObject(sql, new Object[]{code}, (resultSet, rowNum) -> new Teacher(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4),
                        resultSet.getString(5))
        );
        LOG.info(String.format("Getting teacher %s", teacher));
        return teacher;
    }

    @Override
    public List<Teacher> selectNames() {
        String sql = "SELECT id_prof, nom_prof FROM profesor";

        List<Teacher> teachers = jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Teacher p = new Teacher();
            p.setId(resultSet.getInt(1));
            p.setNames(resultSet.getString(2));
            return p;
        });
        LOG.info(String.format("Getting %d teachers", teachers.size()));
        return teachers;
    }

    @Override
    public int insert(Teacher entity) {
//        String sql = "INSERT INTO profesor(id_prof, cod_prof, nom_prof, nacimiento_prof, email_prof) VALUES (?, ?, ?, ?, ?)";
//
//        int state;
//
//        try (Connection c = DAOFactory.createConnection();
//             PreparedStatement ps = c.prepareStatement(sql)) {
//
//            ps.setInt(1, entity.getId());
//            ps.setString(2, entity.getCode());
//            ps.setString(3, entity.getNames());
//            ps.setDate(4, new Date(entity.getBirthday().getTime()));
//            ps.setString(5, entity.getEmail());
//
//            state = ps.executeUpdate();
//        } catch (SQLException e) {
//            LOG.error("Error al insertar los datos del profesor", e);
//            state = STATE_ERROR;
//        }
//
//        return state;
        throw new UnsupportedOperationException();
    }

    @Override
    public int update(Teacher entity) {
//        String sql = "UPDATE profesor SET cod_prof = ?, nom_prof = ?, nacimiento_prof = ?, email_prof = ? WHERE id_prof = ?";
//
//        int state;
//
//        try (Connection c = DAOFactory.createConnection();
//             PreparedStatement ps = c.prepareStatement(sql)) {
//
//            ps.setString(1, entity.getCode());
//            ps.setString(2, entity.getNames());
//            ps.setDate(3, new Date(entity.getBirthday().getTime()));
//            ps.setString(4, entity.getEmail());
//            ps.setInt(5, entity.getId());
//
//            state = ps.executeUpdate();
//        } catch (SQLException e) {
//            LOG.error("Error al actualizar los datos del profesor", e);
//            state = STATE_ERROR;
//        }
//
//        return state;
        throw new UnsupportedOperationException();
    }

    @Override
    public int delete(Teacher entity) {
//        String sql = "DELETE FROM profesor WHERE cod_prof = ?";
//
//        int state;
//
//        try (Connection c = DAOFactory.createConnection();
//             PreparedStatement ps = c.prepareStatement(sql)) {
//
//            ps.setString(1, entity.getCode());
//
//            state = ps.executeUpdate();
//        } catch (SQLException e) {
//            LOG.error("Error al eliminar los datos del profesor", e);
//            state = STATE_ERROR;
//        }
//
//        return state;
        throw new UnsupportedOperationException();
    }

    @Override
    public int selectIdByCode(String code) {
//        String sql = "SELECT id_prof FROM profesor WHERE cod_prof = ?";
//
//        int id = 0;
//
//        try (Connection c = DAOFactory.createConnection();
//             PreparedStatement ps = c.prepareStatement(sql)) {
//
//            ps.setString(1, code);
//
//            try (ResultSet rs = ps.executeQuery()) {
//                if (rs.next()) {
//                    id = rs.getInt(1);
//                }
//            }
//        } catch (SQLException e) {
//            LOG.error("Error al cargar el id del profesor", e);
//            id = STATE_ERROR;
//        }
//
//        return id;
        throw new UnsupportedOperationException();
    }

}
