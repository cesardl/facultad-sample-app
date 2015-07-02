package app;

import dao.StudentDAO;
import dao.TeacherDAO;
import factory.DAOFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author cesardiaz
 */
public class TeacherDAOTest {

    @Test
    public void teacherGetIdByCode() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        TeacherDAO dao = factory.getTeacherDAO();

        // No existe este ID en la tabla
        int id = dao.selectIdByCode("00000");
        assertEquals(id, 0);

        id = dao.selectIdByCode("212963");
        assertEquals(id, 3);
    }

}
