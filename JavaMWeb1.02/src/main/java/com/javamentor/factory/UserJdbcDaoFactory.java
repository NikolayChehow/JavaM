package com.javamentor.factory;

import com.javamentor.dao.CrudDao;
import com.javamentor.dao.UserHibernateDAO;
import com.javamentor.dao.UsersDao;
import com.javamentor.dao.UsersDaoJdbcImpl;
import com.javamentor.models.User;

public class UserJdbcDaoFactory implements UserDaoFactory{

    @Override
    public UsersDao getDao() {
        return UsersDaoJdbcImpl.getInstance();
    }
}
