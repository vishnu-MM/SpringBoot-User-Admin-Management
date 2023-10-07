package com.example.logindb.controller;

import com.example.logindb.dto.UserDTO;
import com.example.logindb.sevices.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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

    @InitBinder
    public void removeWhiteSpaces(WebDataBinder dataBinder){
        StringTrimmerEditor st = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, st);
    }

    @GetMapping("/")
     public String home(Authentication authentication) {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities ) {
            if (grantedAuthority.getAuthority().equals("USER")) {
                return "redirect:/home";
            }
            else {
                return "redirect:/dashboard";
            }
        }
         return "redirect:/login";
     }

     @GetMapping("/login")
     public String showLoginPage( Authentication authentication ) {

//        check whether a user is already authenticated or not
         if (authentication != null) {
             return "redirect:/";
         }
         return "LoginPage";
     }

     @GetMapping("/register")
     public String showRegistrationForm(Model model) {

         UserDTO user = new UserDTO();
         model.addAttribute("newUser",user);
         return "User/RegistrationPage";
     }

    @PostMapping("/register/save")
    public String userRegistration(@Valid @ModelAttribute("newUser") UserDTO userDTO, BindingResult result, Model model) {

        if ( userService.isExists(userDTO.getUsername()) ) {
            result.rejectValue("username", null, "User with this email is already exist");
        }

        if ( result.getFieldError("password") != null ) {
            result.rejectValue("password", "password.error", "Try again!!");
        }

        if ( result.getFieldError("username") != null ) {
            result.rejectValue("username","username.error" , "Try again!!");
        }

        if (result.getFieldError("name") != null ) {
            result.rejectValue("name","name.error","Try again!!");

        }

        if (result.hasErrors()) {
            return "User/RegistrationPage";
        }
        userService.saveUser(userDTO);
        return "redirect:/register?success";
    }

    @GetMapping("/access-denied")
    public String handleAccessDeniedException(){
        return "redirect:/home";
    }
}
