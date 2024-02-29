package com.example.aswe.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class firstApi {

    @GetMapping("api")
    public String getString() {
        return "{\"value\": \"string\"}"; // Return a JSON object with the string value
    }
    
}
