package controller;

import beans.Teacher;

/**
 *
 * @author Cesardl
 */
public interface TeacherController extends Controller<Teacher> {

    public Teacher[] getNames();

}
