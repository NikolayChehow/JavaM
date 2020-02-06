package com.javamentor.dao;

import com.javamentor.models.User;
import com.javamentor.util.DBHelper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersDaoJdbcImpl implements UsersDao {

    private static UsersDaoJdbcImpl userDao;

    public static UsersDaoJdbcImpl getInstance() {
        if (userDao == null) {
            userDao = new UsersDaoJdbcImpl(DBHelper.connection());
        }
        return userDao;
    }

    private UsersDaoJdbcImpl(Connection connection) {
        this.connection = connection;

    }

    //language=SQL
    private final String SQL_SELECT_ALL =
            "SELECT * FROM Users";

    //language=SQL
    private final String SQL_SELECT_BY_ID = "SELECT * FROM Users WHERE id=?";


    private Connection connection;


    @Override

    public User findByFirstName(String firstName) {
        return null;
    }

    @Override
    public User find(Integer id) {
        try {

            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                return new User(id, firstName, lastName);
            }
            return null;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (first_name, last_name) VALUES (?,?)");
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public void update(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET last_name=? WHERE first_name =?");
            statement.setString(1, user.getLastName());
            statement.setString(2, user.getFirstName());
            statement.execute();
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }


    @Override
    public void delete(String firstName) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE first_name =?");
            statement.setString(1, firstName);
            statement.execute();
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try {
            List<User> users = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                User user = new User(id, firstName, lastName);
                users.add(user);
//                System.out.println(user);
            }
            return users;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
