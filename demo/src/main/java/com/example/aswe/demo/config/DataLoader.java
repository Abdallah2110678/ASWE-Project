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
        categoryRepository.save(new Category("DevOps"));
        categoryRepository.save(new Category("Big Data"));
        categoryRepository.save(new Category("Natural Language Processing (NLP)"));
        categoryRepository.save(new Category("Robotics"));
        categoryRepository.save(new Category("Game Development"));
        categoryRepository.save(new Category("Cloud Computing"));
        categoryRepository.save(new Category("UI & UX Design"));
        categoryRepository.save(new Category("Virtual Reality (VR)"));
        categoryRepository.save(new Category("Augmented Reality (AR)"));
        categoryRepository.save(new Category("Quantum Computing"));
        categoryRepository.save(new Category("Software Engineering"));
        categoryRepository.save(new Category("Information Software"));
        categoryRepository.save(new Category("Health & Fitness"));
        categoryRepository.save(new Category("Marketing"));
        categoryRepository.save(new Category("Graphic Design"));
        categoryRepository.save(new Category("Music"));
        categoryRepository.save(new Category("Business Administration"));
        categoryRepository.save(new Category("Web Development"));
        categoryRepository.save(new Category("Data Science"));
        categoryRepository.save(new Category("Machine Learning"));
        categoryRepository.save(new Category("Artificial Intelligence"));
        categoryRepository.save(new Category("Blockchain"));
        categoryRepository.save(new Category("Cybersecurity"));
        categoryRepository.save(new Category("Internet of Things (IoT)"));
        categoryRepository.save(new Category("Mobile Development"));
    }
}
