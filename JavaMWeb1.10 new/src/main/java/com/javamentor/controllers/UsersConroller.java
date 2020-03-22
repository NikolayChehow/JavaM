package com.javamentor.controllers;

import com.javamentor.forms.UserForm;
import com.javamentor.models.User;
import com.javamentor.service.UsersService;
import com.javamentor.transfer.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class UsersConroller {

    @Autowired
    private UsersService usersService;

    //    @GetMapping(value = "/admin/admin")
//    public ModelAndView getUsers(@RequestParam(required = false, name = "first_name") String firstName) {
//        List<User> users = null;
//        if (firstName != null) {
//            users = usersService.findAllByFirstName(firstName);
//        } else {
//            users = usersService.findAll();
//        }
//        return new ModelAndView("adminPanel").addObject("usersFromDB", users);
//    }
    @PutMapping("/admin/addUser")
    public ResponseEntity<Object>  addUser(@RequestBody UserForm userForm) {

        usersService.save(userForm);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/allUsers")
        public List<UserDto> getAllUsers(){
            return UserDto.from(usersService.findAll());

        }


    @PostMapping("/admin/updateUsers")
    public ResponseEntity<Object> setUpdate(@RequestBody UserForm uf) {

        usersService.getUpdate(uf);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/deleteUsers")
    public ResponseEntity<Object> deleteUsers(@RequestParam(required = false, name = "delete") String[] items) {
        List<User> users = usersService.findAll();
        if (users == null)
            users = new ArrayList<>();
        for (User user : users) {
            for (String str : items) {
                if (str.equals(user.getId().toString())) {
                    usersService.delete(user);
                }
            }
        }
        return ResponseEntity.ok().build();
    }


}
