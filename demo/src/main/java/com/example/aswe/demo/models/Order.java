// package com.example.aswe.demo.models;

// import java.util.List;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Getter
// @Setter
// @AllArgsConstructor
// @NoArgsConstructor
// @Builder
// @Entity
// public class Order {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY) 
//     private String orderId;
//     private String customerName;
//     private double amount;
//     private String currency;
//     private String description;
//     private List<Item> items;


//     public String getOrderId() {
//         return orderId;
//     }

//     public void setOrderId(String orderId) {
//         this.orderId = orderId;
//     }

//     public String getCustomerName() {
//         return customerName;
//     }

//     public void setCustomerName(String customerName) {
//         this.customerName = customerName;
//     }

//     public double getAmount() {
//         return amount;
//     }

//     public void setAmount(double amount) {
//         this.amount = amount;
//     }

//     public String getCurrency() {
//         return currency;
//     }

//     public void setCurrency(String currency) {
//         this.currency = currency;
//     }

//     public String getDescription() {
//         return description;
//     }

//     public void setDescription(String description) {
//         this.description = description;
//     }

//     public double getTotalAmount() {
//         double totalAmount = 0.0;
//         for (Item item : items) {
//             totalAmount += item.getPrice();
//         }
//         return totalAmount;
//     }
// }
