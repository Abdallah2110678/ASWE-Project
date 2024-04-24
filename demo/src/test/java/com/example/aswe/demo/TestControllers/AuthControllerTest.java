package com.example.aswe.demo.TestControllers;

import com.example.aswe.demo.controllers.AuthController;
import com.example.aswe.demo.models.Student;
import com.example.aswe.demo.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private HttpSession session;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostMethodNameSuccess() {
        String email = "test@example.com";
        String password = "password";
        Student student = new Student();
        student.setEmail(email);
        student.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)));
        student.setGender("Male");

        when(studentRepository.findByEmail(email)).thenReturn(student);

        ResponseEntity<?> response = authController.postMethodName(email, password, session);

        assertEquals(ResponseEntity.ok(student), response);
        verify(session).setAttribute("name", student.getGender());
    }

    @Test
    void testPostMethodNameFailure() {
        String email = "test@example.com";
        String password = "wrongpassword";

        when(studentRepository.findByEmail(email)).thenReturn(null);

        ResponseEntity<?> response = authController.postMethodName(email, password, session);

        assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    void testGetMethodName() {
        String expectedName = "Male";
        when(session.getAttribute("name")).thenReturn(expectedName);

        String actualName = authController.getMethodName(session);

        assertEquals(expectedName, actualName);
        verify(session).invalidate();
    }
}
