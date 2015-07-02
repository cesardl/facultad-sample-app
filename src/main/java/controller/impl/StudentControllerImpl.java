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
        StudentDAO dao = factory.getStudentDAO();

        List<Student> students = dao.selectAll();

        Object[][] rowData = new Object[students.size()][6];

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);

            rowData[i][0] = student.getCode();
            rowData[i][1] = student.getNames();
            rowData[i][2] = Utils.formatDate(student.getBirthday());
            rowData[i][3] = student.getGender().getValue();
            rowData[i][4] = student.getDirection();
            rowData[i][5] = student.getTelefono();
        }

        return rowData;
    }

    @Override
    public Student getByCode(String code) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        StudentDAO dao = factory.getStudentDAO();

        Student student = dao.selectByCode(code);

        return student;
    }

    @Override
    public boolean saveOrUpdate(Student entity) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        StudentDAO dao = factory.getStudentDAO();

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
        StudentDAO dao = factory.getStudentDAO();

        Student entity = new Student();
        entity.setCode(code);

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
        StudentDAO dao = factory.getStudentDAO();

        int id = dao.selectIdByCode(code);

        return id != 0;
    }

}
