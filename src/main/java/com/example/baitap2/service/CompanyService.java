package com.example.baitap2.service;

import com.example.baitap2.model.CompanyModel;
import com.example.baitap2.model.UserModel;
import com.example.baitap2.reponsitory.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    public void saveOrUpdate(CompanyModel company){
        companyRepository.save(company);
    }
    public void deleteCompany(int companyId){
        companyRepository.deleteById(companyId);
    }
    public CompanyModel getCompanyById(int companyId){
        return companyRepository.findById(companyId).orElseGet(null);
    }
    public Iterable<CompanyModel> getAllCompanies(){
        return companyRepository.findAll();
    }
}
