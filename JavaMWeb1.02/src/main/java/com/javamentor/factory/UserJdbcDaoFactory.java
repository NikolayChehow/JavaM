package com.javamentor.factory;

import com.javamentor.dao.UsersDao;
import com.javamentor.dao.UsersDaoJdbcImpl;
import com.javamentor.util.DBHelper;

public class UserJdbcDaoFactory implements UserDaoFactory {

    @Override
    public UsersDao getDao() {
        return new UsersDaoJdbcImpl(DBHelper.connection());
    }
}
