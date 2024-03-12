package com.example.aswe.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.example.aswe.demo.models.Instructor;
import com.example.aswe.demo.repositry.InstructorRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/Instructor")

public class InstructorControllers {
    @Autowired
    private InstructorRepository instructorRepository;
   
    @GetMapping("/path") 
    public ResponseEntity<?> getUsers() {
    Collection<Instructor> instructors = this.instructorRepository.findAll();
    if (!instructors.isEmpty()) {
        // Convert the collection of users to a list before returning
        List<Instructor> userList = new ArrayList<>(instructors);
        return ResponseEntity.ok(userList);
    } else {
        return ResponseEntity.notFound().build();
    }
}
    @GetMapping("/{id}")
    public Optional<Instructor> getUser(@PathVariable Long id) {
        return this.instructorRepository.findById(id);
    }   


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Instructor instructor) {
        try {
            Instructor savedInstructor = instructorRepository.save(instructor);
            return ResponseEntity.ok(savedInstructor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("update/{id}")
public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Instructor updatedInstructor) {
    try {
        Optional<Instructor> InstructorOptional = instructorRepository.findById(id);
        if (!InstructorOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Instructor existingInstructor = InstructorOptional.get();
        // Update existing user with the fields from updatedUser
        existingInstructor.setEmail(updatedInstructor.getEmail());
        existingInstructor.setPassword(updatedInstructor.getPassword());
        // Update other fields as needed

        Instructor savedInstructor = instructorRepository.save(existingInstructor);
        return ResponseEntity.ok(savedInstructor);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

}
