package com.example.baitap2.controller;

import com.example.baitap2.model.CompanyModel;
import com.example.baitap2.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class RestCompanyController {
    @Autowired
    public CompanyService companyService;

    @GetMapping
    public List<CompanyModel> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyModel> getCompanyById(@PathVariable("id") int id) {
        CompanyModel company = companyService.getCompanyById(id);
        return new ResponseEntity<>(company , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody CompanyModel company) {
        companyService.createCompany(company);
        return ResponseEntity.ok("Company created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable("id") int id, @RequestBody CompanyModel company) {
        companyService.updateCompany(id, company);
        return ResponseEntity.ok("Update successful");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable("id") int id) {
        companyService.deleteCompany(id);
        return ResponseEntity.ok("Delete successful");
    }
}
