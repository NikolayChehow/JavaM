//package com.javamentor.app;
//
//import com.javamentor.models.Role;
//import com.javamentor.models.State;
//import com.javamentor.models.User;
//import com.javamentor.service.UsersService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//
//
//import javax.annotation.PostConstruct;
//
//@Component
//public class StartUpInit {
//
//
//    @Autowired
//    private UsersService usersService;
//
//    @PostConstruct
//    public void init() {
//
//
//        if (usersService.findAll().size() == 0) {
//            usersService.save(User.builder().email("admin@admin").firstName("a").lastName("a").password("admin").role(Role.ADMIN).state(State.ACTIVE).build());
//        }
//    }
//}
