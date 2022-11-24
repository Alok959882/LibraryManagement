package com.example.demo.repositories;

import com.example.demo.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser, Integer> {

    MyUser findByUsername(String username);
}