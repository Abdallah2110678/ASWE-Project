package com.example.aswe.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.aswe.demo.models.Category;
import com.example.aswe.demo.repository.CategoryRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Autowired
    public DataLoader(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryRepository.save(new Category("Computer Science"));
        categoryRepository.save(new Category("Engineering"));
        categoryRepository.save(new Category("UI & UX Design"));
        categoryRepository.save(new Category("Quantum Computing"));
        categoryRepository.save(new Category("Health & Fitness"));
        categoryRepository.save(new Category("Marketing"));
        categoryRepository.save(new Category("Graphic Design"));
        categoryRepository.save(new Category("Music"));
        categoryRepository.save(new Category("Business Administration"));
        categoryRepository.save(new Category("Blockchain"));
        categoryRepository.save(new Category("Art & Design"));
        categoryRepository.save(new Category("History"));
        categoryRepository.save(new Category("Literature"));
        categoryRepository.save(new Category("Mathematics"));
        categoryRepository.save(new Category("Philosophy"));
        categoryRepository.save(new Category("Psychology"));
        categoryRepository.save(new Category("Sociology"));
        categoryRepository.save(new Category("Economics"));
        categoryRepository.save(new Category("Political Science"));
        categoryRepository.save(new Category("Languages"));
        categoryRepository.save(new Category("Geography"));
        categoryRepository.save(new Category("Physics"));
        categoryRepository.save(new Category("Medicine"));
        categoryRepository.save(new Category("Dentistry"));
        categoryRepository.save(new Category("Pharmacy"));
    }
}
