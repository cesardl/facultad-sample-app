package org.sanmarcux.controller.impl;

import org.sanmarcux.beans.Teacher;
import org.sanmarcux.controller.TeacherController;
import org.sanmarcux.dao.TeacherDAO;
import org.sanmarcux.util.DateFormatHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author Cesardl
 */
@Controller
public class TeacherControllerImpl implements TeacherController {

    @Autowired
    private DateFormatHelper dateFormatHelper;

    @Autowired
    private TeacherDAO dao;

    @Override
    public Object[][] getAll() {
        List<Teacher> teachers = dao.selectAll();

        Object[][] rowData = new Object[teachers.size()][4];

        for (int i = 0; i < teachers.size(); i++) {
            Teacher teacher = teachers.get(i);

            rowData[i][0] = teacher.getCode();
            rowData[i][1] = teacher.getNames();
            rowData[i][2] = dateFormatHelper.format(teacher.getBirthday());
            rowData[i][3] = teacher.getEmail();
        }
        return rowData;
    }

    @Override
    public Teacher getByCode(String code) {
        return dao.selectByCode(code);
    }

    @Override
    public Teacher[] getNames() {
        List<Teacher> teachers = dao.selectNames();

        return teachers.toArray(new Teacher[teachers.size()]);
    }

    @Override
    public boolean saveOrUpdate(Teacher entity) {
        int state;

        if (entity.getId() == 0) {
            state = dao.insert(entity);
        } else {
            state = dao.update(entity);
        }

        return state != 0;
    }

    @Override
    public boolean delete(String code) {
        Teacher entity = new Teacher();
        entity.setCode(code);

        int state = dao.delete(entity);

        return state != 0;
    }

    @Override
    public boolean existsCode(String code) {
        try {
            return dao.selectIdByCode(code) != 0;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

}
