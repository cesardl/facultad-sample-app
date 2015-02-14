package app;

import dao.StudentDAO;
import dao.TeacherDAO;
import factory.DAOFactory;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author cesardiaz
 */
public class DaoTest {

    @Test
    public void alumnoGetIdByCode() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        StudentDAO dao = factory.getStudentDAO();

        // No existe este ID en la tabla
        int id = dao.selectIdByCode("20011");
        assertEquals(id, 0);

        id = dao.selectIdByCode("200170");
        assertEquals(id, 6);
    }

    @Test
    public void profesorGetIdByCode() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        TeacherDAO dao = factory.getTeacherDAO();

        // No existe este ID en la tabla
        int id = dao.selectIdByCode("00000");
        assertEquals(id, 0);

        id = dao.selectIdByCode("212963");
        assertEquals(id, 3);
    }

}
