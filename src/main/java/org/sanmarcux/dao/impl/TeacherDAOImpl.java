package org.sanmarcux.dao.impl;

import org.sanmarcux.beans.Teacher;
import org.sanmarcux.dao.TeacherDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.List;

/**
 * @author Cesardl
 */
@Repository
public class TeacherDAOImpl implements TeacherDAO {

    private static final Logger LOG = LoggerFactory.getLogger(TeacherDAOImpl.class);

    private final JdbcTemplate jdbcTemplate;

    /**
     * @param dataSource database connection
     */
    public TeacherDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Teacher> selectAll() {
        String sql = "SELECT id_prof, cod_prof, nom_prof, nacimiento_prof, email_prof, COUNT(id_alum) FROM profesor " +
                "LEFT JOIN alumno ON profesor_id_prof = id_prof GROUP BY id_prof";

        List<Teacher> teachers = jdbcTemplate.query(sql, (resultSet, rowNum) ->
                new Teacher(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4),
                        resultSet.getString(5),
                        resultSet.getInt(6))
        );
        LOG.info("Getting names from {} teachers", teachers.size());
        return teachers;
    }

    @Override
    public Teacher selectByCode(final String code) {
        String sql = "SELECT id_prof, cod_prof, nom_prof, nacimiento_prof, email_prof, COUNT(id_alum) FROM profesor " +
                "LEFT JOIN alumno ON profesor_id_prof = id_prof WHERE cod_prof = ? GROUP BY id_prof";

        Teacher teacher = jdbcTemplate.queryForObject(sql, new Object[]{code}, (resultSet, rowNum) ->
                new Teacher(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4),
                        resultSet.getString(5),
                        resultSet.getInt(6))
        );
        LOG.info("Getting teacher [ id: '{}', code: '{}', names: '{}', email: '{}', email: '{}' ]",
                teacher.getId(), teacher.getCode(), teacher.getNames(), teacher.getEmail(), teacher.getAssignedStudents());
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
        LOG.info("Getting {} teachers", teachers.size());
        return teachers;
    }

    @Override
    public int insert(final Teacher entity) {
        String sql = "INSERT INTO profesor(cod_prof, nom_prof, nacimiento_prof, email_prof) VALUES (?, ?, ?, ?)";

        int rowsAffected = jdbcTemplate.update(sql, entity.getCode(), entity.getNames(),
                new Date(entity.getBirthday().getTime()), entity.getEmail());
        LOG.info("Creating new teacher, operation result: {}", rowsAffected);
        return rowsAffected;
    }

    @Override
    public int update(final Teacher entity) {
        String sql = "UPDATE profesor SET cod_prof = ?, nom_prof = ?, nacimiento_prof = ?, email_prof = ? WHERE id_prof = ?";

        int rowsAffected = jdbcTemplate.update(sql, entity.getCode(), entity.getNames(),
                new Date(entity.getBirthday().getTime()), entity.getEmail(), entity.getId());
        LOG.info("Updating teacher, operation result: {}", rowsAffected);
        return rowsAffected;
    }

    @Override
    public int findAssignedStudents(final String code) {
        String sql = "SELECT COUNT(1) FROM alumno " +
                "INNER JOIN profesor ON id_prof = profesor_id_prof WHERE cod_prof = ?";

        int assignedStudents = jdbcTemplate.queryForObject(sql, new Object[]{code}, Integer.class);
        LOG.info("Getting {} assigned students", assignedStudents);
        return assignedStudents;
    }

    @Override
    public int delete(final Teacher entity) {
        String sql = "DELETE FROM profesor WHERE cod_prof = ?";

        int rowsAffected = jdbcTemplate.update(sql, entity.getCode());
        LOG.info("Deleting student, operation result: {}", rowsAffected);
        return rowsAffected;
    }

    @Override
    public int selectIdByCode(final String code) {
        try {
            String sql = "SELECT id_prof FROM profesor WHERE cod_prof = ?";

            Integer id = jdbcTemplate.queryForObject(sql, new Object[]{code}, Integer.class);
            LOG.info("Getting id [{}] from code [{}]", id, code);
            return id;
        } catch (EmptyResultDataAccessException e) {
            LOG.warn("No results obtained with code [{}]", code, e);
            return 0;
        }
    }

}
