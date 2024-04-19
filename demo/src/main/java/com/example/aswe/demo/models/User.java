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
    private String FirstName;
    private String LastName;
    private String gender;
    private LocalDate DOB;
    private String Phone;
    private String email;
    private String password;
    private String UserType;

    public User() {
    }

    public User(Long Id, String FirstName, String LastName, String gender, LocalDate DOB, String Phone, String email, String password, String UserType) {
        this.Id = Id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.gender = gender;
        this.DOB = DOB;
        this.Phone = Phone;
        this.email = email;
        this.password = password;
        this.UserType = UserType;
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getFirstName() {
        return this.FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return this.LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDOB() {
        return this.DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public String getPhone() {
        return this.Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
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

    public String getUserType() {
        return this.UserType;
    }

    public void setUserType(String UserType) {
        this.UserType = UserType;
    }

    public User Id(Long Id) {
        setId(Id);
        return this;
    }

    public User FirstName(String FirstName) {
        setFirstName(FirstName);
        return this;
    }

    public User LastName(String LastName) {
        setLastName(LastName);
        return this;
    }

    public User gender(String gender) {
        setGender(gender);
        return this;
    }

    public User DOB(LocalDate DOB) {
        setDOB(DOB);
        return this;
    }

    public User Phone(String Phone) {
        setPhone(Phone);
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

    public User UserType(String UserType) {
        setUserType(UserType);
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
        return Objects.equals(Id, user.Id) && Objects.equals(FirstName, user.FirstName) && Objects.equals(LastName, user.LastName) && Objects.equals(gender, user.gender) && Objects.equals(DOB, user.DOB) && Objects.equals(Phone, user.Phone) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(UserType, user.UserType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, FirstName, LastName, gender, DOB, Phone, email, password, UserType);
    }

    @Override
    public String toString() {
        return "{" +
            " Id='" + getId() + "'" +
            ", FirstName='" + getFirstName() + "'" +
            ", LastName='" + getLastName() + "'" +
            ", gender='" + getGender() + "'" +
            ", DOB='" + getDOB() + "'" +
            ", Phone='" + getPhone() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", UserType='" + getUserType() + "'" +
            "}";
    }


}
