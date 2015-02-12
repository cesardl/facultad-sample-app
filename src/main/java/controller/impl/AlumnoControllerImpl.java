package controller.impl;

import beans.Alumno;
import controller.AlumnoController;
import dao.AlumnoDAO;
import dao.DAO;
import factory.DAOFactory;
import java.util.List;
import util.Utils;

/**
 *
 * @author Cesardl
 */
public class AlumnoControllerImpl implements AlumnoController {

    @Override
    public Object[][] getAll() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        AlumnoDAO dao = factory.getAlumnoDAO();

        List<Alumno> alumnos = dao.selectAll();

        Object[][] rowData = new Object[alumnos.size()][6];

        for (int i = 0; i < alumnos.size(); i++) {
            Alumno alumno = alumnos.get(i);

            rowData[i][0] = alumno.getCodigo();
            rowData[i][1] = alumno.getNombre();
            rowData[i][2] = Utils.formatDate(alumno.getNacimiento());
            rowData[i][3] = alumno.getSexo().getValue();
            rowData[i][4] = alumno.getDireccion();
            rowData[i][5] = alumno.getTelefono();
        }

        return rowData;
    }

    @Override
    public Alumno getByCode(String code) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        AlumnoDAO dao = factory.getAlumnoDAO();

        Alumno alumno = dao.selectByCode(code);

        return alumno;
    }

    @Override
    public boolean saveOrUpdate(Alumno entity) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        AlumnoDAO dao = factory.getAlumnoDAO();

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
        AlumnoDAO dao = factory.getAlumnoDAO();

        Alumno entity = new Alumno();
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
        AlumnoDAO dao = factory.getAlumnoDAO();

        int id = dao.selectIdByCode(code);

        return id > 0;
    }

}
