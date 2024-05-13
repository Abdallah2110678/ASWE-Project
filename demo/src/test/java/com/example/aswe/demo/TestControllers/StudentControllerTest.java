package com.example.aswe.demo.TestControllers;
import java.lang.reflect.Field;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.aswe.demo.controllers.StudentController;
import com.example.aswe.demo.models.Cart;
import com.example.aswe.demo.models.Course;
import com.example.aswe.demo.models.User;
import com.example.aswe.demo.repository.CartRepository;
import com.example.aswe.demo.repository.CourseRepository;
import com.example.aswe.demo.repository.EnrollmentRepository;
import com.example.aswe.demo.repository.UserRepository;

class StudentControllerTest {

    private StudentController studentController;
    private CartRepository cartRepository;
    private CourseRepository courseRepository;
    private EnrollmentRepository enrollmentRepository;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        studentController = new StudentController();
        cartRepository = mock(CartRepository.class);
        courseRepository = mock(CourseRepository.class);
        enrollmentRepository = mock(EnrollmentRepository.class);
        userRepository = mock(UserRepository.class);

        injectDependencies(studentController);
    }

    private void injectDependencies(Object target) {
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == CartRepository.class) {
                field.setAccessible(true);
                try {
                    field.set(target, cartRepository);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if (field.getType() == CourseRepository.class) {
                field.setAccessible(true);
                try {
                    field.set(target, courseRepository);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if (field.getType() == EnrollmentRepository.class) {
                field.setAccessible(true);
                try {
                    field.set(target, enrollmentRepository);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if (field.getType() == UserRepository.class) {
                field.setAccessible(true);
                try {
                    field.set(target, userRepository);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    void testGetAllCartItemsByUserId() {
        // Mock data
        List<Cart> cartItems = new ArrayList<>();
        cartItems.add(new Cart());
        cartItems.add(new Cart());

        // Stubbing repository method
        when(cartRepository.findByUserId(1L)).thenReturn(cartItems);

        // Call the controller method
        ResponseEntity<List<Cart>> responseEntity = studentController.getAllCartItemsByUserId(1L);

        // Verify the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(cartItems);
    }

    @Test
    void testAddCourseToCartByUserId() {
        // Mock data
        Course course = new Course();
        course.setId(1L);
        course.setTitle("Java Programming");

        // Stubbing repository methods
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(cartRepository.findByUserIdAndCourseId(1L, 1L)).thenReturn(Optional.empty());

        // Call the controller method
        ResponseEntity<String> responseEntity = studentController.addCourseToCartByUserId(1L, 1L);

        // Verify the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("Course added to the cart successfully.");
    }

    // More tests for other controller methods can be added similarly
}
