package com.example.aswe.demo.models;

import java.time.LocalDate;

import java.util.Objects;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User {

    public Admin() {
    }

    public Admin(Long id, String FirstName, String LastName, String gender, LocalDate DOB, String Phone, String Email, String password, String usertype) {
        super(id,FirstName, LastName, gender, DOB, Phone, Email, password,usertype);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Admin)) {
            return false;
        }
        Admin admin = (Admin) o;
        return Objects.equals(this, admin);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "{" +
            "}";
    }

}
