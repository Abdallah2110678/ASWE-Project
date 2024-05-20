package com.example.aswe.demo.TestControllers;

import com.example.aswe.demo.controllers.StudentController;
import com.example.aswe.demo.models.Cart;
import com.example.aswe.demo.models.Course;
import com.example.aswe.demo.models.Enrollment;
import com.example.aswe.demo.models.User;
import com.example.aswe.demo.repository.CartRepository;
import com.example.aswe.demo.repository.CourseRepository;
import com.example.aswe.demo.repository.EnrollmentRepository;
import com.example.aswe.demo.repository.UserRepository;
import com.example.aswe.demo.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class StudentControllerTest {

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private EnrollmentRepository enrollmentRepository;

    @MockBean
    private CourseService courseService;

    @Test
    void testGetAllCartItemsByUserId() {
        Long userId = 1L;
        List<Cart> cartItems = new ArrayList<>();
        cartItems.add(new Cart());

        when(cartRepository.findByUserId(userId)).thenReturn(cartItems);

        StudentController studentController = new StudentController();
        ResponseEntity<List<Cart>> response = studentController.getAllCartItemsByUserId(userId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(cartItems);
    }

    @Test
    void testSearchCoursesByTitle() {
        String title = "Test Title";
        List<Course> courses = new ArrayList<>();
        courses.add(new Course());

        when(courseRepository.findByTitleContainingIgnoreCase(title)).thenReturn(courses);

        StudentController studentController = new StudentController();
        List<Course> response = studentController.searchCoursesByTitle(title);

        assertThat(response).isEqualTo(courses);
    }

    @Test
    void testGetCourseById() {
        Long id = 1L;
        Course course = new Course();

        when(courseService.getPost(id)).thenReturn(course);

        StudentController studentController = new StudentController();
        Course response = studentController.getCourseById(id);

        assertThat(response).isEqualTo(course);
    }

    @Test
    void testAddCourseToCartByUserId() {
        Long userId = 1L;
        Long courseId = 1L;
        User user = new User();
        Course course = new Course();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(cartRepository.findByUserIdAndCourseId(userId, courseId)).thenReturn(Optional.empty());

        StudentController studentController = new StudentController();
        ResponseEntity<String> response = studentController.addCourseToCartByUserId(userId, courseId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Course added to the cart successfully.");
    }

    @Test
    void testDeleteCartItem() {
        Long cartItemId = 1L;
        Cart cart = new Cart();

        when(cartRepository.findById(cartItemId)).thenReturn(Optional.of(cart));

        StudentController studentController = new StudentController();
        ResponseEntity<?> response = studentController.deleteCartItem(cartItemId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(cartRepository).delete(cart);
    }

    @Test
    void testEnrollToCourse() {
        Long userId = 1L;
        Long courseId = 1L;
        User user = new User();
        Course course = new Course();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(enrollmentRepository.existsByUserAndCourse(user, course)).thenReturn(false);

        StudentController studentController = new StudentController();
        ResponseEntity<String> response = studentController.enrollToCourse(userId, courseId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Enrolled to course successfully.");
    }

    @Test
    void testGetCoursesEnrolledByUser() {
        Long userId = 1L;
        User user = new User();
        List<Enrollment> enrollments = new ArrayList<>();
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(new Course());
        enrollments.add(enrollment);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(enrollmentRepository.findByUser(user)).thenReturn(enrollments);

        StudentController studentController = new StudentController();
        ResponseEntity<?> response = studentController.getCoursesEnrolledByUser(userId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(enrollments.stream().map(Enrollment::getCourse).collect(Collectors.toList()));
    }

    @Test
    void testCheckEnrollment() {
        Long userId = 1L;
        Long courseId = 1L;
        User user = new User();
        Course course = new Course();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(enrollmentRepository.existsByUserAndCourse(user, course)).thenReturn(true);

        StudentController studentController = new StudentController();
        boolean response = studentController.checkEnrollment(userId, courseId);

        assertThat(response).isTrue();
    }
}
