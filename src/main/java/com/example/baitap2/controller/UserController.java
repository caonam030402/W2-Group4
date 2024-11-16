package com.example.baitap2.controller;

import com.example.baitap2.model.CompanyModel;
import com.example.baitap2.model.UserModel;
import com.example.baitap2.service.CompanyService;
import com.example.baitap2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;

    @GetMapping("/addUser")
    public String addUser(Model model) {
        UserModel user = new UserModel();
        model.addAttribute("user", user);
        Iterable<CompanyModel> companies = companyService.getAllCompanies();
        System.out.println("check company list" + " " + companies.toString());
        model.addAttribute("companyList", companies);
        return "userAdd";
    }

    @PostMapping("/addUser")
    public String saveUser(@ModelAttribute UserModel user) {
    System.out.println("check user :::: " + user);
        userService.saveOrUpdate(user);
        System.out.println("Add User Success!");
        System.out.println("FirstName: " + user.getFirstName());
        System.out.println("LastName: " + user.getLastName());
        System.out.println("company id: " + user.getCompanyId());
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
            userList.add(user);
        System.out.println("check company list" + "user  " + user.toString() +"\n company" + companydb.toString());
        };
        Iterable<UserModel> users  = userList;
        model.addAttribute("users",  users);
        return "userList";
    }
}
