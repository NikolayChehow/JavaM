package com.javamentor.dao;

import com.javamentor.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoJdbcImpl implements UsersDao {

    public UsersDaoJdbcImpl(Connection connection) {
        this.connection = connection;

    }

    //language=SQL
    private final String SQL_SELECT_ALL =
            "SELECT * FROM Users";

    //language=SQL
    private final String SQL_SELECT_BY_ID = "SELECT * FROM Users WHERE id=?";


    private Connection connection;


    @Override

    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User find(Integer id) {
        try {

            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String password = resultSet.getString("password");
                return  new User(id, firstName, lastName, email, password);

            }
            return null;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (first_name, last_name, email, password) VALUES (?,?,?,?)");
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(1, user.getLastName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public void update(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET first_name =? last_name =? email=?  password=? WHERE id=?");
            statement.setString(1, user.getFirstName());
            statement.setString(1, user.getLastName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getId());
            statement.execute();
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }


    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id =?");
            statement.setInt(1, id);
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
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String password = resultSet.getString("password");
                User user = new User(id, firstName, lastName, email, password);
                users.add(user);
//                System.out.println(user);
            }
            return users;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
