package com.example.aswe.demo.TestControllers;

import com.example.aswe.demo.controllers.UserController;
import com.example.aswe.demo.models.*;
import com.example.aswe.demo.repository.UserRepository;
import com.example.aswe.demo.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationService authService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        User user = new User();
        AuthenticationResponse response = new AuthenticationResponse("token", user);
        when(authService.register(user)).thenReturn(response);

        ResponseEntity<AuthenticationResponse> result = userController.register(user);

        assertEquals(ResponseEntity.ok(response), result);
    }

    @Test
    void testLogin() {
        User user = new User();
        AuthenticationResponse response = new AuthenticationResponse("token", user);
        when(authService.authenticate(user)).thenReturn(response);

        ResponseEntity<AuthenticationResponse> result = userController.login(user);

        assertEquals(ResponseEntity.ok(response), result);
    }

    // @Test
    // void testGetAllInstructors() {
    //     User instructor = new User();
    //     instructor.setRole(Role.INSTRUCTOR);
    //     List<User> instructors = Collections.singletonList(instructor);
    //     when(userRepository.findAllByRole(Role.INSTRUCTOR)).thenReturn(instructors);

    //     List<HashMap<String, Object>> result = userController.getAllInstructors();

    //     assertNotNull(result);
    //     assertEquals(1, result.size());
    //     assertEquals(instructor.toHashMap(), result.get(0));
    // }

    @Test
    void testGetInstructor() {
        User instructor = new User();
        instructor.setRole(Role.INSTRUCTOR);
        when(userRepository.findById(1L)).thenReturn(Optional.of(instructor));

        ResponseEntity<User> result = userController.getInstructor(1L);

        assertEquals(ResponseEntity.ok(instructor), result);
    }

    @Test
    void testCreateInstructor() {
        User instructor = new User();
        instructor.setRole(Role.INSTRUCTOR);
        instructor.setPassword("password");
        User savedInstructor = new User();
        when(userRepository.save(any(User.class))).thenReturn(savedInstructor);

        ResponseEntity<User> result = userController.createInstructor(instructor);

        assertEquals(ResponseEntity.ok(savedInstructor), result);
        verify(passwordEncoder).encode("password");
    }

    @Test
    void testUpdateInstructor() {
        User instructor = new User();
        instructor.setRole(Role.INSTRUCTOR);
        instructor.setPassword("password");
        User repoInstructor = new User();
        repoInstructor.setRole(Role.INSTRUCTOR);
        when(userRepository.findById(1L)).thenReturn(Optional.of(repoInstructor));
        when(userRepository.save(any(User.class))).thenReturn(repoInstructor);

        ResponseEntity<User> result = userController.updateInstructor(instructor, 1L);

        assertEquals(ResponseEntity.ok(repoInstructor), result);
    }

    @Test
    void testDeleteInstructor() {
        User instructor = new User();
        instructor.setRole(Role.INSTRUCTOR);
        when(userRepository.findById(1L)).thenReturn(Optional.of(instructor));

        ResponseEntity<Void> result = userController.deleteInstructor(1L);

        assertEquals(ResponseEntity.noContent().build(), result);
        verify(userRepository).delete(instructor);
    }

    // @Test
    // void testCheckInstructorEmailExists() {
    //     User instructor = new User();
    //     instructor.setRole(Role.INSTRUCTOR);
    //     when(userRepository.findByEmailAndRole("email", Role.INSTRUCTOR)).thenReturn(Optional.of(instructor));

    //     ResponseEntity<Boolean> result = userController.checkInstructorEmailExists("email");

    //     assertEquals(ResponseEntity.ok(true), result);
    // }

    @Test
    void testGetAllStudents() {
        User student = new User();
        student.setRole(Role.STUDENT);
        List<User> students = Collections.singletonList(student);
        when(userRepository.findAllByRole(Role.STUDENT)).thenReturn(students);

        List<User> result = userController.getAllStudents();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(student, result.get(0));
    }

    @Test
    void testGetStudent() {
        User student = new User();
        student.setRole(Role.STUDENT);
        when(userRepository.findById(1L)).thenReturn(Optional.of(student));

        ResponseEntity<User> result = userController.getStudent(1L);

        assertEquals(ResponseEntity.ok(student), result);
    }

    @Test
    void testCreateStudent() {
        User student = new User();
        student.setRole(Role.STUDENT);
        student.setPassword("password");
        User savedStudent = new User();
        when(userRepository.save(any(User.class))).thenReturn(savedStudent);

        ResponseEntity<User> result = userController.createStudent(student);

        assertEquals(ResponseEntity.ok(savedStudent), result);
        verify(passwordEncoder).encode("password");
    }

    // @Test
    // void testCheckStudentEmailExists() {
    //     User student = new User();
    //     student.setRole(Role.STUDENT);
    //     when(userRepository.findByEmailAndRole("email", Role.STUDENT)).thenReturn(Optional.of(student));

    //     ResponseEntity<Boolean> result = userController.checkStudentEmailExists("email");

    //     assertEquals(ResponseEntity.ok(true), result);
    // }

    // @Test
    // void testUpdateStudent() {
    //     User student = new User();
    //     student.setRole(Role.STUDENT);
    //     student.setPassword("password");
    //     User repoStudent = new User();
    //     repoStudent.setRole(Role.STUDENT);
    //     when(userRepository.findById(1L)).thenReturn(Optional.of(repoStudent));
    //     when(userRepository.save(any(User.class))).thenReturn(repoStudent);

    //     ResponseEntity<User> result = userController.updateStudent(student, 1L);

    //     assertEquals(ResponseEntity.ok(repoStudent), result);
    //     verify(passwordEncoder).encode("password");
    // }

    @Test
    void testDeleteStudent() {
        User student = new User();
        student.setRole(Role.STUDENT);
        when(userRepository.findById(1L)).thenReturn(Optional.of(student));

        ResponseEntity<Void> result = userController.deleteStudent(1L);

        assertEquals(ResponseEntity.noContent().build(), result);
        verify(userRepository).delete(student);
    }
}
