package com.javamentor.factory;

import com.javamentor.dao.UserHibernateDAO;
import com.javamentor.dao.UsersDao;
import com.javamentor.dao.UsersDaoJdbcImpl;
import com.javamentor.util.DBHelper;
import com.javamentor.util.PropertyReader;

import java.util.Properties;

public class ManagerDaoFactory  {
    public static UserDaoFactory getDao(){

        Properties properties = PropertyReader.getProperties(DBHelper.class.getClassLoader().getResourceAsStream("db.properties"));
        String factory = properties.getProperty("factory");
        if (factory.equals("Hibernate")){
            return new UserHibernateDaoFactory();
        }
        else if (factory.equals("Jdbc")){
            return new UserJdbcDaoFactory();
        }
        return null;
    }
}
