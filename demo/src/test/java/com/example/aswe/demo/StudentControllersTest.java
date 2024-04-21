package com.example.aswe.demo;

import com.example.aswe.demo.controllers.StudentControllers;
import com.example.aswe.demo.models.Student;
import com.example.aswe.demo.repository.StudentRepository;
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
public class StudentControllersTest {

    @InjectMocks
    private StudentControllers studentControllers;

    @Mock
    private StudentRepository studentRepository;

    public StudentControllersTest() {
        MockitoAnnotations.openMocks(this);
    }

    // @Test
    // public void testGetAllStudents() {
    //     List<Student> students = new ArrayList<>();
    //     students.add(new Student());
    //     when(studentRepository.findAll()).thenReturn(students);

    //     ResponseEntity<?> response = studentControllers.getAllStudents();

    //     assertEquals(ResponseEntity.ok(students), response);
    // }

    // @Test
    // public void testCreateNewStudent() {
    //     Student student = new Student();
    //     student.setPassword("password");
    //     Student savedStudent = new Student();
    //     when(studentRepository.save(any(Student.class))).thenReturn(savedStudent);

    //     ResponseEntity<?> response = studentControllers.CreateNewStudent(student);

    //     assertEquals(ResponseEntity.ok(savedStudent), response);
    // }

    // @Test
    // public void testGetSpecificStudent() {
    //     Student student = new Student();
    //     when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

    //     Optional<Student> foundStudent = studentControllers.getSpesicStudent(1L);

    //     assertEquals(Optional.of(student), foundStudent);
    // }

    // @Test
    // public void testCheckEmailExists() {
    //     String email = "test@example.com";
    //     when(studentRepository.existsByEmail(email)).thenReturn(true);

    //     ResponseEntity<?> response = studentControllers.checkEmailExists(email);

    //     assertEquals(ResponseEntity.ok(true), response);
    // }

    // @Test
    // public void testUpdateStudentData() {
    //     Student existingStudent = new Student();
    //     existingStudent.setPassword("oldPassword");
    //     Student updatedStudent = new Student();
    //     updatedStudent.setPassword("newPassword");
    //     when(studentRepository.findById(1L)).thenReturn(Optional.of(existingStudent));
    //     when(studentRepository.save(any(Student.class))).thenReturn(updatedStudent);

    //     ResponseEntity<?> response = studentControllers.UpdateStudentData(1L, updatedStudent);

    //     assertEquals(ResponseEntity.ok(updatedStudent), response);
    // }

    // @Test
    // public void testDeleteStudent() {
    //     doNothing().when(studentRepository).deleteById(1L);

    //     ResponseEntity<?> response = studentControllers.deleteStudent(1L);

    //     assertEquals(ResponseEntity.ok().build(), response);
    // }
}
