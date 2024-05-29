package com.example.aswe.demo.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aswe.demo.annotations.AdminAction;
import com.example.aswe.demo.models.AuthenticationResponse;
import com.example.aswe.demo.models.Enrollment;
import com.example.aswe.demo.models.Role;
import com.example.aswe.demo.models.User;
import com.example.aswe.demo.repository.CourseRepository;
import com.example.aswe.demo.repository.EnrollmentRepository;
import com.example.aswe.demo.repository.UserRepository;
import com.example.aswe.demo.service.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private AuthenticationService authService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;


    @GetMapping("/statistics")
    public Map<String, Integer> getStatistics() {
        Map<String, Integer> statistics = new HashMap<>();
        int studentCount = userRepository.countUsersByRole(Role.STUDENT);
        int instructorCount = userRepository.countUsersByRole(Role.INSTRUCTOR);
        int courseCount = courseRepository.findAll().size();

        statistics.put("studentCount", studentCount);
        statistics.put("instructorCount", instructorCount);
        statistics.put("courseCount", courseCount);

        return statistics;
    }

    @Transactional
    @DeleteMapping("/students/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        try {
            Optional<User> userOptional = userRepository.findById(id);

            if (userOptional.isPresent()) {
                User student = userOptional.get();
                enrollmentRepository.deleteAllByUser(student);
                this.userRepository.delete(student);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID: " + id + " not found");
            }
        } catch (Exception e) {
            // Log the exception for troubleshooting
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete enrollments for user with ID: " + id);
        }
    }

    @DeleteMapping("/cc/{id}")
    public ResponseEntity<?> deleteEnrollmentsByUserId(@PathVariable Long id) {
        try {
            Optional<User> userOptional = userRepository.findById(id);

            if (userOptional.isPresent()) {
                User student = userOptional.get();
                enrollmentRepository.deleteAllByUser(student);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID: " + id + " not found");
            }
        } catch (Exception e) {
            // Log the exception for troubleshooting
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete enrollments for user with ID: " + id);
        }
    }
}
