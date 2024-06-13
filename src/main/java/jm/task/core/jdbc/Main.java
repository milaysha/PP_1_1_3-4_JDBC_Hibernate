package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        String name = "Name1";
        userService.saveUser(name, "LastName1", (byte) 20);
        System.out.println("user c именем -" + name);
        name = "Name2";
        userService.saveUser(name, "LastName2", (byte) 25);
        System.out.println("user c именем -" + name);
        name = "Name3";
        userService.saveUser(name, "LastName3", (byte) 31);
        System.out.println("user c именем -" + name);
        name = "Name4";
        userService.saveUser(name, "LastName4", (byte) 38);
        System.out.println("user c именем -" + name);
        List<User> us = userService.getAllUsers();
        for (User u : us) {
            System.out.println(u.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
