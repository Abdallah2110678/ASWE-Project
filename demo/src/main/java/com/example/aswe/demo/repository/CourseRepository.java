package com.example.aswe.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aswe.demo.models.Course;




public interface CourseRepository extends JpaRepository <Course, Long> {

}
