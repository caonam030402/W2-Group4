package com.example.baitap2.controller;

import com.example.baitap2.model.UserModel;
import com.example.baitap2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/addUser")
    public String addUser(Model model) {
        UserModel user = new UserModel();
        model.addAttribute("user", user);
        return "userAdd";
    }

    @PostMapping("/addUser")
    public String saveUser(@ModelAttribute UserModel user) {

        userService.saveOrUpdate(user);
        System.out.println("Add User Success!");
        System.out.println("FirstName: " + user.getFirstName());
        System.out.println("LastName: " + user.getLastName());
        return "redirect:/user";
    }

    @GetMapping("/user")
    public String userList(Model model) {
        Iterable<UserModel> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userList";
    }
}
