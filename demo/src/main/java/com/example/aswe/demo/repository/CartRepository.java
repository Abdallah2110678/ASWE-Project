package com.example.aswe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

import com.example.aswe.demo.models.Cart;


public interface CartRepository extends JpaRepository <Cart, Long>{
    List<Cart> findByUserId(Long userId);
    Optional<Cart> findByUserIdAndCourseId(Long userId, Long courseId);
}
