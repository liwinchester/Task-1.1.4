package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS user" +
                "(id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR (55), " +
                "lastname VARCHAR (55)," +
                "age INTEGER)";
        session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "DROP TABLE IF EXISTS user";
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user1 = new User();
        user1.setName(name);
        user1.setLastName(lastName);
        user1.setAge(age);
        session.save(user1);
        System.out.println("User с именем " + name + " добавлен в базу данных");
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(session.get(User.class, id));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> users =  session.createCriteria(User.class).list();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("TRUNCATE TABLE user").executeUpdate();
        session.getTransaction().commit();
        session.close();

    }
}
