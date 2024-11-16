package com.example.baitap2.reponsitory;

import com.example.baitap2.model.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends CrudRepository<UserModel, Integer>
{
    @Query("SELECT u, c FROM UserModel u JOIN CompanyModel c ON u.company.id = c.id")
    List<Object[]> findAllUsersWithCompanyByCompanyId();

    @Query("SELECT u FROM UserModel u  WHERE u.email = :email")
    UserModel findUserWithRoleByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM User_Demo WHERE email = :email", nativeQuery = true)
    Optional<UserModel> findByEmail(@Param("email") String email);

    List<UserModel> findAll();
}
