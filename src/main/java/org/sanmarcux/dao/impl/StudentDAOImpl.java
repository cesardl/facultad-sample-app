package org.sanmarcux.dao.impl;

import org.apache.log4j.Logger;
import org.sanmarcux.beans.Student;
import org.sanmarcux.beans.etc.Gender;
import org.sanmarcux.dao.StudentDAO;
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

    private static final Logger LOG = Logger.getLogger(StudentDAOImpl.class);

    private JdbcTemplate jdbcTemplate;

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
        LOG.info(String.format("Getting %d students", students.size()));
        return students;
    }

    @Override
    public Student selectByCode(String code) {
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
        LOG.info(String.format("Getting student %s", student));
        return student;
    }

    @Override
    public int insert(Student entity) {
        String sql = "INSERT INTO alumno(id_alum, cod_alum, nom_alum, nacimiento_alum, sexo_alum, direc_alum, telef_alum, profesor_id_prof) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        int rowsAffected = jdbcTemplate.update(sql, entity.getId(), entity.getCode(), entity.getNames(), new Date(entity.getBirthday().getTime()),
                entity.getGender().name(), entity.getAddress(), entity.getPhone(), entity.getTeacherId());
        LOG.info(String.format("Creating new student, operation result: %d", rowsAffected));
        return rowsAffected;
    }

    @Override
    public int update(Student entity) {
        String sql = "UPDATE alumno SET cod_alum = ?, nom_alum = ?, nacimiento_alum = ?, sexo_alum = ?, direc_alum = ?, telef_alum = ?, profesor_id_prof = ? WHERE id_alum = ?";

        int rowsAffected = jdbcTemplate.update(sql, entity.getCode(), entity.getNames(), new Date(entity.getBirthday().getTime()),
                entity.getGender().name(), entity.getAddress(), entity.getPhone(), entity.getTeacherId(), entity.getId());
        LOG.info(String.format("Updating student, operation result: %d", rowsAffected));
        return rowsAffected;
    }

    @Override
    public int delete(Student entity) {
        String sql = "DELETE FROM alumno WHERE cod_alum = ?";

        int rowsAffected = jdbcTemplate.update(sql, entity.getCode());
        LOG.info(String.format("Deleting student, operation result: %d", rowsAffected));
        return rowsAffected;
    }

    @Override
    public int selectIdByCode(String code) {
        String sql = "SELECT id_alum FROM alumno WHERE cod_alum = ?";

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
//            LOG.error("Error al cargar el id del alumno", e);
//            id = STATE_ERROR;
//        }
//
//        return id;
        Integer id = jdbcTemplate.queryForObject(sql, new Object[]{code}, Integer.class);
        LOG.info(String.format("Getting id [%d] from code [%s]", id, code));
        return id;
    }

}
