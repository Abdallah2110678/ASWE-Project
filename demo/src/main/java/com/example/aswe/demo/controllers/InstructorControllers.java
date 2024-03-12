package com.example.aswe.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.aswe.demo.models.Instructor;
import com.example.aswe.demo.models.Student;
import com.example.aswe.demo.repository.InstructorRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/Instructor")

public class InstructorControllers {
    @Autowired
    private InstructorRepository instructorRepository;
   
    @GetMapping("/Instructor") 
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

    
    @GetMapping("Edit/{id}")
    public ModelAndView editAccount(@PathVariable("id") Long id) {
    ModelAndView mav = new ModelAndView("Edit.html");
    Instructor instructor = instructorRepository.findById(id).orElse(null);
    if (instructor == null) {
        // Handle instructor not found
        mav.addObject("error", "Instructor not found");
        return mav;
    }
    mav.addObject("instructor", instructor);
    return mav;
}

    @PostMapping("Edit")
public String editAccount(@ModelAttribute Instructor instructor) {
    Instructor dbInstructor = instructorRepository.findById(instructor.getId()).orElse(null);
    if (dbInstructor == null) {
        return "Account not found";
    }

    dbInstructor.setFname(instructor.getFname());
    dbInstructor.setLname(instructor.getLname());
    dbInstructor.setEmail(instructor.getEmail());
    dbInstructor.setGender(instructor.getGender());
    dbInstructor.setDob(instructor.getDob());
    dbInstructor.setPassword(instructor.getPassword()); // Assuming password is plain text for demonstration purposes
    dbInstructor.setPhone(instructor.getPhone());
    dbInstructor.setUsertype(instructor.getUsertype());

    instructorRepository.save(dbInstructor);
    return "Account updated";
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

  @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
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
