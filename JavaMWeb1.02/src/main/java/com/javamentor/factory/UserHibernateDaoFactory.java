package com.javamentor.factory;

import com.javamentor.dao.CrudDao;
import com.javamentor.dao.UserHibernateDAO;
import com.javamentor.dao.UsersDao;
import com.javamentor.models.User;

public class UserHibernateDaoFactory implements UserDaoFactory {


    @Override
    public UsersDao getDao() {
        return UserHibernateDAO.getInstance();
    }
}
