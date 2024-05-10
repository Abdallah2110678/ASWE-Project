package com.example.aswe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.aswe.demo.models.CourseMaterial;

public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {
    CourseMaterial findCourseMaterialByIdAndCourseId(Long courseMaterialId, Long courseId);

    void deleteCourseMaterialByIdAndCourseId(Long courseMaterialId, Long courseId);
}
