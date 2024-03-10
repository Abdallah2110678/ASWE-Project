package com.example.aswe.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.aswe.demo.models.Student;
import com.example.aswe.demo.repositories.StudentRepository;

import java.time.LocalDate;
import java.util.List;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/Student")

public class StudentControllers {
    @Autowired
    private StudentRepository studentRepository;
    
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

    studentRepository.save(dbStudent);
    return "Account updated";
}






    // @PostMapping("addStudent")
    // public String addStudent(
    //         @RequestParam("id") Long id,
    //         @RequestParam("Fname") String Fname,
    //         @RequestParam("Lname") String Lname,
    //         @RequestParam("password") String password,
    //         @RequestParam("gender") String gender,
    //         @RequestParam("dob") LocalDate dob,
    //         @RequestParam("email") String email) {
    //     Student student = new Student();
    //     student.setId(id);
    //     student.setFname(Fname);
    //     student.setLname(Lname);
    //     String encodedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
    //     student.setPassword(encodedPassword);
    //     student.setGender(gender);
    //     student.setDob(dob);
    //     student.setEmail(email);
    //     this.studentRepository.save(student);
    //     return "Student Added Successfully";
    // }
    

    
}

