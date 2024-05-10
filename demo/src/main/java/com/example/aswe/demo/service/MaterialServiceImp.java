package com.example.aswe.demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.aswe.demo.models.CourseMaterial;
import com.example.aswe.demo.repository.CourseMaterialRepository;

@Service
public class MaterialServiceImp implements MaterialService {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Override
    public CourseMaterial uploadCourseMaterial(String path, MultipartFile file) throws IOException {

        CourseMaterial courseMaterial = new CourseMaterial();
        String fileName = file.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        String finalName = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));
        String filePath = path + File.separator + finalName;
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));
        courseMaterial.setVideoFileName(finalName);
        return courseMaterial;
    }

    @Override
    public InputStream getCourseMaterial(String path, String fileName, Long id) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }
}
