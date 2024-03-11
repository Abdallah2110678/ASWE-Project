package com.example.aswe.demo.models;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity

public class Student {
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


    public Student() {
    }

    public Student(Long id, String Fname, String Lname, String password, String gender, LocalDate dob, String email, String phone, String usertype) {
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

    public Student id(Long id) {
        setId(id);
        return this;
    }

    public Student Fname(String Fname) {
        setFname(Fname);
        return this;
    }

    public Student Lname(String Lname) {
        setLname(Lname);
        return this;
    }

    public Student password(String password) {
        setPassword(password);
        return this;
    }

    public Student gender(String gender) {
        setGender(gender);
        return this;
    }

    public Student dob(LocalDate dob) {
        setDob(dob);
        return this;
    }

    public Student email(String email) {
        setEmail(email);
        return this;
    }

    public Student phone(String phone) {
        setPhone(phone);
        return this;
    }

    public Student usertype(String usertype) {
        setUsertype(usertype);
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
        return Objects.equals(id, student.id) && Objects.equals(Fname, student.Fname) && Objects.equals(Lname, student.Lname) && Objects.equals(password, student.password) && Objects.equals(gender, student.gender) && Objects.equals(dob, student.dob) && Objects.equals(email, student.email) && Objects.equals(phone, student.phone) && Objects.equals(usertype, student.usertype);
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