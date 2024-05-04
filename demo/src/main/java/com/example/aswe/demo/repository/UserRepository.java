package com.example.aswe.demo.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aswe.demo.models.Role;
import com.example.aswe.demo.models.User;

public interface UserRepository extends JpaRepository<User, Long>  {
    Optional<User> findByEmailAndRole(String email, Role role);
    Optional<User> findByEmail(String email);
    List<User> findAllByRole(Role role);
    Optional<User> findById(Long id);
}
