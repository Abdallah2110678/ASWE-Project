package com.example.aswe.demo.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.example.aswe.demo.models.CourseMaterial;

public interface MaterialService {

    CourseMaterial uploadCourseMaterial(String path, MultipartFile file) throws IOException;

    InputStream getCourseMaterial(String path, String fileName, Long id) throws FileNotFoundException;

}
