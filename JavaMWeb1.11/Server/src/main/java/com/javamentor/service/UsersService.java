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
import java.util.Optional;

@Service
public class UsersService {

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

    public User findOneByEmail(String email) {
        return usersRepository.findOneByEmail(email);
    }

    public boolean save(UserForm userForm) {
        String hashPassword = passwordEncoder.encode(userForm.getPassword());
        if (findOneByEmail(userForm.getEmail())==null) {
            User user = User.builder()
                    .firstName(userForm.getFirstName())
                    .lastName(userForm.getLastName())
                    .email(userForm.getEmail())
                    .password(hashPassword)
                    .role(userForm.getRole())
                    .state(userForm.getState())
                    .build();
            usersRepository.save(user);
            return true;
        } else return false;
    }

    public void save(User user) {
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
    public void getUpdate(UserForm userForm) {
        String hashPassword = passwordEncoder.encode(userForm.getPassword());
        User userUpdate = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .email(userForm.getEmail())
                .password(hashPassword)
                .role(userForm.getRole())
                .state(userForm.getState())
                .id(userForm.getId())
                .build();
        usersRepository.save(userUpdate);
    }

    public void delete(User user) {
        usersRepository.delete(user);
    }


}

