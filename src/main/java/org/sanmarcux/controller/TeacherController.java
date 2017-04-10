package org.sanmarcux.controller;

import org.sanmarcux.beans.Teacher;

/**
 * @author Cesardl
 */
public interface TeacherController extends Controller<Teacher> {

    Teacher[] getNames();

}
