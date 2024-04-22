package com.example.aswe.demo.models;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;

@Entity
public class Instructor extends User {


    private String Expertise_area;


    public Instructor() {
    }

    public Instructor(Long Id, String FirstName, String LastName, String gender, LocalDate DOB, String Phone, String Email, String password,String UserType,String Expertise_area) {
        super(Id,FirstName, LastName, gender, DOB, Phone, Email, password,UserType);
        this.Expertise_area=Expertise_area;
    }

    public Instructor(String Expertise_area) {
        this.Expertise_area = Expertise_area;
    }

    public String getExpertise_area() {
        return this.Expertise_area;
    }

    public void setExpertise_area(String Expertise_area) {
        this.Expertise_area = Expertise_area;
    }

    public Instructor Expertise_area(String Expertise_area) {
        setExpertise_area(Expertise_area);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Instructor)) {
            return false;
        }
        Instructor instructor = (Instructor) o;
        return Objects.equals(Expertise_area, instructor.Expertise_area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Expertise_area);
    }
    

    @Override
    public String toString() {
        return "{" +
            " Expertise_area='" + getExpertise_area() + "'" +
            "}";
    }

}
