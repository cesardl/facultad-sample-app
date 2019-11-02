package org.sanmarcux.dao;

import org.sanmarcux.beans.Teacher;

import java.util.List;

/**
 * @author Cesardl
 */
public interface TeacherDAO extends DAO<Teacher> {

    /**
     * Obtain a list of teacher's name
     *
     * @return the list
     */
    List<Teacher> selectNames();

    int findAssignedStudents(String code);
}
