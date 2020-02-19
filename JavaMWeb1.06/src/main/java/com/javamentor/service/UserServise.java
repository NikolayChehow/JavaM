package com.javamentor.service;

import com.javamentor.forms.UserForm;
import com.javamentor.models.User;
import com.javamentor.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServise {

    @Autowired
    private UsersRepository usersRepository;

    public List<User> findAll() {
        return usersRepository.findAll();
    }
    public User findOne(Long id) {
        return usersRepository.findOne(id);
    }

    public List<User> findAllByFirstName(String firstName) {
        return usersRepository.findAllByFirstName(firstName);
    }

    public void save(User user) {

        usersRepository.save(user);
    }


    public void  getUpdate(UserForm userForm, Long id) {

        User user = usersRepository.findOne(id);
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setNameRole(userForm.getNameRole());
        usersRepository.save(user);

    }

    public void delete(Long id) {
        usersRepository.delete(id);
    }

}
