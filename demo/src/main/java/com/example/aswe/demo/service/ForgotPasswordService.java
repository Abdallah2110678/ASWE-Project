package com.example.aswe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.aswe.demo.models.User;
import com.example.aswe.demo.repository.UserRepository;
import java.util.Optional;
import java.util.UUID;

@Service
public class ForgotPasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public void initiatePasswordReset(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String resetToken = generateResetToken();

            // Save the reset token to the user entity
            user.setResetToken(resetToken);
            userRepository.save(user);

            // Send the password reset email
            sendPasswordResetEmail(user.getEmail(), resetToken);
        } else {
            // User not found, handle error
        }
    }

    private String generateResetToken() {
        // Generate a unique reset token (e.g., UUID)
        return UUID.randomUUID().toString();
    }

    private void sendPasswordResetEmail(String email, String resetToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, click the following link: http://localhost:3000/reset-password?token=" + resetToken);
        javaMailSender.send(message);
    }
}
