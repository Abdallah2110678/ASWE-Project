package com.example.aswe.demo.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.aswe.demo.models.Role;
import com.example.aswe.demo.models.User;
import com.example.aswe.demo.repository.CourseRepository;
import com.example.aswe.demo.repository.UserRepository;
import com.example.aswe.demo.service.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;

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

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/allusers")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/promote-to-admin/{id}")
    public ResponseEntity<Void> promoteUserToAdmin(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setRole(Role.ADMIN);
            userRepository.save(user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

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

    @GetMapping("/allinstructors")
    public List<User> getAllInstructors() {
        return userRepository.findAllByRole(Role.INSTRUCTOR);
    }

    @GetMapping("/instructor/{id}")
    public ResponseEntity<User> getInstructor(@PathVariable Long id) {
        User instructor = userRepository.findById(id).orElse(null);
        if (instructor != null && instructor.getRole() == Role.INSTRUCTOR) {
            return ResponseEntity.ok(instructor);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/instructors/create")
    public ResponseEntity<User> createInstructor(@RequestBody User instructor) {
        instructor.setRole(Role.INSTRUCTOR);
        instructor.setPassword(passwordEncoder.encode(instructor.getPassword()));
        User savedInstructor = userRepository.save(instructor);
        return ResponseEntity.ok(savedInstructor);
    }

    @PutMapping("/instructors/update/{id}")
    public ResponseEntity<User> updateInstructor(@RequestBody User instructor, @PathVariable Long id) {
        User repoInstructor = userRepository.findById(id).orElse(null);
        if (repoInstructor != null && repoInstructor.getRole() == Role.INSTRUCTOR) {
            repoInstructor.setFname(instructor.getFname());
            repoInstructor.setLname(instructor.getLname());
            repoInstructor.setGender(instructor.getGender());
            repoInstructor.setPhone(instructor.getPhone());
            repoInstructor.setEmail(instructor.getEmail());
            repoInstructor.setDob(instructor.getDob());
            if (!instructor.getPassword().isEmpty()) {
                repoInstructor.setPassword(passwordEncoder.encode(instructor.getPassword()));
            }
            userRepository.save(repoInstructor);
            return ResponseEntity.ok(repoInstructor);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/instructors/delete/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        User instructor = userRepository.findById(id).orElse(null);
        if (instructor != null && instructor.getRole() == Role.INSTRUCTOR) {
            userRepository.delete(instructor);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/allstudents")
    public List<User> getAllStudents() {
        return userRepository.findAllByRole(Role.STUDENT);
    }

    @GetMapping("/getstudent/{id}")
    public ResponseEntity<User> getStudent(@PathVariable Long id) {
        User student = userRepository.findById(id).orElse(null);
        if (student != null && student.getRole() == Role.STUDENT) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/students/create")
    public ResponseEntity<User> createStudent(@RequestBody User student) {
        student.setRole(Role.STUDENT);
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        User savedStudent = userRepository.save(student);
        return ResponseEntity.ok(savedStudent);
    }

    @PutMapping("/students/update/{id}")
    public User updateStudent(@RequestBody User student, @PathVariable Long id) {
        User repoStudent = userRepository.findById(id).orElse(null);
        if (repoStudent != null && repoStudent.getRole() == Role.STUDENT) {
            repoStudent.setFname(student.getFname());
            repoStudent.setLname(student.getLname());
            repoStudent.setEmail(student.getEmail());
            repoStudent.setGender(student.getGender());
            repoStudent.setDob(student.getDob());
            if (!student.getPassword().isEmpty()) {
                repoStudent.setPassword(passwordEncoder.encode(student.getPassword()));
            }
            userRepository.save(repoStudent);
            return repoStudent;
        }
        return null;
    }

    @DeleteMapping("/students/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        User student = userRepository.findById(id).orElse(null);
        if (student != null && student.getRole() == Role.STUDENT) {
            this.userRepository.delete(student);
            return ResponseEntity.noContent().build();
        }
        return null;
    }

    @GetMapping("/check-email/{email}")
    public ResponseEntity<Boolean> checkEmailExists(@PathVariable String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return ResponseEntity.ok(user.isPresent());
    }

    public User getUserFromToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwtToken = authHeader.substring(7);
            return authService.getUserFromToken(jwtToken);
        } else {
            return null;
        }
    }

    @AdminAction
    @GetMapping("/jwt")
    public ResponseEntity<HashMap<String, Object>> AngetMethodName(HttpServletRequest request) {
        User user = getUserFromToken(request);
        return ResponseEntity.ok(user.toHashMap());
    }

    @PutMapping("/updateProfile/{id}")
    public ResponseEntity<User> updateUserProfile(@RequestBody User updatedProfile, @PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setFname(updatedProfile.getFname());
            user.setLname(updatedProfile.getLname());
            user.setEmail(updatedProfile.getEmail());
            user.setGender(updatedProfile.getGender());
            user.setDob(updatedProfile.getDob());
            // Check if the password is provided and update it if not empty
            if (updatedProfile.getPassword() != null && !updatedProfile.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(updatedProfile.getPassword()));
            }
            userRepository.save(user);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteProfile/{id}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // @PostMapping("/forgot-password")
    // public ResponseEntity<Void> forgotPassword(@RequestBody Map<String, String>
    // request) {
    // String email = request.get("email");
    // Optional<User> userOptional = userRepository.findByEmail(email);
    // if (userOptional.isPresent()) {
    // User user = userOptional.get();
    // String resetToken = UUID.randomUUID().toString();
    // user.setResetToken(resetToken);
    // userRepository.save(user);
    // // Send the password reset email (implementation depends on your email
    // service)
    // return ResponseEntity.ok().build();
    // } else {
    // // User not found, return 404
    // return ResponseEntity.notFound().build();
    // }
    // }

    // @PostMapping("/reset-password")
    // public ResponseEntity<Void> resetPassword(@RequestBody Map<String, String>
    // request) {
    // String resetToken = request.get("resetToken");
    // String newPassword = request.get("newPassword");
    // Optional<User> userOptional = userRepository.findByResetToken(resetToken);
    // if (userOptional.isPresent()) {
    // User user = userOptional.get();
    // user.setPassword(passwordEncoder.encode(newPassword));
    // user.setResetToken(null);
    // userRepository.save(user);
    // return ResponseEntity.ok().build();
    // } else {
    // // Token not found or expired, return 404
    // return ResponseEntity.notFound().build();
    // }
    // }
}
