package controller.impl;

import beans.Teacher;
import controller.TeacherController;
import dao.StudentDAO;
import dao.DAO;
import dao.TeacherDAO;
import factory.DAOFactory;
import java.util.List;
import util.Utils;

/**
 *
 * @author Cesardl
 */
public class TeacherControllerImpl implements TeacherController {

    @Override
    public Object[][] getAll() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        TeacherDAO dao = factory.getProfesorDAO();

        List<Teacher> profesores = dao.selectAll();

        Object[][] rowData = new Object[profesores.size()][4];

        for (int i = 0; i < profesores.size(); i++) {
            Teacher profesor = profesores.get(i);

            rowData[i][0] = profesor.getCodigo();
            rowData[i][1] = profesor.getNombre();
            rowData[i][2] = Utils.formatDate(profesor.getNacimiento());
            rowData[i][3] = profesor.getEmail();
        }
        return rowData;
    }

    @Override
    public Teacher getByCode(String code) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        TeacherDAO dao = factory.getProfesorDAO();

        Teacher profesor = dao.selectByCode(code);

        return profesor;
    }

    @Override
    public Teacher[] getNames() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        TeacherDAO dao = factory.getProfesorDAO();

        List<Teacher> profesores = dao.selectNames();

        return profesores.toArray(new Teacher[profesores.size()]);
    }

    @Override
    public boolean saveOrUpdate(Teacher entity) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        TeacherDAO dao = factory.getProfesorDAO();

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
        TeacherDAO dao = factory.getProfesorDAO();

        Teacher entity = new Teacher();
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
