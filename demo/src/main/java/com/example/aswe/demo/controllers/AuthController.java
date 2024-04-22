package com.example.aswe.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.aswe.demo.models.Student;
import com.example.aswe.demo.repository.StudentRepository;

import jakarta.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class AuthController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/lll")
    public ResponseEntity<?> postMethodName(@RequestParam("email") String email,
        @RequestParam("password") String password,HttpSession session) {
        
        Student student = this.studentRepository.findByEmail(email);

        if (student != null && BCrypt.checkpw(password, student.getPassword())) {
            session.setAttribute("name",student.getGender());
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build() ;
        }
    }   
    @GetMapping("/logout")
    public String getMethodName(HttpSession session) {

        String x=(String) session.getAttribute("name");
        session.invalidate();
        return x;
    }
    
}
