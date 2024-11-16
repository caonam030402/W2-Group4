package com.example.baitap2.service;

import com.example.baitap2.model.UserInfoDetails;
import com.example.baitap2.model.UserModel;
import com.example.baitap2.reponsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    public void saveOrUpdate(UserModel user){
        userRepository.save(user);
    }

    public Optional< UserModel> findUserByEmail(String email){
        return  userRepository.findByEmail(email);
    }

    public Optional< UserModel> findUserById(int userId){
        return  userRepository.findById(userId);
    }
    public void deleteUserById(int userId){
        userRepository.deleteById(userId);
    }

    public Iterable<Object[]> getAllUserWithCompany(){
      return  userRepository.findAllUsersWithCompanyByCompanyId();
    };

    public Iterable<UserModel> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> userDetail = userRepository.findByEmail(username); // Ass
        // Converting UserInfo to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
