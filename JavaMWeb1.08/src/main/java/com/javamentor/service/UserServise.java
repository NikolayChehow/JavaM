package com.javamentor.service;

import com.javamentor.forms.UserForm;
import com.javamentor.models.Role;
import com.javamentor.models.State;
import com.javamentor.models.User;
import com.javamentor.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServise {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return usersRepository.findAll();
    }

    public User findOne(Long id) {
        return usersRepository.findUserById(id);
    }

    public List<User> findAllByFirstName(String firstName) {
        return usersRepository.findAllByFirstName(firstName);
    }

    public void save(UserForm userForm) {
        String hashPassword = passwordEncoder.encode(userForm.getPassword());
        User user = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .email(userForm.getEmail())
                .password(hashPassword)
                .role(userForm.getRole())
                .state(userForm.getState())
                .build();
        usersRepository.save(user);
    }


    //    public void  getUpdate(UserForm userForm, Long id) {
//
//        User user = usersRepository.findUserById(id);
//        user.setFirstName(userForm.getFirstName());
//        user.setLastName(userForm.getLastName());
//        user.setEmail(userForm.getEmail());
//        user.setPassword(userForm.getPassword());
//        user.setNameRole(userForm.getNameRole());
//        usersRepository.save(user);
//
//    }
    public void getUpdate(UserForm userForm, Long id) {
        String hashPassword = passwordEncoder.encode(userForm.getPassword());
        User userUpdate = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .email(userForm.getEmail())
                .password(hashPassword)
                .role(userForm.getRole())
                .state(userForm.getState())
                .id(id)
                .build();
        usersRepository.save(userUpdate);
    }

    public void delete(User user) {
        usersRepository.delete(user);
    }


}

