package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.util.Util;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {

        try (Connection con = Util.getDbConnection();
             Statement stmt = con.createStatement()) {
            String sql = "CREATE TABLE users " +
                    "(id INT NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(255), " +
                    "last_name VARCHAR(255), " +
                    "age INT NOT NULL, " +
                    "PRIMARY KEY (id))";

            stmt.executeUpdate(sql);
            System.out.println("Table created successfully.");
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection con = Util.getDbConnection();
             Statement stmt = con.createStatement()) {

            String sql = "DROP TABLE IF EXISTS users";
            stmt.executeUpdate(sql);
            System.out.println("Table created successfully.");
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insertSql = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)";
        try (Connection con = Util.getDbConnection();
             PreparedStatement stmt = con.prepareStatement(insertSql)) {

            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setInt(3, age);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Пользователь успешно сохранен в базе данных.");
            } else {
                System.out.println("Не удалось сохранить пользователя.");
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при сохранении пользователя: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection con = Util.getDbConnection();
             Statement stmt1 = con.createStatement()) {
            String deleteQuery = "DELETE FROM users;";
            stmt1.executeUpdate(deleteQuery);
            System.out.println("Все записи успешно удалены из таблицы 'users'");
        } catch (SQLException e) {
            System.out.println("Ошибка при работе с PostgreSQL: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public List<User> getAllUsers() {
        try (
                Connection con = Util.getDbConnection();
                Statement stmt2 = con.createStatement()) {

            // SQL запрос для выбора всех пользователей из таблицы "users"
            String selectQuery = "SELECT name, last_name, age  FROM users;";

            // Выполнение SQL запроса
            ResultSet resultSet = stmt2.executeQuery(selectQuery);
            List<User> us = new ArrayList<>();
            // Обработка результатов запроса
            while (resultSet.next()) {

                String userName = resultSet.getString("name");
                String userLastName = resultSet.getString("last_name");
                byte age = resultSet.getByte("age");
               System.out.println("Name: " + userName + ", Last Name: " + userLastName + ", Age: " + age);
               User user = new User(userName, userLastName, age);
               us.add(user);
            }
          return us;
        } catch (SQLException e) {
            System.out.println("Ошибка при работе с PostgreSQL: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void cleanUsersTable() {
        try (Connection con = Util.getDbConnection();
             Statement stmt3 = con.createStatement()) {
            // SQL запросы для очистки данных из таблиц
            String tableName = "users";
            String sql = "TRUNCATE TABLE users";

            stmt3.executeUpdate(sql);
            System.out.println("Таблица " + tableName + " успешно очищена.");

        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }}

