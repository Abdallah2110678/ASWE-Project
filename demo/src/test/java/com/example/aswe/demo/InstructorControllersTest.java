package com.example.aswe.demo;

import com.example.aswe.demo.controllers.InstructorControllers;
import com.example.aswe.demo.models.Instructor;
import com.example.aswe.demo.repository.InstructorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringJUnitConfig
public class InstructorControllersTest {

    @InjectMocks
    private InstructorControllers instructorControllers;

    @Mock
    private InstructorRepository instructorRepository;

    public InstructorControllersTest() {
        MockitoAnnotations.openMocks(this);
    }

    // @Test
    // public void testGetAllInstructors() {
    //     List<Instructor> instructors = new ArrayList<>();
    //     instructors.add(new Instructor());
    //     when(instructorRepository.findAll()).thenReturn(instructors);

    //     ResponseEntity<?> response = instructorControllers.getAllInstractors();

    //     assertEquals(ResponseEntity.ok(instructors), response);
    // }

    // @Test
    // public void testCreateNewInstructor() {
    //     Instructor instructor = new Instructor();
    //     instructor.setPassword("password");
    //     Instructor savedInstructor = new Instructor();
    //     when(instructorRepository.save(any(Instructor.class))).thenReturn(savedInstructor);

    //     ResponseEntity<?> response = instructorControllers.CreateNewInstractor(instructor);

    //     assertEquals(ResponseEntity.ok(savedInstructor), response);
    // }

    // @Test
    // public void testGetSpecificInstructor() {
    //     Instructor instructor = new Instructor();
    //     when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));

    //     Optional<Instructor> foundInstructor = instructorControllers.getSpesicInstractor(1L);

    //     assertEquals(Optional.of(instructor), foundInstructor);
    // }

    // @Test
    // public void testCheckEmailExists() {
    //     String email = "test@example.com";
    //     when(instructorRepository.existsByEmail(email)).thenReturn(true);

    //     ResponseEntity<?> response = instructorControllers.checkEmailExists(email);

    //     assertEquals(ResponseEntity.ok(true), response);
    // }

    // @Test
    // public void testUpdateInstructorData() {
    //     Instructor existingInstructor = new Instructor();
    //     existingInstructor.setPassword("oldPassword");
    //     Instructor updatedInstructor = new Instructor();
    //     updatedInstructor.setPassword("newPassword");
    //     when(instructorRepository.findById(1L)).thenReturn(Optional.of(existingInstructor));
    //     when(instructorRepository.save(any(Instructor.class))).thenReturn(updatedInstructor);

    //     ResponseEntity<?> response = instructorControllers.updateInstractorData(1L, updatedInstructor);

    //     assertEquals(ResponseEntity.ok(updatedInstructor), response);
    // }

    // @Test
    // public void testDeleteInstructor() {
    //     doNothing().when(instructorRepository).deleteById(1L);
    //     ResponseEntity<?> response = instructorControllers.deleteInstractor(1L);
    //     assertEquals(ResponseEntity.ok().build(), response);
    // }
}
