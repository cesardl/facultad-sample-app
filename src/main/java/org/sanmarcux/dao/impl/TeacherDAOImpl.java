package org.sanmarcux.dao.impl;

import org.apache.log4j.Logger;
import org.sanmarcux.beans.Teacher;
import org.sanmarcux.dao.TeacherDAO;
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
        LOG.info(String.format("Getting names from %d teachers", teachers.size()));
        return teachers;
    }

    @Override
    public Teacher selectByCode(final String code) {
        String sql = "SELECT id_prof, cod_prof, nom_prof, nacimiento_prof, email_prof FROM profesor WHERE cod_prof = ?";

        Teacher teacher = jdbcTemplate.queryForObject(sql, new Object[]{code}, (resultSet, rowNum) -> new Teacher(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4),
                        resultSet.getString(5))
        );
        LOG.info(String.format("Getting teacher [ id: '%s', code: '%s', names: '%s', email: '%s' ]",
                teacher.getId(), teacher.getCode(), teacher.getNames(), teacher.getEmail()));
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
    public int insert(final Teacher entity) {
        String sql = "INSERT INTO profesor(cod_prof, nom_prof, nacimiento_prof, email_prof) VALUES (?, ?, ?, ?)";

        int rowsAffected = jdbcTemplate.update(sql, entity.getCode(), entity.getNames(),
                new Date(entity.getBirthday().getTime()), entity.getEmail());
        LOG.info(String.format("Creating new teacher, operation result: %d", rowsAffected));
        return rowsAffected;
    }

    @Override
    public int update(final Teacher entity) {
        String sql = "UPDATE profesor SET cod_prof = ?, nom_prof = ?, nacimiento_prof = ?, email_prof = ? WHERE id_prof = ?";

        int rowsAffected = jdbcTemplate.update(sql, entity.getCode(), entity.getNames(),
                new Date(entity.getBirthday().getTime()), entity.getEmail(), entity.getId());
        LOG.info(String.format("Updating teacher, operation result: %d", rowsAffected));
        return rowsAffected;
    }

    @Override
    public int delete(final Teacher entity) {
        String sql = "DELETE FROM profesor WHERE cod_prof = ?";

        int rowsAffected = jdbcTemplate.update(sql, entity.getCode());
        LOG.info(String.format("Deleting student, operation result: %d", rowsAffected));
        return rowsAffected;
    }

    @Override
    public int selectIdByCode(final String code) {
        String sql = "SELECT id_prof FROM profesor WHERE cod_prof = ?";

        Integer id = jdbcTemplate.queryForObject(sql, new Object[]{code}, Integer.class);
        LOG.info(String.format("Getting id [%d] from code [%s]", id, code));
        return id;
    }

}
