package com.example.baitap2.reponsitory;

import com.example.baitap2.model.CompanyModel;
import com.example.baitap2.model.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CompanyRepository extends CrudRepository<CompanyModel, Integer>
{
    List<CompanyModel> findAll();
}
