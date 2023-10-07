package com.example.logindb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/home")
    public String userHome() {
        return "User/UserHomePage";
    }

}
