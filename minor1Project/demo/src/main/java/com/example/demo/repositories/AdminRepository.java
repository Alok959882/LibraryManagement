package com.example.demo.repositories;

import com.example.demo.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

}