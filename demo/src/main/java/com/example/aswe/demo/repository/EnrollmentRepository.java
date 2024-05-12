package com.example.aswe.demo.repository;

import com.example.aswe.demo.models.Course;
import com.example.aswe.demo.models.Enrollment;
import com.example.aswe.demo.models.User;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByUserAndCourse(User user, Course course);
    List<Enrollment> findByUser(User user);
    int countByCourse(Course course);
}

