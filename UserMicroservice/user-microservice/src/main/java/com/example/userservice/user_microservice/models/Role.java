package com.example.userservice.user_microservice.models;

public enum Role {
    ADMIN("admin"),
    INSTRUCTOR("instructor"),
    STUDENT("student");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getValue() {
        return role;
    }
}
