package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("Alisha", "Obelix", (byte) 22);
        userDaoHibernate.saveUser("Alisia", "Obion", (byte) 25);
        userDaoHibernate.saveUser("Alistar", "Oboz", (byte) 48);
        userDaoHibernate.saveUser("Alik", "Obern", (byte) 31);
        userDaoHibernate.getAllUsers();
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
    }
}
