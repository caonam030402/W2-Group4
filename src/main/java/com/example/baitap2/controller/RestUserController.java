package com.example.baitap2.controller;


import ch.qos.logback.classic.encoder.JsonEncoder;
import com.example.baitap2.model.RoleModel;
import com.example.baitap2.model.UserModel;
import com.example.baitap2.reponsitory.RoleRepository;
import com.example.baitap2.reponsitory.UserRepository;
import com.example.baitap2.service.UserService;
import jakarta.persistence.PostPersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class RestUserController {
    @Autowired
    UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserModel> users() {
        List<UserModel> users = userService.getAllUsers();
        return users;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable("id") int id) {
        UserModel user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserModel user) {
        System.out.println("okk");
        RoleModel role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Vai trò mặc định không tồn tại"));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<RoleModel> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        userService.saveOrUpdate(user);
        return ResponseEntity.ok("User created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") int id, @RequestBody UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.updateUser(id, user);
        return ResponseEntity.ok("Update successful");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Delete successful");
    }

}
