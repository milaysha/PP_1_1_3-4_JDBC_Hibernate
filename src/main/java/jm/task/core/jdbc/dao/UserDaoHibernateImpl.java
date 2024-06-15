package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;
import org.hibernate.query.NativeQuery;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getDbSession;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Session session = getDbSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        String sqlQuery =  "CREATE TABLE IF NOT EXISTS users" +
                "(id INT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(255), " +
                "last_name VARCHAR(255), " +
                "age INT NOT NULL, " +
                "PRIMARY KEY (id));";
        NativeQuery query = session.createSQLQuery(sqlQuery);
        query.executeUpdate();
        transaction.commit();
        session.close();
        System.out.println("Table created successfully."); //сделано
    }

    @Override
    public void dropUsersTable() {
        Session session = getDbSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        String sqlQuery =  "DROP TABLE IF EXISTS users";
        NativeQuery query = session.createSQLQuery(sqlQuery);
        query.executeUpdate();
        transaction.commit();
        session.close();
        System.out.println("Table created successfully."); //сделано
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = getDbSession().getSession();
        Transaction transaction = session.beginTransaction();
        User users = new User();
        users.setName(name);
        users.setLastName(lastName);
        users.setAge(age);
        session.save(users);

        transaction.commit();
        session.close();
        System.out.println("Table created successfully.");//сделано
    }


    @Override
    public void removeUserById(long id) {
        Session session = getDbSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        User user = session.get(User.class, id);
        session.delete(user);

        transaction.commit();
        session.close();//сделано
    }

    @Override
    public List<User> getAllUsers() {
        Session session = getDbSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Criteria criteria = session.createCriteria(User.class);
        List<User> users = criteria.list();
        transaction.commit();
        session.close();
        return users;//сделана

    }
    @Override
    public void cleanUsersTable() {
        Session session = getDbSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        String hql = "DELETE FROM User";
        Query query = session.createQuery(hql);
        query.executeUpdate();
        transaction.commit();
        session.close();
        System.out.println("Table cleaned successfully.");

    }
}

