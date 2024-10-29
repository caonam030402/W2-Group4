package com.example.baitap2.service;

import com.example.baitap2.model.UserModel;
import com.example.baitap2.reponsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void saveOrUpdate(UserModel user){
        userRepository.save(user);
    }

    public Iterable<UserModel> getAllUsers(){
        return userRepository.findAll();
    }
}
