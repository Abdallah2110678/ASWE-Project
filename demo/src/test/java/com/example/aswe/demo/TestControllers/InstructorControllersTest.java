package com.example.aswe.demo.TestControllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.aswe.demo.controllers.InstructorControllers;
import com.example.aswe.demo.models.Category;
import com.example.aswe.demo.models.Course;
import com.example.aswe.demo.models.CourseMaterial;
import com.example.aswe.demo.models.User;
import com.example.aswe.demo.repository.CategoryRepository;
import com.example.aswe.demo.repository.CourseMaterialRepository;
import com.example.aswe.demo.repository.CourseRepository;
import com.example.aswe.demo.repository.EnrollmentRepository;
import com.example.aswe.demo.repository.UserRepository;
import com.example.aswe.demo.service.CourseService;
import com.example.aswe.demo.service.MaterialService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class InstructorControllersTest {

    @MockBean
    private CourseService courseService;

    @MockBean
    private MaterialService materialService;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private CourseMaterialRepository courseMaterialRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private EnrollmentRepository enrollmentRepository;

    @Test
    void testSave() {
        Long categoryId = 1L;
        Long userId = 1L;
        Course course = new Course();
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(new Category()));
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
        when(courseService.createPost(course)).thenReturn(course);
        InstructorControllers instructorControllers = new InstructorControllers();
        ResponseEntity<?> response = instructorControllers.save(categoryId, userId, course);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(courseService).createPost(course);
    }

    @Test
    void testGetAllCourse() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course());
        courses.add(new Course());
        when(courseService.getAllPost()).thenReturn(courses);
        InstructorControllers instructorControllers = new InstructorControllers();
        ResponseEntity<?> response = instructorControllers.getAllCourse();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(courses);
    }

    @Test
    void testGetAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
        categories.add(new Category());
        when(categoryRepository.findAll()).thenReturn(categories);
        InstructorControllers instructorControllers = new InstructorControllers();
        ResponseEntity<List<Category>> response = instructorControllers.getAllCategories();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(categories);
    }

    @Test
    void testGetCourseById() {
        Long id = 1L;
        Course course = new Course();
        when(courseService.getPost(id)).thenReturn(course);
        InstructorControllers instructorControllers = new InstructorControllers();
        assertThat(instructorControllers.getCourseById(id)).isEqualTo(course);
    }

    @Test
    void testGetCoursesByUserId() {
        Long userId = 1L;
        List<Course> courses = new ArrayList<>();
        courses.add(new Course());
        courses.add(new Course());
        when(courseRepository.findAllByUserId(userId)).thenReturn(courses);
        InstructorControllers instructorControllers = new InstructorControllers();
        assertThat(instructorControllers.getCoursesByUserId(userId)).isEqualTo(courses);
    }

    @Test
    void testUploadVideo() throws IOException {
        Long id = 1L;
        MultipartFile video = mock(MultipartFile.class);
        String title = "Video Title";
        CourseMaterial courseMaterial = new CourseMaterial();
        Course course = new Course();
        when(courseRepository.findById(id)).thenReturn(Optional.of(course));
        when(materialService.uploadCourseMaterial(anyString(), eq(video))).thenReturn(courseMaterial);
        InstructorControllers instructorControllers = new InstructorControllers();
        assertThat(instructorControllers.uploadVideo(id, video, title)).isEqualTo(courseMaterial);
    }

    @Test
    void testPlayVideo() throws IOException {
        Long id = 1L;
        Long im = 1L;
        Course course = new Course();
        CourseMaterial courseMaterial = new CourseMaterial();
        jakarta.servlet.http.HttpServletResponse response = mock(jakarta.servlet.http.HttpServletResponse.class);
        InputStream inputStream = mock(InputStream.class);
        when(courseRepository.findById(id)).thenReturn(Optional.of(course));
        when(materialService.getCourseMaterial(anyString(), anyString(), eq(id))).thenReturn(inputStream);
        when(course.getCourseMaterials()).thenReturn(List.of(courseMaterial));
        when(courseMaterial.getId()).thenReturn(im);
        InstructorControllers instructorControllers = new InstructorControllers();
        instructorControllers.playVideo(id, im, response);
        verify(response).setContentType(org.springframework.http.MediaType.ALL_VALUE);
        verify(response.getOutputStream()).write(any(byte[].class));
    }

    @Test
    void testDeleteCourse() {
        Long id = 1L;
        Course course = new Course();
        when(courseRepository.findById(id)).thenReturn(Optional.of(course));
        InstructorControllers instructorControllers = new InstructorControllers();
        ResponseEntity<?> response = instructorControllers.deleteCourse(id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(courseMaterialRepository, times(course.getCourseMaterials().size())).delete(any());
        verify(courseRepository).delete(course);
    }

    @Test
    void testDeleteCourseMaterial() throws Exception {
        Long courseId = 1L;
        Long materialId = 1L;
        CourseMaterial courseMaterial = new CourseMaterial();
        when(courseRepository.existsById(courseId)).thenReturn(true);
        when(courseMaterialRepository.findCourseMaterialByIdAndCourseId(materialId, courseId)).thenReturn(courseMaterial);
        InstructorControllers instructorControllers = new InstructorControllers();
        ResponseEntity<String> response = instructorControllers.deleteCourseMaterial(courseId, materialId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("CourseMaterial deleted successfully");
        verify(courseMaterialRepository).delete(courseMaterial);
    }

    @Test
    void testGetNumberOfStudentsEnrolled() {
        Long courseId = 1L;
        Course course = new Course();
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(enrollmentRepository.countByCourse(course)).thenReturn(5);
        InstructorControllers instructorControllers = new InstructorControllers();
        ResponseEntity<Integer> response = instructorControllers.getNumberOfStudentsEnrolled(courseId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(5);
    }
}
