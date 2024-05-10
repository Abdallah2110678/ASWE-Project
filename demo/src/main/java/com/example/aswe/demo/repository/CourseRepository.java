package com.example.aswe.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.aswe.demo.models.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByUserId(Long id);
}
