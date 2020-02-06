package com.javamentor.service;

import com.javamentor.dao.CrudDao;
import com.javamentor.dao.UsersDao;
import com.javamentor.factory.ManagerDaoFactory;
import com.javamentor.models.User;

import java.util.List;
import java.util.Objects;


public class UserService {
    private static UserService userService;

    private UserService() {
    }
    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
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


    private static UsersDao getUsersDao() {
      // return new UsersDaoJdbcImpl(DBHelper.connection());
      // return new UserHibernateDAO(DBHelper.getSessionFactory().openSession());
        return  Objects.requireNonNull(ManagerDaoFactory.getDao()).getDao();
    }
}


