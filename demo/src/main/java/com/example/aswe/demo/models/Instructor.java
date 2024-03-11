package com.example.aswe.demo.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class Instructor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Fname;
    private String Lname;
    private String password;
    private String gender;
    private LocalDate dob;
    private String email;
    private String phone;
    private String usertype;


    public Instructor() {
    }

    public Instructor(Long id, String Fname, String Lname, String password, String gender, LocalDate dob, String email, String phone, String usertype) {
        this.id = id;
        this.Fname = Fname;
        this.Lname = Lname;
        this.password = password;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.usertype = usertype;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFname() {
        return this.Fname;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public String getLname() {
        return this.Lname;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsertype() {
        return this.usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public Instructor id(Long id) {
        setId(id);
        return this;
    }

    public Instructor Fname(String Fname) {
        setFname(Fname);
        return this;
    }

    public Instructor Lname(String Lname) {
        setLname(Lname);
        return this;
    }

    public Instructor password(String password) {
        setPassword(password);
        return this;
    }

    public Instructor gender(String gender) {
        setGender(gender);
        return this;
    }

    public Instructor dob(LocalDate dob) {
        setDob(dob);
        return this;
    }

    public Instructor email(String email) {
        setEmail(email);
        return this;
    }

    public Instructor phone(String phone) {
        setPhone(phone);
        return this;
    }

    public Instructor usertype(String usertype) {
        setUsertype(usertype);
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
        return Objects.equals(id, instructor.id) && Objects.equals(Fname, instructor.Fname) && Objects.equals(Lname, instructor.Lname) && Objects.equals(password, instructor.password) && Objects.equals(gender, instructor.gender) && Objects.equals(dob, instructor.dob) && Objects.equals(email, instructor.email) && Objects.equals(phone, instructor.phone) && Objects.equals(usertype, instructor.usertype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Fname, Lname, password, gender, dob, email, phone, usertype);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", Fname='" + getFname() + "'" +
            ", Lname='" + getLname() + "'" +
            ", password='" + getPassword() + "'" +
            ", gender='" + getGender() + "'" +
            ", dob='" + getDob() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", usertype='" + getUsertype() + "'" +
            "}";
    }
    
}
