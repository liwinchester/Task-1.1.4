package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS userProject" +
                    "(id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR (55), " +
                    "lastname VARCHAR (55)," +
                    "age INTEGER)");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            String sql = "DROP TABLE IF EXISTS userProject";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age)  {
        String sql = "INSERT INTO my_database.userproject (id, name, lastname, age) VALUES(NULL, ?, ?, ?)";
        try {
            Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");
            preparedStatement.close();
            connection.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM userProject WHERE " + id);
            preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM userProject ";
        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
            System.out.println(users);
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE userProject");
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
