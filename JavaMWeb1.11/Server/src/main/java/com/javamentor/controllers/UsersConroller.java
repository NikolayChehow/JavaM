package com.javamentor.controllers;

import com.javamentor.forms.UserForm;
import com.javamentor.models.User;
import com.javamentor.service.UsersService;
import com.javamentor.transfer.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
@Transactional
public class UsersConroller {

    @Autowired
    private UsersService usersService;

    @PutMapping("/addUser")
    public ResponseEntity<Object> addUser(@RequestBody UserForm userForm) {

        usersService.save(userForm);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/allUsers")
    public List<UserDto> getAllUsers() {

        return UserDto.from(usersService.findAll());

    }

    @GetMapping("/getUser/{email}")
    public User getUserByEmail(@PathVariable String email) {

        return usersService.findOneByEmail(email);

    }


    @PostMapping("/updateUsers")
    public ResponseEntity<Object> setUpdate(@RequestBody UserForm uf) {

        usersService.getUpdate(uf);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deleteUsers/")
    public ResponseEntity<Integer> deleteUsers(@RequestBody String[] items) {
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
