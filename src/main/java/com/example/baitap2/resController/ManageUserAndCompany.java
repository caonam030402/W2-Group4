package com.example.baitap2.resController;

import com.example.baitap2.dto.AuthRequest;
import com.example.baitap2.dto.RequestUser;
import com.example.baitap2.jwtsecurity.JwtService;
import com.example.baitap2.model.CompanyModel;
import com.example.baitap2.model.RoleModel;
import com.example.baitap2.model.UserModel;
import com.example.baitap2.response.UserResponse;
import com.example.baitap2.service.CompanyService;
import com.example.baitap2.service.RoleService;
import com.example.baitap2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ManageUserAndCompany {
    @Autowired
    UserService userService;

    @Autowired
    CompanyService companyService;


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;
    @Qualifier("authenticationManager")
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/api/users")
    public List<UserModel> users() {
     return  (List<UserModel>) userService.getAllUsers();
    }
    @PostMapping("/api/register")
    public String users(@RequestBody RequestUser userInfo) {
        CompanyModel company = null;
        if(userInfo.companyId != -1){
            company = companyService.getCompanyById(userInfo.companyId);
            if(company == null) { return  " company not found"; }
        }
        Set<RoleModel> listRole = new HashSet<>();
        for (int role : (int[])userInfo.roles) {
          Optional<RoleModel> roleExsited =  roleService.getRoleById(role);
          System.out.println("check role " +  " " + roleExsited.get().roleName);
            listRole.add(roleExsited.get());
        }
             UserModel user = new UserModel();
             user.setUsername(userInfo.username);
             user.setPassword(passwordEncoder.encode(userInfo.password));
             user.setFirstName(userInfo.firstName);
             user.setLastName(userInfo.lastName);
             user.setEmail(userInfo.email);
             user.setCompany(company);
             user.setRoles(listRole);
             userService.saveOrUpdate(user);
             return "add";

    }
    @PutMapping("/user")
    public String updateUser(@ModelAttribute UserModel user, @RequestParam int userId) {
        userService.findUserById(userId).orElseThrow();
        userService.saveOrUpdate(user);
        return "update user success";
    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
                userService.findUserById(id).orElseThrow();
                userService.deleteUserById(id);
              return   ResponseEntity.ok("delete user success");
    }

    @GetMapping("/api/company")
    public List<CompanyModel> companies() {
        List<CompanyModel> companies = (List<CompanyModel>) companyService.getAllCompanies();
        ResponseEntity.status(200).body(companies);
        return  companies;
    }

    @PostMapping("/api/company")
    public String users(@RequestBody CompanyModel companyInfo) {
        System.out.println("Check user info::: " + companyInfo);
        CompanyModel companyModel = new CompanyModel();
        companyModel.setCompanyName(companyInfo.getCompanyName());
        companyService.saveOrUpdate(companyModel);

        return "add";
    }

    @DeleteMapping("/api/company")
    public ResponseEntity<String> deleteCompany(@RequestParam int id) {
        CompanyModel companyModel =companyService.getCompanyById(id);

        if (companyModel != null) {
            companyService.deleteCompany(id);
            return ResponseEntity.ok("Company with id " + id + " has been deleted successfully.");
        } else {
            throw new RuntimeException("Company not found with id: " + id);
        }
    }

    @PostMapping("/api/generateToken")
    public UserResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
           Optional<UserModel> user =  userService.findUserByEmail(authRequest.getUsername());
           UserResponse userResponse = new UserResponse();
           userResponse.setFullName(user.get().getLastName() + user.get().getFirstName());
           userResponse.setEmail(user.get().getEmail());
           userResponse.setToken(jwtService.generateToken(authRequest.getUsername()));
            return userResponse;
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}
