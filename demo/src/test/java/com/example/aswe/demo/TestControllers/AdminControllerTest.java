// package com.example.aswe.demo.TestControllers;

// import com.example.aswe.demo.controllers.AdminController;
// import com.example.aswe.demo.models.Instructor;
// import com.example.aswe.demo.models.Student;
// import com.example.aswe.demo.repository.InstructorRepository;
// import com.example.aswe.demo.repository.StudentRepository;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.http.ResponseEntity;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.*;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// public class AdminControllerTest {

//     @InjectMocks
//     private AdminController adminController;

//     @Mock
//     private InstructorRepository instructorRepository;

//     @Mock
//     private StudentRepository studentRepository;

//     public AdminControllerTest() {
//         MockitoAnnotations.openMocks(this);
//     }

//     @Test
//     public void testGetAllInstructors() {
//         List<Instructor> instructors = new ArrayList<>();
//         instructors.add(new Instructor());
//         when(instructorRepository.findAll()).thenReturn(instructors);

//         ResponseEntity<?> response = adminController.getAllInstructors();

//         assertEquals(ResponseEntity.ok(instructors), response);
//     }

//     @Test
//     public void testCreateNewInstructor() {
//         Instructor instructor = new Instructor();
//         instructor.setPassword("password");
//         Instructor savedInstructor = new Instructor();
//         when(instructorRepository.save(any(Instructor.class))).thenReturn(savedInstructor);

//         ResponseEntity<?> response = adminController.createNewInstructor(instructor);

//         assertEquals(ResponseEntity.ok(savedInstructor), response);
//     }

//     @Test
//     public void testGetSpecificInstructor() {
//         Instructor instructor = new Instructor();
//         when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));

//         ResponseEntity<Optional<Instructor>> response = adminController.getSpecificInstructor(1L);

//         assertEquals(ResponseEntity.ok(Optional.of(instructor)), response);
//     }

//     @Test
//     public void testCheckInstructorEmailExists() {
//         String email = "test@example.com";
//         when(instructorRepository.existsByEmail(email)).thenReturn(true);

//         ResponseEntity<?> response = adminController.checkInstructorEmailExists(email);

//         assertEquals(ResponseEntity.ok(true), response);
//     }

//     @Test
//     public void testUpdateInstructorData() {
//         Instructor existingInstructor = new Instructor();
//         existingInstructor.setPassword("oldPassword");
//         Instructor updatedInstructor = new Instructor();
//         updatedInstructor.setPassword("newPassword");
//         when(instructorRepository.findById(1L)).thenReturn(Optional.of(existingInstructor));
//         when(instructorRepository.save(any(Instructor.class))).thenReturn(updatedInstructor);

//         ResponseEntity<?> response = adminController.updateInstructorData(1L, updatedInstructor);

//         assertEquals(ResponseEntity.ok(updatedInstructor), response);
//     }

//     @Test
//     public void testDeleteInstructor() {
//         doNothing().when(instructorRepository).deleteById(1L);

//         ResponseEntity<?> response = adminController.deleteInstructor(1L);

//         assertEquals(ResponseEntity.ok().build(), response);
//     }

//     @Test
//     public void testGetAllStudents() {
//         List<Student> students = new ArrayList<>();
//         students.add(new Student());
//         when(studentRepository.findAll()).thenReturn(students);

//         ResponseEntity<?> response = adminController.getAllStudents();

//         assertEquals(ResponseEntity.ok(students), response);
//     }

//     @Test
//     public void testCreateNewStudent() {
//         Student student = new Student();
//         student.setPassword("password");
//         Student savedStudent = new Student();
//         when(studentRepository.save(any(Student.class))).thenReturn(savedStudent);

//         ResponseEntity<?> response = adminController.createNewStudent(student);

//         assertEquals(ResponseEntity.ok(savedStudent), response);
//     }

//     @Test
//     public void testGetSpecificStudent() {
//         Student student = new Student();
//         when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

//         ResponseEntity<Optional<Student>> response = adminController.getSpecificStudent(1L);

//         assertEquals(ResponseEntity.ok(Optional.of(student)), response);
//     }

//     @Test
//     public void testUpdateStudentData() {
//         Student existingStudent = new Student();
//         existingStudent.setPassword("oldPassword");
//         Student updatedStudent = new Student();
//         updatedStudent.setPassword("newPassword");
//         when(studentRepository.findById(1L)).thenReturn(Optional.of(existingStudent));
//         when(studentRepository.save(any(Student.class))).thenReturn(updatedStudent);

//         ResponseEntity<?> response = adminController.updateStudentData(1L, updatedStudent);

//         assertEquals(ResponseEntity.ok(updatedStudent), response);
//     }

//     @Test
//     public void testDeleteStudent() {
//         doNothing().when(studentRepository).deleteById(1L);

//         ResponseEntity<?> response = adminController.deleteStudent(1L);

//         assertEquals(ResponseEntity.ok().build(), response);
//     }

//     @Test
//     public void testCheckStudentEmailExists() {
//         String email = "test@student.com";
//         when(studentRepository.existsByEmail(email)).thenReturn(true);

//         ResponseEntity<?> response = adminController.checkStudentEmailExists(email);

//         assertEquals(ResponseEntity.ok(true), response);
//     }
// }
