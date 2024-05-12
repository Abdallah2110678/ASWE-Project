// package com.example.aswe.demo.service;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// import com.example.aswe.demo.models.Order; // Import the Order model class
// import com.example.aswe.demo.response.PaymentResponse;
// import com.stripe.Stripe;
// import com.stripe.exception.StripeException;
// import com.stripe.model.checkout.Session;
// import com.stripe.param.checkout.SessionCreateParams;

// @Service
// public class PaymentServiceImpl implements PaymentService {

//     @Value("${stripe.secret.key}")
//     private String stripeSecretKey;

//     @Value("${http://localhost:3000/}")
//     private String websiteUrl; 

//     @Override
//     public PaymentResponse createPaymentLink(Order order) {
//         Stripe.apiKey = stripeSecretKey;

//         try {
//             SessionCreateParams params =
//                 SessionCreateParams.builder()
//                     .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
//                     .setMode(SessionCreateParams.Mode.PAYMENT)
//                     .setSuccessUrl(websiteUrl + "/success.html")
//                     .setCancelUrl(websiteUrl + "/cancel.html")
//                     .addLineItem(
//                         SessionCreateParams.LineItem.builder()
//                             .setQuantity(1L)
//                             .setPriceData(
//                                 SessionCreateParams.LineItem.PriceData.builder()
//                                     .setCurrency("usd")
//                                     .setUnitAmount((long) order.getTotalAmount()*100)
//                                     .setProductData(
//                                         SessionCreateParams.LineItem.PriceData.ProductData.builder()
//                                             .setName("Course")
//                                             .build()
//                                     )
//                                     .build()
//                             )
//                             .build()
//                     )
//                     .build();

//             Session session = Session.create(params);

//             PaymentResponse response = new PaymentResponse();
//             response.setPaymentLink(session.getUrl());
//             response.setSessionId(session.getId());
//             return response;
//         } catch (StripeException e) {
//             e.printStackTrace();
//             return null; 
//         }
//     }
// }
