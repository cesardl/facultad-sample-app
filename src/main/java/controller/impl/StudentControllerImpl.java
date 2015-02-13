package controller.impl;

import beans.Student;
import controller.StudentController;
import dao.StudentDAO;
import dao.DAO;
import factory.DAOFactory;
import java.util.List;
import util.Utils;

/**
 *
 * @author Cesardl
 */
public class StudentControllerImpl implements StudentController {

    @Override
    public Object[][] getAll() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        StudentDAO dao = factory.getAlumnoDAO();

        List<Student> alumnos = dao.selectAll();

        Object[][] rowData = new Object[alumnos.size()][6];

        for (int i = 0; i < alumnos.size(); i++) {
            Student alumno = alumnos.get(i);

            rowData[i][0] = alumno.getCodigo();
            rowData[i][1] = alumno.getNombre();
            rowData[i][2] = Utils.formatDate(alumno.getNacimiento());
            rowData[i][3] = alumno.getSexo().getValue();
            rowData[i][4] = alumno.getDireccion();
            rowData[i][5] = alumno.getTelefono();
        }

        return rowData;
    }

    @Override
    public Student getByCode(String code) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        StudentDAO dao = factory.getAlumnoDAO();

        Student alumno = dao.selectByCode(code);

        return alumno;
    }

    @Override
    public boolean saveOrUpdate(Student entity) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        StudentDAO dao = factory.getAlumnoDAO();

        int state;

        if (entity.getId() == 0) {
            state = dao.insert(entity);
        } else {
            state = dao.update(entity);
        }

        switch (state) {
            case DAO.STATE_ERROR:
                return false;

            default:
                return true;
        }
    }

    @Override
    public boolean delete(String code) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        StudentDAO dao = factory.getAlumnoDAO();

        Student entity = new Student();
        entity.setCodigo(code);

        int state = dao.delete(entity);

        switch (state) {
            case DAO.STATE_ERROR:
                return false;

            default:
                return true;
        }
    }

    @Override
    public boolean existsCode(String code) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        StudentDAO dao = factory.getAlumnoDAO();

        int id = dao.selectIdByCode(code);

        return id > 0;
    }

}
