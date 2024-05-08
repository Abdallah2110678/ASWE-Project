package com.example.aswe.demo.models;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cart {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    
    @ManyToOne
    private User user;
    
    @ManyToOne
    private Course course; 
}
