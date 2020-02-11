package com.javamentor.service;

import com.javamentor.dao.CrudDao;
import com.javamentor.dao.UsersDao;
import com.javamentor.factory.ManagerDaoFactory;
import com.javamentor.models.User;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.SQLException;
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

    public User find(Integer id) {
        return getUsersDao().find(id);
    }

    public void save(User user) {
        getUsersDao().save(user);
    }

    public void deleteUsers(Integer id) {
        getUsersDao().delete(id);
    }

    public void updateUsers(User user) {
        getUsersDao().update(user);
    }



    public User validateClient(String email, String password)  {
        User user = getUsersDao().findByEmail(email);
        if (user != null && user.getEmail().equals(email) && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }


    private static UsersDao getUsersDao() {
        // return new UsersDaoJdbcImpl(DBHelper.connection());
        // return new UserHibernateDAO(DBHelper.getSessionFactory().openSession());
        return Objects.requireNonNull(ManagerDaoFactory.getDao()).getDao();
    }
}


