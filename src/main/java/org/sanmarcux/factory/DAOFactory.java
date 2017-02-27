package org.sanmarcux.factory;

import org.sanmarcux.dao.StudentDAO;
import org.sanmarcux.dao.TeacherDAO;
import org.sanmarcux.db.Database;
import org.sanmarcux.factory.impl.MysqlDAOFactory;
import org.sanmarcux.factory.impl.OracleDAOFactory;
import org.sanmarcux.factory.impl.PostgresqlDAOFactory;
import org.sanmarcux.factory.impl.SqlServerDAOFactory;
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
    private static String password;

    // There will be a method for each DAO that can be 
    // created. The concrete factories will have to 
    // implement these methods.
    public abstract StudentDAO getStudentDAO();

    public abstract TeacherDAO getTeacherDAO();

    static {
        try (InputStream is = DAOFactory.class.getResourceAsStream("/db/database.properties")) {
            Properties prop = new Properties();
            prop.load(is);

            whichFactory = Database.valueOf(prop.getProperty("database.factory"));
//            driver = prop.getProperty("database.driver");
            url = prop.getProperty("database.url");
            user = prop.getProperty("database.user");
            password = prop.getProperty("database.password");
        } catch (IOException e) {
            log.error("Error while properties resources were loaded.", e);
        }
    }

    /**
     *
     * @return the factory for current database.
     */
    public static DAOFactory getDAOFactory() {
        switch (whichFactory) {
            case MYSQL:
                return new MysqlDAOFactory();

            case ORACLE:
                return new OracleDAOFactory();

            case SQL_SERVER:
                return new SqlServerDAOFactory();

            case POSTGRESQL:
                return new PostgresqlDAOFactory();

            default:
                return null;
        }
    }

    /**
     * Method to create db connections.
     *
     * @return the database connection.
     * @throws SQLException
     */
    public static Connection createConnection() throws SQLException {
        // TODO Recommend connection pool implementation/usage
        return DriverManager.getConnection(url, user, password);
    }
}
