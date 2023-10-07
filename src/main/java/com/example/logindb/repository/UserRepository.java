package com.example.logindb.repository;

import com.example.logindb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    void deleteById(int id);

    User findByUsername(String email);
    User findById(int id);

    List<User> findAllByRole(String role);
    List<User> findAllByRoleAndNameStartingWithIgnoreCase(String role,String name);

    boolean existsByUsername(String email);
}
