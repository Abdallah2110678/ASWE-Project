package com.example.aswe.demo.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aswe.demo.models.Cart;
import com.example.aswe.demo.models.Course;
import com.example.aswe.demo.models.Enrollment;
import com.example.aswe.demo.models.User;
import com.example.aswe.demo.repository.CartRepository;
import com.example.aswe.demo.repository.CourseRepository;
import com.example.aswe.demo.repository.EnrollmentRepository;
import com.example.aswe.demo.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/student")
public class StudentController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    // Get all cart items by user id

    @GetMapping("/cart/{userId}")
    public ResponseEntity<List<Cart>> getAllCartItemsByUserId(@PathVariable Long userId) {
        try {
            List<Cart> cartItems = cartRepository.findByUserId(userId);
            return ResponseEntity.ok(cartItems);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Return a response with an error status
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/cart/addcourse/{userId}/{courseId}")
    public ResponseEntity<String> addCourseToCartByUserId(@PathVariable Long userId, @PathVariable Long courseId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (userOptional.isPresent() && courseOptional.isPresent()) {
            User user = userOptional.get();
            Course course = courseOptional.get();
            if (checkCourseInCart(userId, courseId))
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Course already in the cart.");
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setCourse(course);
            cartRepository.save(cart);
            return ResponseEntity.ok("Course added to the cart successfully.");

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + userId + " or Course with Id" + courseId + " not found.");
        }
    }

    public Boolean checkCourseInCart(Long userId, Long courseId) {
        Optional<Cart> cart = cartRepository.findByUserIdAndCourseId(userId, courseId);
        return cart.isPresent();
    }

    @DeleteMapping("/cart/delete/{cartItemId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable Long cartItemId) {
        Optional<Cart> cartItemOptional = cartRepository.findById(cartItemId);
        if (cartItemOptional.isPresent()) {
            Cart cartItem = cartItemOptional.get();
            cartRepository.delete(cartItem);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/enroll/{userId}/{courseId}")
    public ResponseEntity<String> enrollToCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (userOptional.isPresent() && courseOptional.isPresent()) {
            User user = userOptional.get();
            Course course = courseOptional.get();
            if (!enrollmentRepository.existsByUserAndCourse(user, course)) {
                Enrollment enrollment = new Enrollment();
                enrollment.setUser(user);
                enrollment.setCourse(course);
                enrollment.setEnrollmentDate(new Date());
                enrollmentRepository.save(enrollment);
                return ResponseEntity.ok("Enrolled to course successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Already enrolled to this course.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + userId + " or Course with ID " + courseId + " not found.");
        }
    }

}
