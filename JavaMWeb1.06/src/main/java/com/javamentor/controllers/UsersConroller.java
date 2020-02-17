package com.javamentor.controllers;

import com.javamentor.forms.UserForm;
import com.javamentor.models.User;
import com.javamentor.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsersConroller {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/user/home")
    public ModelAndView getHome(){
        return new ModelAndView("home");
    }

    @GetMapping("/admin/admin")
    public ModelAndView getUsers(@RequestParam(required = false, name = "first_name") String firstName) {
        List<User> users = null;
        if (firstName != null){
            users=usersRepository.findAllByFirstName(firstName);
        } else {
            users=usersRepository.findAll();
        }
        return new ModelAndView("adminPanel").addObject("usersFromDB", users);
    }

    @PostMapping("/admin/admin")
    public String addUsers(UserForm userForm){

            User newUser = User.from(userForm);
            usersRepository.save(newUser);
            return "redirect:/admin/admin";
        }


        Long id;
     @GetMapping("admin/updateUsers")
     public ModelAndView getUpdate(@RequestParam(required = false, name = "id") Long id){
         this.id=id;
         User user = usersRepository.findOne(id);
         List<User> users = new ArrayList<>();
         users.add(user);
         return new ModelAndView("updatePanel").addObject("usersFromDB", users);
     }
     @PostMapping("admin/updateUsers")
    public String setUpdate(UserForm uf){

//         User newUser = User.fromUpdate(userForm, id);
         usersRepository.setUserInfoById(uf.getFirstName(),uf.getLastName(),uf.getEmail(),uf.getPassword(),uf.getNameRole(), id);
         return "redirect:/admin/admin";
     }

    @PostMapping("admin/deleteUsers")
    public String deleteUsers(@RequestParam(required = false, name = "Delete") String[] items){
         List<User> users =usersRepository.findAll();
        if (users == null)
            users = new ArrayList<>();
        for (User user : users) {
            for (String str : items) {
                if (str.equals(user.getId().toString())) {
                    usersRepository.delete(user.getId());
                }
            }
        }
                return "redirect:/admin/admin";

    }



}
