package  com.example.aswe.demo.models;

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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class CourseMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String videoFileName;
    private String title;

    @ManyToOne
    @JsonBackReference
    private Course course;
}
