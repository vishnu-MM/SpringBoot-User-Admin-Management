package com.example.logindb.controller;

import com.example.logindb.dto.UserDTO;
import com.example.logindb.entity.User;
import com.example.logindb.sevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class AdminController {

    private UserService userService;
    @Autowired
    public AdminController(UserService userService ) {
        this.userService = userService;

    }

    @GetMapping("/dashboard")
    public String adminHome(Principal principal,Model model) {

        List<UserDTO> users = userService.findAllByRole("USER");
        User user =userService.findByUsername(principal.getName());

        model.addAttribute("users", users);
        model.addAttribute("name", user.getName());

        return "Admin/AdminHomePage";
    }



    @PostMapping("/dashboard/delete")
    public String removeUser(@RequestParam int id) {

        userService.deleteUserById(id);
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard/updateUser")
    public String showUpdateForm(@RequestParam int id , Model model) {
        UserDTO userDTO= userService.findById(id);
        model.addAttribute("user",userDTO);
        return "Admin/UpdateUser";
    }


    @PostMapping("/dashboard/updateUser/save")
    public String updateUser(@ModelAttribute("user") UserDTO userDTO) {
        userService.updateUser(userDTO);
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard/search")
    public String searchUser(@RequestParam String name ,Principal principal , Model model) {
        List<UserDTO> users = userService.searchUser("USER",name);
        User user =userService.findByUsername(principal.getName());
        model.addAttribute("users",users);
        model.addAttribute("name", user.getName());
        return "Admin/AdminHomePage";
    }

    @GetMapping("/dashboard/addUser")
    public String showRegistrationForm(Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("newUser",user);
        return "User/RegistrationPage";
    }

    @GetMapping("/dashboard/editProfile")
    public String updateAdminForm(Principal principal, Model model) {

        User user =userService.findByUsername(principal.getName());
        model.addAttribute("user",user);
        return "Admin/UpdateUser";
    }

    @PostMapping("/dashboard/editProfile/save")
    public String updateAdmin(@ModelAttribute("user") UserDTO userDTO) {
        userService.updateUser(userDTO);
        return "redirect:/dashboard";
    }

}
