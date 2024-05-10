package com.example.aswe.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aswe.demo.exception.ResourceNotFound;
import com.example.aswe.demo.models.Course;
import com.example.aswe.demo.repository.CategoryRepository;
import com.example.aswe.demo.repository.CourseRepository;

@Service
public class CourseServiceImp implements CourseService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course createPost(Course course) {
        if (course.getTitle().isEmpty()) {
            throw new ResourceNotFound(false, "video title can not be null or empty");
        }
        try {
            course.setDate(new Date());
            Course saveVideo = courseRepository.save(course);

            return saveVideo;

        } catch (Exception e) {
            throw new ResourceNotFound(false, "something is worng");
        }
    }

    @Override
    public Course getPost(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFound(false, "video not found"));
        return course;
    }

    @Override
    public Course updatePost(Course course, Long id) {
        Course course1 = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound(false, "video not found"));
        course1.setTitle(course.getTitle());
        course1.setDescription(course.getDescription());
        course1.setDate(new Date());
        courseRepository.save(course1);
        return course1;
    }

    @Override
    public void deletePost(Long id) {

    }

    @Override
    public List<Course> getAllPost() {
        return courseRepository.findAll();
    }

}
