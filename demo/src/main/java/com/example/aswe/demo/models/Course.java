package com.example.aswe.demo.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Entity
public class Course {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private Double price;



    @ManyToOne
    private Instructor instructor;

    @ManyToOne
    private Category category;


    @OneToMany
    private List<CourseMaterial> courseMaterial;



    public Course(Long id, String title, String description, Double price, Instructor instructor, Category category, List<CourseMaterial> courseMaterial) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.instructor = instructor;
        this.category = category;
        this.courseMaterial = courseMaterial;
    }

    public Instructor getInstructor() {
        return this.instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Course instructor(Instructor instructor) {
        setInstructor(instructor);
        return this;
    }
    
  
    public Course() {
    }

    public Course(Long id, String title, String description, Double price, Category category, List<CourseMaterial> courseMaterial) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        
        this.category = category;
        this.courseMaterial = courseMaterial;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<CourseMaterial> getCourseMaterial() {
        return this.courseMaterial;
    }

    public void setCourseMaterial(List<CourseMaterial> courseMaterial) {
        this.courseMaterial = courseMaterial;
    }

    public Course id(Long id) {
        setId(id);
        return this;
    }

    public Course title(String title) {
        setTitle(title);
        return this;
    }

    public Course description(String description) {
        setDescription(description);
        return this;
    }

    public Course price(Double price) {
        setPrice(price);
        return this;
    }

  

    public Course category(Category category) {
        setCategory(category);
        return this;
    }

    public Course courseMaterial(List<CourseMaterial> courseMaterial) {
        setCourseMaterial(courseMaterial);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Course)) {
            return false;
        }
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(title, course.title) && Objects.equals(description, course.description) && Objects.equals(price, course.price) && Objects.equals(instructor, course.instructor) && Objects.equals(category, course.category) && Objects.equals(courseMaterial, course.courseMaterial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, price, instructor, category, courseMaterial);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", price='" + getPrice() + "'" +
            ", instructor='" + getInstructor() + "'" +
            ", category='" + getCategory() + "'" +
            ", courseMaterial='" + getCourseMaterial() + "'" +
            "}";
    }

}

