// package com.example.aswe.demo.controllers;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.aswe.demo.models.Order;
// import com.example.aswe.demo.response.PaymentResponse;
// import com.example.aswe.demo.service.PaymentService;

// @RestController
// @RequestMapping("/api/orders")
// public class OrderController {

//     @Autowired
//     private PaymentService paymentService;

//     public OrderController(PaymentService paymentService) {
//         this.paymentService = paymentService;
//     }

//     @PostMapping("/create-payment")
//     public ResponseEntity<PaymentResponse> createPayment(@RequestBody Order order) {

//         PaymentResponse response = paymentService.createPaymentLink(order);

//         return new ResponseEntity<>(response, HttpStatus.OK);
//     }
// }
 