package com.example.aswe.demo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.aswe.demo.models.Role;
import com.example.aswe.demo.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = :role")
    int countUsersByRole(Role role);

    Optional<User> findByEmail(String email);

    List<User> findAllByRole(Role role);

    Optional<User> findById(Long id);

    // Optional<User> findByResetToken(String resetToken);
}
