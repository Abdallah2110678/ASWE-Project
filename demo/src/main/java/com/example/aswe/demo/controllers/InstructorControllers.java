package com.example.aswe.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.example.aswe.demo.models.Instructor;
import com.example.aswe.demo.repository.InstructorRepository;
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
@RequestMapping("instructor")

public class InstructorControllers {
    @Autowired
    private InstructorRepository instructorRepository;
   
    @GetMapping("") 
    public ResponseEntity<?> getAllInstractors() {
    Collection<Instructor> instructors = this.instructorRepository.findAll();
    if (!instructors.isEmpty()) {
        List<Instructor> instructorList = new ArrayList<>(instructors);
        return ResponseEntity.ok(instructorList);
    } else {
        return ResponseEntity.notFound().build();
    }


}@PostMapping("/create")
public ResponseEntity<?> CreateNewInstractor(@RequestBody Instructor instructor) {
    try {

        String encodedPassword = BCrypt.hashpw(instructor.getPassword(), BCrypt.gensalt(12));
        instructor.setPassword(encodedPassword);
        Instructor savedInstructor = instructorRepository.save(instructor);
        return ResponseEntity.ok(savedInstructor);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}


    @GetMapping("/{id}")
    public Optional<Instructor> getSpesicInstractor(@PathVariable Long id) {
        return this.instructorRepository.findById(id);
    }   

     @GetMapping("/checkEmail")
    public ResponseEntity<?> checkEmailExists(@RequestParam String email) {
        boolean exists = instructorRepository.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }


    @PutMapping("update/{id}")
public ResponseEntity<?> updateInstractorData(@PathVariable Long id, @RequestBody Instructor updatedInstructor) {
    try {
        Optional<Instructor> InstructorOptional = instructorRepository.findById(id);
        if (!InstructorOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        String encodedPassword = BCrypt.hashpw(updatedInstructor.getPassword(), BCrypt.gensalt(12));
            updatedInstructor.setPassword(encodedPassword);
    
            Instructor savedUser = instructorRepository.save(updatedInstructor);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
}

  @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteInstractor(@PathVariable Long id) {
    try {
        instructorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    } catch (EmptyResultDataAccessException ex) {
        // If the user with the specified ID does not exist
        return ResponseEntity.notFound().build();
    } catch (Exception e) {
        // Other unexpected errors
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
} 
}
