package com.example.aswe.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.aswe.demo.models.Student;
import com.example.aswe.demo.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("student")

public class StudentControllers {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("")
    public ResponseEntity<?> getAllStudents() {
        Collection<Student> users = this.studentRepository.findAll();
        if (!users.isEmpty()) {
            // Convert the collection of users to a list before returning
            List<Student> userList = new ArrayList<>(users);
            return ResponseEntity.ok(userList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> CreateNewStudent(@RequestBody Student student) {
        try {
            String encodedPassword = BCrypt.hashpw(student.getPassword(), BCrypt.gensalt(12));
            student.setPassword(encodedPassword);
            Student savedStudent = studentRepository.save(student);
            return ResponseEntity.ok(savedStudent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public Optional<Student> getSpesicStudent(@PathVariable long id) {
        return this.studentRepository.findById(id);
    }

    @GetMapping("/checkEmail")
    public ResponseEntity<?> checkEmailExists(@RequestParam String email) {
        boolean exists = studentRepository.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> UpdateStudentData(@PathVariable Long id, @RequestBody Student updatedStudent) {
        try {
            Optional<Student> userOptional = studentRepository.findById(id);
            if (!userOptional.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            
            String encodedPassword = BCrypt.hashpw(updatedStudent.getPassword(), BCrypt.gensalt(12));
            updatedStudent.setPassword(encodedPassword);
           

            Student savedUser = studentRepository.save(updatedStudent);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        try {
            studentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
