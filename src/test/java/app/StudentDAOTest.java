package app;

import org.sanmarcux.dao.StudentDAO;
import org.sanmarcux.factory.DAOFactory;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author cesardiaz
 */
public class StudentDAOTest {

    @Test
    public void studentGetIdByCode() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        StudentDAO dao = factory.getStudentDAO();

        // No existe este ID en la tabla
        int id = dao.selectIdByCode("20011");
        assertEquals(id, 0);

        id = dao.selectIdByCode("200170");
        assertEquals(id, 6);
    }

}
