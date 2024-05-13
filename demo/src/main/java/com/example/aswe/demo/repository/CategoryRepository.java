package com.example.aswe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.aswe.demo.models.Category;
@Repository
@Component
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
