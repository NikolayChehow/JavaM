package com.javamentor.service;

import com.javamentor.dao.UsersDao;
import com.javamentor.dao.UsersDaoJdbcImpl;
import com.javamentor.models.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;


public class UserService {


    public UserService() {
    }

    public List<User> findAll() {
        return getUsersDao().findAll();
    }

    public void save(User user) {
        getUsersDao().save(user);
    }

    public void deleteUsers(String firstName) {
        getUsersDao().delete(firstName);
    }
    public void updateUsers(User user) {
        getUsersDao().update(user);
    }



    private static Connection getConnection() {

        Properties properties = new Properties();
        Connection connection;
        try {
            properties.load(new FileInputStream("C:\\Users\\nk\\IdeaProjects\\JavaM\\JavaM\\JavaMWeb1.01\\target\\JavaMWeb1.01-0.1\\WEB-INF\\classes\\db.properties"));
            String dbUrl = properties.getProperty("db.url");
            String dbUserName = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String driverClassName = properties.getProperty("db.driverClassName");

            Class.forName(driverClassName);
            connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        return connection;
    }

    private static UsersDao getUsersDao() {
        UsersDao usersDao = new UsersDaoJdbcImpl(getConnection());
        return usersDao;
    }

}



