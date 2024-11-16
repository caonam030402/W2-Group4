package com.example.baitap2.service;

import com.example.baitap2.model.RoleModel;
import com.example.baitap2.reponsitory.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public void saveOrUpdate(RoleModel role){
        roleRepository.save(role);
    }
    public Iterable<RoleModel> getAllRoles(){
        return roleRepository.findAll();
    }
    public Optional<RoleModel> getRoleById(int id){
        return  roleRepository.findById(id);
    }
}
