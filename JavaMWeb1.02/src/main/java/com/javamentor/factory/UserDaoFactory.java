package com.javamentor.factory;

import com.javamentor.dao.CrudDao;
import com.javamentor.dao.UsersDao;
import com.javamentor.models.User;

public interface UserDaoFactory {
   UsersDao getDao();


}
