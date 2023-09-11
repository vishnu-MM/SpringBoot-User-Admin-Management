package com.example.logindb.controller;

import com.example.logindb.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/home")
    public String userHome() {
        return "UserHomePage";
    }

//     @GetMapping("/home")
//    public String userHome(Authentication authentication , Model model) {
//        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//        String username = userDetails.getUsername();
//        model.addAttribute("email",username);
//        model.addAttribute("name",userDetails.getName());
//        model.addAttribute("email",userDetails.getUsername());
//        return "UserHomePage";
//    }
}
