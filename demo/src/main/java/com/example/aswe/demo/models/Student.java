package com.example.aswe.demo.models;

import java.time.LocalDate;

import java.util.Objects;

import jakarta.persistence.Entity;

@Entity
public class Student extends User {

    private String major;

    public Student(Long Id, String FirstName, String LastName, String gender, LocalDate DOB, String Phone, String Email, String password, String UserType) {
        super(Id,FirstName, LastName, gender, DOB, Phone, Email, password,UserType);
    }


    public Student() {
    }

    public Student(String major) {
        this.major = major;
    }

    public String getMajor() {
        return this.major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Student major(String major) {
        setMajor(major);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(major, student.major);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), major);
    }
    

    @Override
    public String toString() {
        return "{" +
            " major='" + getMajor() + "'" +
            "}";
    }
    
}
