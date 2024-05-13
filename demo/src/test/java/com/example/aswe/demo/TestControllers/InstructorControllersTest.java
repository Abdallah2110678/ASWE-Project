package com.example.aswe.demo.TestControllers;

import com.example.aswe.demo.controllers.InstructorControllers;
import com.example.aswe.demo.models.Category;
import com.example.aswe.demo.models.Course;
import com.example.aswe.demo.models.User;
import com.example.aswe.demo.repository.CourseMaterialRepository;
import com.example.aswe.demo.repository.CourseRepository;
import com.example.aswe.demo.repository.UserRepository;
import com.example.aswe.demo.service.CourseService;
import com.example.aswe.demo.service.MaterialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse; // Import this
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class InstructorControllersTest {

    @InjectMocks
    private InstructorControllers instructorControllers;

    @Mock
    private CourseService courseService;

    @Mock
    private MaterialService materialService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseMaterialRepository courseMaterialRepository;

    @Mock
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(InstructorControllersTest.class);


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Long categoryId = 1L;
        Long useId = 1L;
        Course course = new Course();

        logger.info("Starting testSave...");

        // when(courseRepository.findById(categoryId)).thenReturn(Optional.of(new Category()));
        when(userRepository.findById(useId)).thenReturn(Optional.of(new User()));
        when(courseService.createPost(course)).thenReturn(course);

        ResponseEntity<?> response = instructorControllers.save(categoryId, useId, course);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(courseService).createPost(course);

        logger.info("testSave completed successfully.");
    }

    @Test
    void testGetAllCourse() {
        logger.info("Starting testGetAllCourse...");

        when(courseService.getAllPost()).thenReturn(List.of(new Course(), new Course()));

        ResponseEntity<?> response = instructorControllers.getAllCourse();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(List.class);
        assertThat(((List<?>) response.getBody()).size()).isEqualTo(2);

        logger.info("testGetAllCourse completed successfully.");
    }

}
