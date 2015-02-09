package controller.impl;

import beans.Profesor;
import controller.ProfesorController;
import dao.DAO;
import dao.ProfesorDAO;
import factory.DAOFactory;
import java.util.List;
import util.Utils;

/**
 *
 * @author Cesardl
 */
public class ProfesorControllerImpl implements ProfesorController {

    @Override
    public Object[][] getAll() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProfesorDAO dao = factory.getProfesorDAO();

        List<Profesor> profesores = dao.selectAll();

        Object[][] rowData = new Object[profesores.size()][4];

        for (int i = 0; i < profesores.size(); i++) {
            Profesor profesor = profesores.get(i);

            rowData[i][0] = profesor.getCodigo();
            rowData[i][1] = profesor.getNombre();
            rowData[i][2] = Utils.formatDate(profesor.getNacimiento());
            rowData[i][3] = profesor.getEmail();
        }
        return rowData;
    }

    @Override
    public Profesor getByCode(String code) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProfesorDAO dao = factory.getProfesorDAO();

        Profesor profesor = dao.selectByCode(code);

        return profesor;
    }

    @Override
    public Profesor[] getNames() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProfesorDAO dao = factory.getProfesorDAO();

        List<Profesor> profesores = dao.selectNames();

        return profesores.toArray(new Profesor[profesores.size()]);
    }

    @Override
    public boolean saveOrUpdate(Profesor entity) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProfesorDAO dao = factory.getProfesorDAO();

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
        ProfesorDAO dao = factory.getProfesorDAO();

        Profesor entity = new Profesor();
        entity.setCodigo(code);

        int state = dao.delete(entity);

        switch (state) {
            case DAO.STATE_ERROR:
                return false;

            default:
                return true;
        }
    }

}
