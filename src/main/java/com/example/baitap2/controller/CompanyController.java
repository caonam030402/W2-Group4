package com.example.baitap2.controller;

import com.example.baitap2.model.CompanyModel;
import com.example.baitap2.model.UserModel;
import com.example.baitap2.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class CompanyController {

    @Autowired
    CompanyService companyServices;

    @GetMapping("/addCompany")
    public String addCompany(Model model) {
        CompanyModel company = new CompanyModel();
        model.addAttribute("company", company);
        return "companyAdd";
    }



    @PostMapping("/addCompany")
    public String saveCompany(@ModelAttribute CompanyModel company) {
        companyServices.saveOrUpdate(company);
        System.out.println("Add Company Success!");
        return "redirect:/listCompany";
    }
    @PostMapping("/updateCompany")
    public String updateCompany(  @ModelAttribute CompanyModel company) {
        System.out.println("Update User Success!" + company.getId() + company.toString());
        Optional<CompanyModel> icc = Optional.ofNullable(companyServices.getCompanyById(company.getId()));
        if(icc.isPresent()) {
            CompanyModel ic = icc.get();
            ic.setCompanyName(company.getCompanyName());
        companyServices.saveOrUpdate(ic);
        }else {
            System.out.println("Update Company Failed!");
            throw new RuntimeException("Update Company Failed!");
        }
        return "redirect:/listCompany";
    }

    @GetMapping("/listCompany")
    public String companyList(Model model) {
        Iterable<CompanyModel> companies = companyServices.getAllCompanies();
        model.addAttribute("companies", companies);
        return "companyList";
    }


    @GetMapping("/companies")
    public String userList(Model model) {
        Iterable<CompanyModel> companies = companyServices.getAllCompanies();
        model.addAttribute("companies", companies);
        return "userAdd";
    }
    @GetMapping("/delete")
    public String deleteCompany(@RequestParam int id, Model model) {
        Optional<CompanyModel> companyModel = Optional.ofNullable(companyServices.getCompanyById(id));
        if (companyModel.isPresent()) {
        companyServices.deleteCompany(id);
            return "redirect:/listCompany";
        } else {
            throw new RuntimeException("Company not found with id: " + id);
        }
    }
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/updateCompany")
    public String updateCompany(@RequestParam int id, Model model) {
        Optional<CompanyModel> icc = Optional.ofNullable(companyServices.getCompanyById(id));
        if(icc.isPresent()) {
            model.addAttribute("company", icc.get());
            return "updateCompany";
        }else {
            throw new RuntimeException("Company not found with id: " + id);
        }
    }
}
