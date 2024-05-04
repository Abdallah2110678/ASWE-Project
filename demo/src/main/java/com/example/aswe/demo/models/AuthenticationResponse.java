package com.example.aswe.demo.models;

import java.util.Optional;

public class AuthenticationResponse {
    private String token;
    private User user;

    public AuthenticationResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public AuthenticationResponse(String jwt, Optional<User> user2) {

    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}
