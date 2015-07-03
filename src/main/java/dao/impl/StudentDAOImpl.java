package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import beans.Student;
import beans.etc.Gender;
import dao.StudentDAO;
import factory.DAOFactory;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cesardl
 */
public class StudentDAOImpl implements StudentDAO {

    @Override
    public List<Student> selectAll() {
        String sql = "SELECT id_alum, cod_alum, nom_alum, nacimiento_alum, sexo_alum, direc_alum, telef_alum FROM alumno";

        List<Student> students = new ArrayList<>();

        try (Connection c = DAOFactory.createConnection();
                Statement st = c.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        Gender.valueOf(rs.getString(5)),
                        rs.getString(6),
                        rs.getString(7)));
            }
        } catch (SQLException e) {
            log.error("Error al cargar los datos de alumnos", e);
            students = null;
        }

        return students;
    }

    @Override
    public Student selectByCode(String code) {
        String sql = "SELECT id_alum, nom_alum, nacimiento_alum, sexo_alum, direc_alum, telef_alum, profesor_id_prof FROM alumno WHERE cod_alum = ?";

        Student student = null;

        try (Connection c = DAOFactory.createConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, code);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    student = new Student(
                            rs.getInt(1),
                            code,
                            rs.getString(2),
                            rs.getDate(3),
                            Gender.valueOf(rs.getString(4)),
                            rs.getString(5),
                            rs.getString(6));
                    student.setTeacherId(rs.getInt(7));
                }
            }
        } catch (SQLException e) {
            log.error("Error al cargar los datos del alumno", e);
            student = null;
        }

        return student;
    }

    @Override
    public int insert(Student entity) {
        String sql = "INSERT INTO alumno(id_alum, cod_alum, nom_alum, nacimiento_alum, sexo_alum, direc_alum, telef_alum, profesor_id_prof) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        int state;

        try (Connection c = DAOFactory.createConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getCode());
            ps.setString(3, entity.getNames());
            ps.setDate(4, new Date(entity.getBirthday().getTime()));
            ps.setString(5, entity.getGender().name());
            ps.setString(6, entity.getDirection());
            ps.setString(7, entity.getTelefono());
            ps.setInt(8, entity.getTeacherId());

            state = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Error al insertar los datos del alumno", e);
            state = STATE_ERROR;
        }

        return state;
    }

    @Override
    public int update(Student entity) {
        String sql = "UPDATE alumno SET cod_alum = ?, nom_alum = ?, nacimiento_alum = ?, sexo_alum = ?, direc_alum = ?, telef_alum = ?, profesor_id_prof = ? WHERE id_alum = ?";

        int state;

        try (Connection c = DAOFactory.createConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, entity.getCode());
            ps.setString(2, entity.getNames());
            ps.setDate(3, new Date(entity.getBirthday().getTime()));
            ps.setString(4, entity.getGender().name());
            ps.setString(5, entity.getDirection());
            ps.setString(6, entity.getTelefono());
            ps.setInt(7, entity.getTeacherId());
            ps.setInt(8, entity.getId());

            state = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Error al actualizar los datos del alumno", e);
            state = STATE_ERROR;
        }

        return state;
    }

    @Override
    public int delete(Student entity) {
        String sql = "DELETE FROM alumno WHERE cod_alum = ?";

        int state;

        try (Connection c = DAOFactory.createConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, entity.getCode());

            state = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Error al eliminar los datos del alumno", e);
            state = STATE_ERROR;
        }

        return state;
    }

    @Override
    public int selectIdByCode(String code) {
        String sql = "SELECT id_alum FROM alumno WHERE cod_alum = ?";

        int id = 0;

        try (Connection c = DAOFactory.createConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, code);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            log.error("Error al cargar el id del alumno", e);
            id = STATE_ERROR;
        }

        return id;
    }

}
