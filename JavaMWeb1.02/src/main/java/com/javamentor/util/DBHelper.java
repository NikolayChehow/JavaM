package com.javamentor.util;

import com.javamentor.models.User;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    private static Connection connection;

    public static Connection connection() {
        if (connection == null) {
            connection = getConnection();
        }
        return connection;
    }

    @SuppressWarnings("UnusedDeclaration")
    private static Configuration getSqlConfiguration() {
        Properties properties = PropertyReader.getProperties(DBHelper.class.getClassLoader().getResourceAsStream("db.properties"));
        Configuration configuration = new Configuration();

        configuration.setProperty("hibernate.dialect", properties.getProperty("hibernate.dialect"));
        configuration.setProperty("hibernate.connection.driver_class", properties.getProperty("db.driverClassName"));
        configuration.setProperty("hibernate.connection.url", properties.getProperty("db.url"));
        configuration.setProperty("hibernate.connection.username", properties.getProperty("db.username"));
        configuration.setProperty("hibernate.connection.password", properties.getProperty("db.password"));
        configuration.setProperty("hibernate.show_sql", properties.getProperty("show_sql"));
        configuration.setProperty("hibernate.hbm2ddl.auto", properties.getProperty("hbm2ddl"));
        configuration.addAnnotatedClass(User.class);
        return configuration;
    }

    private static Connection getConnection() {

        Properties properties = PropertyReader.getProperties(DBHelper.class.getClassLoader().getResourceAsStream("db.properties"));
        Connection connection = null;
        String dbUrl = properties.getProperty("db.url");
        String dbUserName = properties.getProperty("db.username");
        String dbPassword = properties.getProperty("db.password");
        String driverClassName = properties.getProperty("db.driverClassName");
        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getSqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


}


