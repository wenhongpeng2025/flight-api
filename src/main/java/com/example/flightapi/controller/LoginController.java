package com.example.flightapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flightapi.dto.LoginRequest;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @RequestMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // Implement login logic here
        // For simplicity, let's assume login is successful
        // You can add authentication and authorization logic here

        return ResponseEntity.ok("Login successful");
    }

    public ResponseEntity<String> check(@RequestHeader("Authorization") String authHeader) {

        return ResponseEntity.ok("Check successful");
    }
}
