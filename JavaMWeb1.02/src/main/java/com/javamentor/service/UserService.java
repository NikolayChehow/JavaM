package com.javamentor.service;

import com.javamentor.dao.UserHibernateDAO;
import com.javamentor.dao.UsersDao;
import com.javamentor.dao.UsersDaoJdbcImpl;
import com.javamentor.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

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
//        UsersDao usersDao = new UsersDaoJdbcImpl(getConnection());
        UsersDao usersDao = new UserHibernateDAO(getSessionFactory().openSession());
        return usersDao;
    }

//    @SuppressWarnings("UnusedDeclaration")
    private static Configuration getSqlConfiguration()  {
        Properties properties = new Properties();

        Configuration configuration = new Configuration();
        try {
         //   properties.load(HibernateConfig.class.getClassLoader().getResourceAsStream("hibernate.properties"));
        properties.load(new FileInputStream("C:\\Users\\nk\\IdeaProjects\\JavaM\\JavaM\\JavaMWeb1.02\\target\\JavaMWeb1.02-0.1\\WEB-INF\\classes\\db.properties"));
        configuration.setProperty("hibernate.dialect", properties.getProperty("hibernate.dialect"));
        configuration.setProperty("hibernate.connection.driver_class", properties.getProperty("db.driverClassName"));
        configuration.setProperty("hibernate.connection.url", properties.getProperty("db.url"));
        configuration.setProperty("hibernate.connection.username", properties.getProperty("db.username"));
        configuration.setProperty("hibernate.connection.password", properties.getProperty("db.password"));
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.addAnnotatedClass(User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return configuration;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getSqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

}



