package com.javamentor.controllers;

import com.javamentor.forms.UserForm;
import com.javamentor.models.User;
import com.javamentor.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
public class RESTController {

    @Autowired
    RestTemplateService restTemplateService;

    @GetMapping(value = "/admin/allUsers")
    public String getUsers() {
        return restTemplateService.findAllUsers();
    }

    @PostMapping(value = "/admin/deleteUsers/")
    public Integer delete(@RequestParam(required = false, name = "delete") String[] items)  {
      return   restTemplateService.deleteUser(items);
    }

    @PutMapping(value = "/admin/addUser")
    public Integer addUser(@RequestBody UserForm userForm)  {
        return restTemplateService.addUser(userForm);
    }

    @PostMapping(value = "/admin/updateUsers")
    public int updateUsers(@RequestBody UserForm userForm)  {
        return restTemplateService.updateUsers(userForm);
    }

//    @GetMapping(value = "/admin/getUser")
//    public User getUserByEmail(String email) {
//        return restTemplateService.findOneByEmail(email);
//    }
}
