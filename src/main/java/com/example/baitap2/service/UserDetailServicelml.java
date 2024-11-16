package com.example.baitap2.service;
import com.example.baitap2.model.RoleModel
;
import com.example.baitap2.model.UserModel
;
import com.example.baitap2.reponsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailServicelml implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        Set<RoleModel> roles = user.getRoles();

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, getAuthorities(roles));
    }

    private static List<GrantedAuthority> getAuthorities (Set<RoleModel> roles) {

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleModel role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.roleName));
        }
        return authorities;
    }
}
