package com.example.aswe.demo.TestControllers;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.aswe.demo.controllers.UserController;
import com.example.aswe.demo.models.User;
import com.example.aswe.demo.repository.UserRepository;

class UserControllerTest {

    private UserController userController;
    private UserRepository userRepository;

    // @BeforeEach
    // void setUp() {
    //     userRepository = mock(UserRepository.class);
    //     userController = new UserController();
    //     userController.setUserRepository(userRepository);
    // }

    @Test
    void testGetAllUsers() {
        // Mock data
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());

        // Stubbing repository method
        when(userRepository.findAll()).thenReturn(users);

        // Call the controller method
        List<User> result = userController.getAllUsers();

        // Verify the result
        assertThat(result).isEqualTo(users);
    }

    @Test
    void testDeleteUser() {
        // Stubbing repository method
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));

        // Call the controller method
        ResponseEntity<Void> responseEntity = userController.deleteUser(1L);

        // Verify the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
