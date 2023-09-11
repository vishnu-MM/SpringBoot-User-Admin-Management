package com.example.logindb.controller;


import com.example.logindb.dto.UserDTO;
import com.example.logindb.entity.User;
import com.example.logindb.sevices.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@Controller
public class LoginController {

    private UserService userService;
    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
     public String home(Authentication authentication) {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities ) {
            if (grantedAuthority.getAuthority().equals("USER")) {
                return "redirect:/home";
            }
            else {
                System.out.println(grantedAuthority.getAuthority());
            }
        }

         return "UserHomePage"; // have to change
     }



     @GetMapping("/login")
     public String showLoginPage( Authentication authentication ) {

         if (authentication != null) {
             return "redirect:/";
         }
         return "LoginPage";
     }

     @GetMapping("/register")
     public String showRegistrationForm(Model model) {
         UserDTO user = new UserDTO();
         model.addAttribute("newUser",user);
         return "RegistrationPage";
     }

     @PostMapping("/register/save")
     public String userRegistration(@Valid @ModelAttribute("newUser") UserDTO userDTO , Model model , BindingResult result)  {

         User existing = userService.findByUsername(userDTO.getUsername());
         if (existing != null) {
             result.rejectValue("username",null,"User with this email is already exist");
         }
         if (result.hasErrors() ) {
            model.addAttribute("newUser", userDTO);
            return "RegistrationPage";
         }
         userService.saveUser(userDTO);
         return "redirect:/register?success";
     }

}
