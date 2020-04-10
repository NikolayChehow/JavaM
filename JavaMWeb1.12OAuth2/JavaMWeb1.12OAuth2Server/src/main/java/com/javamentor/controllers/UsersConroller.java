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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
@Transactional
public class UsersConroller {

    @Autowired
    private UsersService usersService;

    @PutMapping("/addUser")
    public ResponseEntity<Object> addUser(@RequestBody UserForm userForm) {
        boolean result = usersService.save(userForm);
        if (result) {
            return ResponseEntity.ok().build();
        } else return ResponseEntity.notFound().build();
    }
//    @GetMapping("/allUsers")
//    public List<UserDto> getAllUsers() {
//        return UserDto.from(usersService.findAll());
//    }

    @PutMapping("/addGoogleUser")
    public ResponseEntity<Object> addGoogleUser(@RequestBody User user) {

        try {
            usersService.save(user);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> list = usersService.findAll();
        if (list!=null) {
            return ResponseEntity.ok(UserDto.from(list));
        } else return ResponseEntity.notFound().build();
    }


//    @GetMapping("/getUser/{email}")
//    public User getUserByEmail(@PathVariable String email) {
//        return usersService.findOneByEmail(email);
//    }

    @GetMapping("/getUser/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = usersService.findOneByEmail(email);
        if (user != null) {
            return ResponseEntity.ok((user));
            //        return new ResponseEntity<>(usersService.findOneByEmail(email), HttpStatus.BAD_REQUEST);
        } else return ResponseEntity.notFound().build();
    }

    @GetMapping("/getGoogleUser/{email}")
    public ResponseEntity<User> getGoodleUserByEmail(@PathVariable String email) {
        User user = usersService.findOneByGoogleEmail(email);
        if (user != null) {
            return ResponseEntity.ok((user));
            //        return new ResponseEntity<>(usersService.findOneByEmail(email), HttpStatus.BAD_REQUEST);
        } else return ResponseEntity.notFound().build();
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
