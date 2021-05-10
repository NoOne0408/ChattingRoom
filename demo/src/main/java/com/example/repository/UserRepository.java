package com.example.repository;

import com.example.entity.Person;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username,String password);
    User findByUsername(String username);
}
