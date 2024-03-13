package com.example.aswe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aswe.demo.models.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository <Instructor, Long>{
        boolean existsByEmail(String email);

}
