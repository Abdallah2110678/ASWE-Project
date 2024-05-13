// package com.example.aswe.demo.controllers;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.aswe.demo.service.ForgotPasswordService;

// @RestController
// @RequestMapping("/api")
// public class ForgotPasswordController {

//     @Autowired
//     private ForgotPasswordService forgotPasswordService;

//     @PostMapping("/forgot-password")
//     public ResponseEntity<?> forgotPassword(@RequestParam String email) {
//         forgotPasswordService.initiatePasswordReset(email);
//         return ResponseEntity.ok().build();
//     }
// }

