package com.example.aswe.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.aswe.demo.models.Student;
import com.example.aswe.demo.repositry.StudentRepository;

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
@RequestMapping("student")

public class StudentControllers {
    @Autowired
    private StudentRepository studentRepository;
    
    @GetMapping("") 
    public ResponseEntity<?> getUser() {
    Collection<Student> users = this.studentRepository.findAll();
    if (!users.isEmpty()) {
        // Convert the collection of users to a list before returning
        List<Student> userList = new ArrayList<>(users);
        return ResponseEntity.ok(userList);
    } else {
        return ResponseEntity.notFound().build();
    }
    }
    
    @GetMapping("")
    public ModelAndView getUsers() {
        ModelAndView mav = new ModelAndView("list-Student.html");
        List<Student>users = this.studentRepository.findAll();
        mav.addObject("users", users);
        return mav;
    }

    @GetMapping("Registration")
    public ModelAndView addUser() {
        ModelAndView mav = new ModelAndView("Registration.html");
        Student newUser = new Student();
        mav.addObject("Student", newUser); ////////
        return mav;
    }
    
    @PostMapping("Registration")
    public String saveUser(@ModelAttribute Student Student) {
        // String encodedPassword = BCrypt.hashpw(Student.getPassword(), BCrypt.gensalt(12));
        // Student.setPassword(encodedPassword);
        this.studentRepository.save(Student);
        return "Added";
    }

    @GetMapping("Login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login.html");
        Student newstudent = new Student();
        mav.addObject("Student", newstudent);
        return mav;
    }
    
    @PostMapping("Login")
    public String LoginProcess(@RequestParam("email") String email, @RequestParam("password") String password) {
    //    Student dbStudent= this.studentRepository.findByEmail(email);
    //    if(dbStudent == null) return "Login Failed";
    //    Boolean isPasswordMatched = this.checkpw(password, dbStudent.getPassword());
    //    if(isPasswordMatched)
    //     return "Welcome " + dbStudent.getFname();
    //     else
    //     return "Login Failed";



    Student dbStudent = this.studentRepository.findByEmail(email);
    if (dbStudent == null) {
        return "Login Failed";
    }

    if (dbStudent.getPassword().equals(password)) {
        return "Welcome " + dbStudent.getFname();
    } else {
        return "Login Failed";
    }

    }

    @GetMapping("Edit/{id}")
public ModelAndView editAccount(@PathVariable("id") Long id) {
    ModelAndView mav = new ModelAndView("Edit.html");
    Student student = studentRepository.findById(id).orElse(null);
    if (student == null) {
        // Handle student not found
        mav.addObject("error", "Student not found");
        return mav;
    }
    mav.addObject("student", student);
    return mav;
}

    @PostMapping("Edit")
public String editAccount(@ModelAttribute Student student) {
    Student dbStudent = studentRepository.findById(student.getId()).orElse(null);
    if (dbStudent == null) {
        return "Account not found";
    }

    dbStudent.setFname(student.getFname());
    dbStudent.setLname(student.getLname());
    dbStudent.setEmail(student.getEmail());
    dbStudent.setGender(student.getGender());
    dbStudent.setDob(student.getDob());
    dbStudent.setPassword(student.getPassword()); // Assuming password is plain text for demonstration purposes
    dbStudent.setPhone(student.getPhone());
    dbStudent.setUsertype(student.getUsertype());

    studentRepository.save(dbStudent);
    return "Account updated";
}


@PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Student user) {
        try {
            Student savedUser = studentRepository.save(user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("update/{id}")
public ResponseEntity<?> Update(@PathVariable Long id, @RequestBody Student updatedStudent) {
    try {
        Optional<Student> userOptional = studentRepository.findById(id);
        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Student existingStudent = userOptional.get();
        // Update existing user with the fields from updatedUser
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setPassword(updatedStudent.getPassword());
        // Update other fields as needed

        Student savedUser = studentRepository.save(existingStudent);
        return ResponseEntity.ok(savedUser);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

     @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
    try {
        studentRepository.deleteById(id);
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
