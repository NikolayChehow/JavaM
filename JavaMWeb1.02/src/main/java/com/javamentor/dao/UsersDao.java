package com.javamentor.dao;

import com.javamentor.models.User;

import java.util.List;

public interface UsersDao extends CrudDao<User> {
    User findByFirstName(String firstName);
}
