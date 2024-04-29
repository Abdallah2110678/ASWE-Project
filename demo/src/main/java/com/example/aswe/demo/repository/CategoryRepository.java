package com.example.aswe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aswe.demo.models.Category;




public interface CategoryRepository extends JpaRepository <Category, Long> {

}
