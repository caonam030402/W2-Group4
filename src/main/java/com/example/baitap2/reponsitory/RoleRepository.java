package com.example.baitap2.reponsitory;

import com.example.baitap2.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleModel, Integer> {
    Optional<RoleModel> findByName(String name);
}
