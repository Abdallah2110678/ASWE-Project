package com.example.aswe.demo.repositories;

import com.example.aswe.demo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository <Student,Long>{
    Student findByEmail(String email);
}
