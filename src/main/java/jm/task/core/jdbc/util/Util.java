package jm.task.core.jdbc.util;

import antlr.PreservingFileWriter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static Connection dbConnection;
    public static Connection getDbConnection() throws ClassNotFoundException, SQLException, IOException {
        if (dbConnection == null || dbConnection.isClosed()) {
            String dbHost = "localhost";
            String dbPort = "3306";
            String dbName = "mydbtest";
            String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
            dbConnection = DriverManager.getConnection(connectionString, "root", "Zalupa_negra228");
        }
        return dbConnection;
    }
    public static Session getDbSession() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory.openSession();
    }
}
