package org.sanmarcux.controller.impl;

import org.sanmarcux.beans.Student;
import org.sanmarcux.controller.StudentController;
import org.sanmarcux.dao.DAO;
import org.sanmarcux.dao.StudentDAO;
import org.sanmarcux.factory.DAOFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cesardl
 */
@Service
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
            rowData[i][2] = dateFormatHelper.format(student.getBirthday());
            rowData[i][3] = student.getGender().getValue();
            rowData[i][4] = student.getAddress();
            rowData[i][5] = student.getPhone();
        }

        return rowData;
    }

    @Override
    public Student getByCode(String code) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        StudentDAO dao = factory.getStudentDAO();

        return dao.selectByCode(code);
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
