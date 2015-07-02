package factory;

import dao.StudentDAO;
import dao.TeacherDAO;
import db.Database;
import factory.impl.MysqlDAOFactory;
import factory.impl.OracleDAOFactory;
import factory.impl.SqlServersDAOFactory;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author Cesardl
 *
 * @see
 * <a href='http://www.oracle.com/technetwork/java/dataaccessobject-138824.html'>Core
 * J2EE Patterns - Data Access Object</a>
 */
public abstract class DAOFactory {

    private static final Logger log = Logger.getLogger(DAOFactory.class);

    private static Database whichFactory;
//    private static String driver;
    private static String url;
    private static String user;
    private static String passwd;

    // There will be a method for each DAO that can be 
    // created. The concrete factories will have to 
    // implement these methods.
    public abstract StudentDAO getStudentDAO();

    public abstract TeacherDAO getTeacherDAO();

    static {
        System.out.println("LOAD RESOURCES");
        try (InputStream is = DAOFactory.class.getResourceAsStream("/db/database.properties")) {
            Properties prop = new Properties();
            prop.load(is);

            whichFactory = Database.valueOf(prop.getProperty("database.factory"));
//            driver = prop.getProperty("database.driver");
            url = prop.getProperty("database.url");
            user = prop.getProperty("database.user");
            passwd = prop.getProperty("database.passwd");
        } catch (IOException e) {
            log.error("Error while properties resources were loaded.", e);
        }
    }

    /**
     *
     * @return
     */
    public static DAOFactory getDAOFactory() {
        switch (whichFactory) {
            case MYSQL:
                return new MysqlDAOFactory();

            case ORACLE:
                return new OracleDAOFactory();

            case SQLSERVER:
                return new SqlServersDAOFactory();

            default:
                return null;
        }
    }

    /**
     * Method to create db connections.
     *
     * @return
     * @throws SQLException
     */
    public static Connection createConnection() throws SQLException {
        // Recommend connection pool implementation/usage
        return DriverManager.getConnection(url, user, passwd);
    }
}
