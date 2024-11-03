package org.example.pp_3_1_1.controller;

import org.example.pp_3_1_1.model.User;
import org.example.pp_3_1_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@EnableAutoConfiguration
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value ="/", method = RequestMethod.GET)
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @RequestMapping(value ="/addUser", method = RequestMethod.GET)
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-info";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/";
    }

    @RequestMapping(value ="/updateInfo", method = RequestMethod.GET)
    public String updateUser(@RequestParam("userId") int id, Model model) {
        model.addAttribute("action", "update");
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-info";
    }

    @RequestMapping(value ="/deleteUser", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("userId") int id) {
        User user = userService.getUserById(id);
        userService.delete(user);
        return "redirect:/";
    }
}
