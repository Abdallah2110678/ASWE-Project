package com.example.aswe.demo.models;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String fname;
    private String lname;
    private String gender;
    private LocalDate dob;
    private String phone;
    private String email;
    private String password;
    private String usertype;

    public User() {
    }

    public User(Long Id, String fname, String lname, String gender, LocalDate dob, String phone, String email, String password, String usertype) {
        this.Id = Id;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.usertype = usertype;
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return this.lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
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

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return this.usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public User Id(Long Id) {
        setId(Id);
        return this;
    }

    public User fname(String fname) {
        setFname(fname);
        return this;
    }

    public User lname(String lname) {
        setLname(lname);
        return this;
    }

    public User gender(String gender) {
        setGender(gender);
        return this;
    }

    public User dob(LocalDate dob) {
        setDob(dob);
        return this;
    }

    public User phone(String phone) {
        setPhone(phone);
        return this;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public User usertype(String usertype) {
        setUsertype(usertype);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(Id, user.Id) && Objects.equals(fname, user.fname) && Objects.equals(lname, user.lname) && Objects.equals(gender, user.gender) && Objects.equals(dob, user.dob) && Objects.equals(phone, user.phone) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(usertype, user.usertype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, fname, lname, gender, dob, phone, email, password, usertype);
    }

    @Override
    public String toString() {
        return "{" +
            " Id='" + getId() + "'" +
            ", fname='" + getFname() + "'" +
            ", lname='" + getLname() + "'" +
            ", gender='" + getGender() + "'" +
            ", dob='" + getDob() + "'" +
            ", phone='" + getPhone() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", usertype='" + getUsertype() + "'" +
            "}";
    }

}
