package org.example.pp_3_1_1.controller;

import org.example.pp_3_1_1.model.User;
import org.example.pp_3_1_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value ="/")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping(value ="/addUser")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-info";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/";
    }

    @PostMapping(value = "/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/";
    }

    @GetMapping(value ="/updateInfo")
    public String updateUser(@RequestParam("userId") int id, Model model) {
        model.addAttribute("action", "update");
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-info";
    }

    @GetMapping(value ="/deleteUser")
    public String deleteUser(@RequestParam("userId") int id) {
        User user = userService.getUserById(id);
        userService.delete(user);
        return "redirect:/";
    }
}
