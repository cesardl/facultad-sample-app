package factory;

import db.Database;
import dao.AlumnoDAO;
import dao.ProfesorDAO;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Cesardl
 *
 * @see
 * <a href='http://www.oracle.com/technetwork/java/dataaccessobject-138824.html'>Core
 * J2EE Patterns - Data Access Object</a>
 */
public abstract class DAOFactory {

    private static String driver;
    private static String url;
    private static String user;
    private static String passwd;

    // There will be a method for each DAO that can be 
    // created. The concrete factories will have to 
    // implement these methods.
    public abstract AlumnoDAO getAlumnoDAO();

    public abstract ProfesorDAO getProfesorDAO();

    static {
        try (InputStream is = DAOFactory.class.getResourceAsStream("/db/database.properties")) {
            Properties prop = new Properties();
            prop.load(is);

            driver = prop.getProperty("database.driver");
            url = prop.getProperty("database.url");
            user = prop.getProperty("database.user");
            passwd = prop.getProperty("database.passwd");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param whichFactory
     * @return
     */
    public static DAOFactory getDAOFactory(
            Database whichFactory) {

        switch (whichFactory) {
            case MYSQL:
                return new MysqlDAOFactory();

//            case ORACLE:
//                return new OracleDAOFactory();
//            case SYBASE:
//                return new SybaseDAOFactory();
            default:
                return null;
        }
    }

    // method to create Cloudscape connections
    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        try {
            // Use DRIVER and DBURL to create a connection
            // Recommend connection pool implementation/usage
            Class.forName(driver);
            return DriverManager.getConnection(url, user, passwd);
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }
}
