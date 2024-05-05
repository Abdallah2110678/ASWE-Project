package com.example.aswe.demo.service;



import java.util.List;

import com.example.aswe.demo.models.Course;



public interface CourseService {
    public Course createPost(Course course);
    public Course getPost(Long id);

    public Course updatePost(Course course,Long id);

    public void deletePost(Long id);
    public List<Course> getAllPost();

}
