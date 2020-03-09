package com.javamentor.controllers;

import com.javamentor.security.detail.UserDetailImpl;
import com.javamentor.transfer.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import static com.javamentor.transfer.UserDto.from;


@Controller
public class ProfileController {
    @GetMapping("/")
    public String getProfilePage(ModelMap model, Authentication authentication) {
        if (authentication==null){
            return "redirect:/login";
        }
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        UserDto user = from(userDetail.getUser());
        model.addAttribute("user", user);
        return "profile";
    }
}
