package com.example.aswe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aswe.demo.models.Admin;

public interface AdminRepository extends JpaRepository <Admin, Long>{
    boolean existsByEmail(String Email);
    
}
