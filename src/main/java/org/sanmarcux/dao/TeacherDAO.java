package org.sanmarcux.dao;

import org.sanmarcux.beans.Teacher;
import java.util.List;

/**
 *
 * @author Cesardl
 */
public interface TeacherDAO extends DAO<Teacher> {

    List<Teacher> selectNames();

}
