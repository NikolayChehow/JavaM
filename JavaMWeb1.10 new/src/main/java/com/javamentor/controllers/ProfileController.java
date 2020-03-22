package com.javamentor.controllers;

import com.javamentor.models.User;
import com.javamentor.security.detail.UserDetailImpl;
import com.javamentor.transfer.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.javamentor.transfer.UserDto.from;


@Controller
public class ProfileController {
    @GetMapping({"/", "/hello"})
    public String getProfilePage(ModelMap model, Authentication authentication) {
        if (authentication==null){
            return "redirect:/login";
        }
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        UserDto user = from(userDetail.getUser());
        model.addAttribute("user", user);
        return "profile";
    }

        @GetMapping("/admin/admin")
    public String getAdminPage(ModelMap model, Authentication authentication) {
            if (authentication == null) {
                return "redirect:/login";
            }
            UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
            UserDto user = from(userDetail.getUser());
            model.addAttribute("admin", user);
            return "adminPanel";
        }

    @GetMapping("/login")
    public String getLoginPage(Authentication authentication, ModelMap model , HttpServletRequest request){
        if(authentication!=null){
            return "redirect:/hello";
        }
        if(request.getParameterMap().containsKey("error")){
            model.addAttribute("error" , true);
            return "login";

        }
        return "login";
    }
}

