package controller.impl;

import beans.Teacher;
import controller.TeacherController;
import dao.DAO;
import dao.TeacherDAO;
import factory.DAOFactory;
import java.util.List;

/**
 *
 * @author Cesardl
 */
public class TeacherControllerImpl implements TeacherController {

    @Override
    public Object[][] getAll() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        TeacherDAO dao = factory.getTeacherDAO();

        List<Teacher> teachers = dao.selectAll();

        Object[][] rowData = new Object[teachers.size()][4];

        for (int i = 0; i < teachers.size(); i++) {
            Teacher teacher = teachers.get(i);

            rowData[i][0] = teacher.getCode();
            rowData[i][1] = teacher.getNames();
            rowData[i][2] = dateFormatHelper.formatDate(teacher.getBirthday());
            rowData[i][3] = teacher.getEmail();
        }
        return rowData;
    }

    @Override
    public Teacher getByCode(String code) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        TeacherDAO dao = factory.getTeacherDAO();

        Teacher teacher = dao.selectByCode(code);

        return teacher;
    }

    @Override
    public Teacher[] getNames() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        TeacherDAO dao = factory.getTeacherDAO();

        List<Teacher> teachers = dao.selectNames();

        return teachers.toArray(new Teacher[teachers.size()]);
    }

    @Override
    public boolean saveOrUpdate(Teacher entity) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        TeacherDAO dao = factory.getTeacherDAO();

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
        TeacherDAO dao = factory.getTeacherDAO();

        Teacher entity = new Teacher();
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
        TeacherDAO dao = factory.getTeacherDAO();

        int id = dao.selectIdByCode(code);

        return id > 0;
    }

}
