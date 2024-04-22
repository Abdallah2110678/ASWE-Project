package com.example.aswe.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.example.aswe.demo.models.Instructor;
import com.example.aswe.demo.models.Student;
import com.example.aswe.demo.repository.InstructorRepository;
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
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/instructors")
    public ResponseEntity<?> getAllInstructors() {
        Collection<Instructor> instructors = this.instructorRepository.findAll();
        if (!instructors.isEmpty()) {
            List<Instructor> instructorList = new ArrayList<>(instructors);
            return ResponseEntity.ok(instructorList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/instructors/create")
    public ResponseEntity<?> createNewInstructor(@RequestBody Instructor instructor) {
        try {
            String encodedPassword = BCrypt.hashpw(instructor.getPassword(), BCrypt.gensalt(12));
            instructor.setPassword(encodedPassword);
            Instructor savedInstructor = instructorRepository.save(instructor);
            return ResponseEntity.ok(savedInstructor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/instructors/{id}")
    public ResponseEntity<Optional<Instructor>> getSpecificInstructor(@PathVariable Long id) {
        return ResponseEntity.ok(this.instructorRepository.findById(id));
    }

    @GetMapping("/instructors/checkEmail")
    public ResponseEntity<?> checkInstructorEmailExists(@RequestParam String email) {
        boolean exists = instructorRepository.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }
    
    @PutMapping("/instructors/update/{id}")
    public ResponseEntity<?> updateInstructorData(@PathVariable Long id, @RequestBody Instructor updatedInstructor) {
        try {
            Optional<Instructor> instructorOptional = instructorRepository.findById(id);
            if (!instructorOptional.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            String encodedPassword = BCrypt.hashpw(updatedInstructor.getPassword(), BCrypt.gensalt(12));
            updatedInstructor.setPassword(encodedPassword);

            Instructor savedInstructor = instructorRepository.save(updatedInstructor);
            return ResponseEntity.ok(savedInstructor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/instructors/delete/{id}")
    public ResponseEntity<?> deleteInstructor(@PathVariable Long id) {
        try {
            instructorRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/students")
    public ResponseEntity<?> getAllStudents() {
        Collection<Student> students = this.studentRepository.findAll();
        if (!students.isEmpty()) {
            List<Student> studentList = new ArrayList<>(students);
            return ResponseEntity.ok(studentList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/students/create")
    public ResponseEntity<?> createNewStudent(@RequestBody Student student) {
        try {
            String encodedPassword = BCrypt.hashpw(student.getPassword(), BCrypt.gensalt(12));
            student.setPassword(encodedPassword);
            Student savedStudent = studentRepository.save(student);
            return ResponseEntity.ok(savedStudent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Optional<Student>> getSpecificStudent(@PathVariable Long id) {
        return ResponseEntity.ok(this.studentRepository.findById(id));
    }

    @PutMapping("/students/update/{id}")
    public ResponseEntity<?> updateStudentData(@PathVariable Long id, @RequestBody Student updatedStudent) {
        try {
            Optional<Student> studentOptional = studentRepository.findById(id);
            if (!studentOptional.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            String encodedPassword = BCrypt.hashpw(updatedStudent.getPassword(), BCrypt.gensalt(12));
            updatedStudent.setPassword(encodedPassword);

            Student savedStudent = studentRepository.save(updatedStudent);
            return ResponseEntity.ok(savedStudent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/students/delete/{id}")
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


    @GetMapping("/students/checkEmail")
    public ResponseEntity<?> checkStudentEmailExists(@RequestParam String email) {
        boolean exists = studentRepository.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }
}
