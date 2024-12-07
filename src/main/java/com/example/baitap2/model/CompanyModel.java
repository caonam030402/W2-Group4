package com.example.baitap2.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "Company")
public class CompanyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    public String companyName;



    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId", referencedColumnName = "id")
    @JsonManagedReference
    private List<UserModel> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public  void setListUser(List<UserModel> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "CompanyModel{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                +
                '}';
    }
}
