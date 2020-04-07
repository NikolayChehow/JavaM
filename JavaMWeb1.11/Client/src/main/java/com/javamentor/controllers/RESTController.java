package com.javamentor.controllers;

import com.javamentor.forms.UserForm;
import com.javamentor.models.User;
import com.javamentor.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
public class RESTController {

    @Autowired
    RestTemplateService restTemplateService;

    @GetMapping(value = "/admin/allUsers")
    public ResponseEntity<String> getUsers() {

        return ResponseEntity.ok(restTemplateService.findAllUsers());
    }

    @PostMapping(value = "/admin/deleteUsers/")
    public ResponseEntity<Object> delete(@RequestParam(required = false, name = "delete") String[] items) {
        Integer result = restTemplateService.deleteUser(items);
        if (result == 200) {
            return ResponseEntity.ok().build();
        } else return ResponseEntity.notFound().build();
    }

//    @PutMapping(value = "/admin/addUser")
//    public ResponseEntity<Object> addUser(@RequestBody UserForm userForm) {
//        Integer result = restTemplateService.addUser(userForm);
//        if (result == 200) {
//            return ResponseEntity.ok().build();
//        } else return ResponseEntity.notFound().build();
//    }
    @PutMapping(value = "/admin/addUser")
    public ResponseEntity<Object> addUser(@RequestBody UserForm userForm) {
        try {
            Integer result = restTemplateService.addUser(userForm);
//            if (result == 200) {
                return ResponseEntity.ok().build();
//            } else return ResponseEntity.notFound().build();
        } catch (Exception e) {

            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping(value = "/admin/updateUsers")
    public ResponseEntity<Object> updateUsers(@RequestBody UserForm userForm) {
        Integer result = restTemplateService.updateUsers(userForm);
        if (result == 200) {
            return ResponseEntity.ok().build();
        } else return ResponseEntity.notFound().build();
    }

//    @GetMapping(value = "/admin/getUser")
//    public User getUserByEmail(String email) {
//        return restTemplateService.findOneByEmail(email);
//    }
}
