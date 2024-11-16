package com.example.baitap2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "Role")
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(unique = true, nullable = false )
    public String roleName;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles",cascade = CascadeType.ALL)
    private Set<UserModel> users = new HashSet<>();;


    public RoleModel(String roleName) {
        this.roleName = roleName;
    }

    public RoleModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RoleModel{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                +
                        '}';
    }
}
