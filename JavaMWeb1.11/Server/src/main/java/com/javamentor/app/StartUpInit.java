package com.javamentor.app;

import com.javamentor.models.Role;
import com.javamentor.models.State;
import com.javamentor.models.User;
import com.javamentor.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;

@Component
public class StartUpInit {


    @Autowired
    private UsersService usersService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {


        if (usersService.findAll().size() == 0) {
            usersService.save(User.builder().email("admin@ru").firstName("a").lastName("a").password(passwordEncoder.encode("123")).role(Role.ADMIN).state(State.ACTIVE).build());
        }
    }
}
