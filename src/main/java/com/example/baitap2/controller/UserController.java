package com.example.baitap2.controller;

import com.example.baitap2.model.CompanyModel;
import com.example.baitap2.model.RoleModel;
import com.example.baitap2.model.UserModel;
import com.example.baitap2.service.CompanyService;
import com.example.baitap2.service.RoleService;
import com.example.baitap2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;

    @Autowired
    RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/addUser")
    public String addUser(Model model) {
        UserModel user = new UserModel();
        model.addAttribute("user", user);
        // add role
        roleService.saveOrUpdate(new RoleModel("USER"));
        roleService.saveOrUpdate(new RoleModel("ADMIN"));

        Iterable<CompanyModel> companies = companyService.getAllCompanies();
        Iterable<RoleModel> roles = roleService.getAllRoles();
        model.addAttribute("roleList", roles);
        System.out.println("check company list" + " " + companies.toString());
        model.addAttribute("companyList", companies);
        return "userAdd";
    }

    @PostMapping("/addUser")
    public String saveUser(@ModelAttribute UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveOrUpdate(user);
        System.out.println("Add User Success!");
        return "redirect:/user";
    }



    @GetMapping("/user")
    public String userList(Model model) {
        List<UserModel> userList = new ArrayList<>();
        Iterable<Object[]> userCompanies = userService.getAllUserWithCompany();
        for (Object[] userCompany : userCompanies) {
            UserModel user = new UserModel();
            UserModel userdb = (UserModel) userCompany[0];
            CompanyModel companydb = (CompanyModel) userCompany[1];
            user.setCompany(companydb);
            user.setFirstName(userdb.getFirstName());
            user.setLastName(userdb.getLastName());
            user.setEmail(userdb.getEmail());
            user.setPassword(userdb.getPassword());
            userList.add(user);
        };
        Iterable<UserModel> users  = userList;
        model.addAttribute("users",  users);
        return "userList";
    }
}
