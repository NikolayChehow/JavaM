package com.javamentor.factory;

import com.javamentor.dao.UserHibernateDAO;
import com.javamentor.dao.UsersDao;
import com.javamentor.util.DBHelper;

public class UserHibernateDaoFactory implements UserDaoFactory {


    @Override
    public UsersDao getDao() {
        return new UserHibernateDAO(DBHelper.getSessionFactory().openSession());
    }
}
