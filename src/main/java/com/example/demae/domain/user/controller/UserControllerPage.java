package com.example.demae.domain.user.controller;

import com.example.demae.domain.user.entity.User;
import com.example.demae.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserControllerPage {

    private final UserService userService;
    
    @GetMapping("/join")
    public String signUpPage(){
        return "user/signUp";
    }

    @GetMapping("/login")
    public String loginFormPage(){
        return "user/login";
    }

    @GetMapping("/main")
    public String mainPage(@AuthenticationPrincipal UserDetails userDetails,
                           Model model) {
        if (userDetails == null) {
            return "root/main";
        }

        User user = userService.findUser(userDetails.getUsername());

        if (user.getUserRole().toString().equals("ADMIN") && user.getStore() != null) {
            model.addAttribute("storeId", user.getStore().getStoreId());
        }
        model.addAttribute("UserRole", user.getUserRole().toString());

        return "root/main";
    }
}
