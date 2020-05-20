package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {


    public static Connection getConnection(String dbName)
            throws SQLException, ClassNotFoundException {
        //Registering the HSQLDB JDBC driver
        Class.forName("org.hsqldb.jdbc.JDBCDriver");

        /* Database files will be created in the "database"
         * folder in the project. If no username or password is
         * specified, the default SA user and an empty password are used */
        Connection con = DriverManager.getConnection
                ("jdbc:hsqldb:file:database/" + dbName, "SA", "");
        return con;
    }
}
