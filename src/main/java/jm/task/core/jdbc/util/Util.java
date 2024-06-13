package jm.task.core.jdbc.util;

import antlr.PreservingFileWriter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static Connection getDbConnection() throws ClassNotFoundException, SQLException, IOException {

        String dbHost = "localhost";
        String dbPort = "3306";
        String dbName = "mydbtest";
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Connection conn = DriverManager.getConnection(connectionString, "root", "Zalupa_negra228");
        return conn;
    }
}
