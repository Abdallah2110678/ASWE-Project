package com.example.aswe.demo.controllers;

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

import com.example.aswe.demo.models.AuthenticationResponse;
import com.example.aswe.demo.models.Role;
import com.example.aswe.demo.models.User;
import com.example.aswe.demo.repository.UserRepository;
import com.example.aswe.demo.service.AuthenticationService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private AuthenticationService authService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    // CRUD operations for instructors

    @PutMapping("/instructors/{id}")
    public ResponseEntity<User> updateInstructor(@RequestBody User instructor, @PathVariable Long id) {
        User repoInstructor = userRepository.findById(id).orElse(null);
        if (repoInstructor != null && repoInstructor.getRole() == Role.INSTRUCTOR) {
            repoInstructor.setFname(instructor.getFname());
            repoInstructor.setLname(instructor.getLname());
            repoInstructor.setGender(instructor.getGender());
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

    @GetMapping("/instructors/{id}")
    public ResponseEntity<User> getInstructor(@PathVariable Long id) {
        User instructor = userRepository.findById(id).orElse(null);
        if (instructor != null && instructor.getRole() == Role.INSTRUCTOR) {
            return ResponseEntity.ok(instructor);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/instructors")
    public ResponseEntity<User> createInstructor(@RequestBody User instructor) {
        instructor.setRole(Role.INSTRUCTOR);
        instructor.setPassword(passwordEncoder.encode(instructor.getPassword()));
        User savedInstructor = userRepository.save(instructor);
        return ResponseEntity.ok(savedInstructor);
    }

    @DeleteMapping("/instructors/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        User instructor = userRepository.findById(id).orElse(null);
        if (instructor != null && instructor.getRole() == Role.INSTRUCTOR) {
            userRepository.delete(instructor);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

@GetMapping("/check-email/instructor/{email}")
public ResponseEntity<Boolean> checkInstructorEmailExists(@PathVariable String email) {
    Optional<User> instructor = userRepository.findByEmailAndRole(email, Role.INSTRUCTOR);
    return ResponseEntity.ok(instructor.isPresent());
}

@GetMapping("/instructors")
public ResponseEntity<Iterable<User>> getAllInstructors() {
    Iterable<User> instructors = userRepository.findAllByRole(Role.INSTRUCTOR);
    return ResponseEntity.ok(instructors);
}


    // CRUD operations for students

    @GetMapping("/allstudents")
    public ResponseEntity<Iterable<User>> getAllStudents() {
        Iterable<User> students = userRepository.findAllByRole(Role.STUDENT);
        return ResponseEntity.ok(students);
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

    @GetMapping("/check-email/student/{email}")
    public ResponseEntity<Boolean> checkStudentEmailExists(@PathVariable String email) {
        Optional<User> student = userRepository.findByEmailAndRole(email, Role.STUDENT);
        return ResponseEntity.ok(student.isPresent());
    }

    @PutMapping("/students/update/{id}")
    public ResponseEntity<User> updateStudent(@RequestBody User student, @PathVariable Long id) {
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
            return ResponseEntity.ok(repoStudent);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/students/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        User student = userRepository.findById(id).orElse(null);
        if (student != null && student.getRole() == Role.STUDENT) {
            userRepository.delete(student);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
