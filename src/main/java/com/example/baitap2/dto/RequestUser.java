package com.example.baitap2.dto;

public class RequestUser {

    public String firstName;
    public String lastName;
    public String username;
    public String email;
    public String password;
    public int companyId ;
    public int[] roles;
    public RequestUser() {
        this.companyId = -1;
    };
}
