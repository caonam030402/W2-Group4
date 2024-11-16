package com.example.baitap2.model;

import jakarta.persistence.*;
import jakarta.ws.rs.PUT;

@Entity
@Table (name = "User_Demo")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    Integer companyId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "companyId", insertable = false, updatable = false)
    private CompanyModel company;


    public CompanyModel getCompany() {
        return company;
    }

    public void setCompany(CompanyModel company) {
        this.company = company;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public Integer getCompanyId(){
        return  companyId;
    }
    public void setCompanyId(Integer companyId){
        this.companyId = companyId;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", companyId=" + companyId +
                '}';
    }
}
