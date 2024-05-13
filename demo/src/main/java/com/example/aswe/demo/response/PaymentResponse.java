// package com.example.aswe.demo.response;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Getter
// @Setter
// @AllArgsConstructor
// @NoArgsConstructor
// @Builder
// @Entity
// @Data
// public class PaymentResponse {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY) 
//     private String sessionId;
//     private String paymentLink;

//     public String getSessionId() {
//         return sessionId;
//     }

//     public void setSessionId(String sessionId) {
//         this.sessionId = sessionId;
//     }

//     public String getPaymentLink() {
//         return paymentLink;
//     }

//     public void setPaymentLink(String paymentLink) {
//         this.paymentLink = paymentLink;
//     }
// }
