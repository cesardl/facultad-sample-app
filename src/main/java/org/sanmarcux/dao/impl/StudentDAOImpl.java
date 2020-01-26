package org.sanmarcux.dao.impl;

import org.sanmarcux.beans.Student;
import org.sanmarcux.beans.etc.Gender;
import org.sanmarcux.dao.StudentDAO;
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
public class StudentDAOImpl implements StudentDAO {

    private static final Logger LOG = LoggerFactory.getLogger(StudentDAOImpl.class);

    private final JdbcTemplate jdbcTemplate;

    /**
     * @param dataSource database connection
     */
    public StudentDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Student> selectAll() {
        String sql = "SELECT id_alum, cod_alum, nom_alum, nacimiento_alum, sexo_alum, direc_alum, telef_alum FROM alumno";

        List<Student> students = jdbcTemplate.query(sql, (resultSet, rowNum) -> new Student(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getDate(4),
                Gender.valueOf(resultSet.getString(5)),
                resultSet.getString(6),
                resultSet.getString(7)));
        LOG.info("Getting {} students", students.size());
        return students;
    }

    @Override
    public Student selectByCode(final String code) {
        String sql = "SELECT id_alum, nom_alum, nacimiento_alum, sexo_alum, direc_alum, telef_alum, profesor_id_prof FROM alumno WHERE cod_alum = ?";

        Student student = jdbcTemplate.queryForObject(sql, new Object[]{code}, (resultSet, rowNum) -> {
            final Student e = new Student(
                    resultSet.getInt(1),
                    code,
                    resultSet.getString(2),
                    resultSet.getDate(3),
                    Gender.valueOf(resultSet.getString(4)),
                    resultSet.getString(5),
                    resultSet.getString(6));
            e.setTeacherId(resultSet.getInt(7));
            return e;
        });
        LOG.info("Getting student [ id: '{}', code: '{}', names: '{}', address: '{}' ]",
                student.getId(), student.getCode(), student.getNames(), student.getAddress());
        return student;
    }

    @Override
    public int insert(final Student entity) {
        String sql = "INSERT INTO alumno(cod_alum, nom_alum, nacimiento_alum, sexo_alum, direc_alum, telef_alum, profesor_id_prof) VALUES (?, ?, ?, ?, ?, ?, ?)";

        int rowsAffected = jdbcTemplate.update(sql, entity.getCode(), entity.getNames(), new Date(entity.getBirthday().getTime()),
                entity.getGender().name(), entity.getAddress(), entity.getPhone(), entity.getTeacherId());
        LOG.info("Creating new student, operation result: {}", rowsAffected);
        return rowsAffected;
    }

    @Override
    public int update(final Student entity) {
        String sql = "UPDATE alumno SET cod_alum = ?, nom_alum = ?, nacimiento_alum = ?, sexo_alum = ?, direc_alum = ?, telef_alum = ?, profesor_id_prof = ? WHERE id_alum = ?";

        int rowsAffected = jdbcTemplate.update(sql, entity.getCode(), entity.getNames(), new Date(entity.getBirthday().getTime()),
                entity.getGender().name(), entity.getAddress(), entity.getPhone(), entity.getTeacherId(), entity.getId());
        LOG.info("Updating student, operation result: {}", rowsAffected);
        return rowsAffected;
    }

    @Override
    public int delete(final Student entity) {
        String sql = "DELETE FROM alumno WHERE cod_alum = ?";

        int rowsAffected = jdbcTemplate.update(sql, entity.getCode());
        LOG.info("Deleting student, operation result: {}", rowsAffected);
        return rowsAffected;
    }

    @Override
    public int selectIdByCode(final String code) {
        try {
            String sql = "SELECT id_alum FROM alumno WHERE cod_alum = ?";

            Integer id = jdbcTemplate.queryForObject(sql, new Object[]{code}, Integer.class);
            LOG.info("Getting id [{}] from code [{}]", id, code);
            return id;
        } catch (EmptyResultDataAccessException e) {
            LOG.warn("No results obtained with code [{}]", code, e);
            return 0;
        }
    }

}
