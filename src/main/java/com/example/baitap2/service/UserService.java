package com.example.baitap2.service;

import com.example.baitap2.model.UserModel;
import com.example.baitap2.reponsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void saveOrUpdate(UserModel user){
        userRepository.save(user);
    }

    public Iterable<Object[]> getAllUserWithCompany(){
      return  userRepository.findAllUsersWithCompanyByCompanyId();
    };

    public List<UserModel> getAllUsers(){
        return userRepository.findAll();
    }

    public UserModel getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public Optional<UserModel> updateUser(int id, UserModel updatedUser) {
        Optional<UserModel> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();

            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            user.setCompanyId(updatedUser.getCompanyId());
            user.setPassword(updatedUser.getPassword());

            userRepository.save(user);

            return Optional.of(user);
        }

        return Optional.empty();
    }

    public boolean deleteUser(int id){
        userRepository.deleteById(id);
        return true;
    }

}
