package com.example.userservice.user_microservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userservice.user_microservice.models.Role;
import com.example.userservice.user_microservice.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findAllByRole(Role role);

    Optional<User> findById(Long id);
}