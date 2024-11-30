package com.example.baitap2.reponsitory;

import com.example.baitap2.model.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends CrudRepository<UserModel, Integer>
{
    @Query("SELECT u, c FROM UserModel u JOIN CompanyModel c ON u.companyId = c.id")
    List<Object[]> findAllUsersWithCompanyByCompanyId();

    List<UserModel> findAll();

    Optional<UserModel> findByEmail(String email);

}
