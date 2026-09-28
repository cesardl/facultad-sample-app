package org.sanmarcux.controller.impl;

import org.sanmarcux.beans.Teacher;
import org.sanmarcux.controller.TeacherController;
import org.sanmarcux.dao.TeacherDAO;
import org.sanmarcux.util.DateFormatHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author Cesardl
 */
@Controller
public class TeacherControllerImpl implements TeacherController {

    private static final Logger LOG = LoggerFactory.getLogger(TeacherControllerImpl.class);

    @Autowired
    private DateFormatHelper dateFormatHelper;

    @Autowired
    private TeacherDAO dao;

    @Override
    public Object[][] getAll() {
        List<Teacher> teachers = dao.selectAll();

        Object[][] rowData = new Object[teachers.size()][5];

        for (int i = 0; i < teachers.size(); i++) {
            Teacher teacher = teachers.get(i);

            rowData[i][0] = teacher.getCode();
            rowData[i][1] = teacher.getNames();
            rowData[i][2] = dateFormatHelper.format(teacher.getBirthday());
            rowData[i][3] = teacher.getEmail();
            rowData[i][4] = teacher.getAssignedStudents();
        }
        return rowData;
    }

    @Override
    public Teacher getByCode(final String code) {
        return dao.selectByCode(code);
    }

    @Override
    public Teacher[] getNames() {
        List<Teacher> teachers = dao.selectNames();

        return teachers.toArray(new Teacher[0]);
    }

    @Override
    public boolean saveOrUpdate(final Teacher entity) {
        int state;

        try {
            if (entity.getId() == 0) {
                state = dao.insert(entity);
            } else {
                state = dao.update(entity);
            }
        } catch (DataIntegrityViolationException e) {
            // e.g. another insert took the same code between existsCode() and here
            LOG.warn("Save rejected by a database constraint for code [{}]", entity.getCode(), e);
            return false;
        }

        return state != 0;
    }

    @Override
    public boolean delete(final String code) {
        int result = dao.findAssignedStudents(code);
        if (result == 0) {
            Teacher entity = new Teacher();
            entity.setCode(code);

            int state = dao.delete(entity);

            return state != 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean existsCode(final String code) {
        return dao.selectIdByCode(code) != 0;
    }

}
