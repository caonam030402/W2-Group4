package com.example.baitap2.controller;


import com.example.baitap2.model.RoleModel;
import com.example.baitap2.model.UserModel;
import com.example.baitap2.reponsitory.RoleRepository;
import com.example.baitap2.reponsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserModel user, Model model) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("error", "Email đã tồn tại!");
            return "register";
        }

        // Lấy vai trò mặc định ROLE_USER
        RoleModel role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Vai trò mặc định không tồn tại"));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<RoleModel> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }


}
