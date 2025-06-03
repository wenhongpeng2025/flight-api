package com.example.flightapi.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flightapi.dto.JwtAuthenticationResponse;
import com.example.flightapi.dto.LoginRequestDTO;
import com.example.flightapi.dto.RefreshTokenRequest;
import com.example.flightapi.dto.UserDTO;
import com.example.flightapi.service.LoginService;
import com.example.flightapi.utils.JwtUtil;
import com.example.flightapi.utils.PasswordEncoderUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Operation(summary = "用户登录", description = "用户登录接口")
    @RequestMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {

        UserDTO userDTO = loginService.findByUsername(loginRequest.getEmail());
        if (userDTO == null)
            return ResponseEntity.status(404).body("User not found");

        boolean isMatch = PasswordEncoderUtil.matches(loginRequest.getPassword(), userDTO.getPassword());
        if (userDTO.getEmail().equals(loginRequest.getEmail()) && isMatch) {
            String accessToken = JwtUtil.generateToken(loginRequest.getEmail(), userDTO.getUserId().toString());
            String refreshToken = JwtUtil.generateRefreshToken(loginRequest.getEmail(), userDTO.getUserId().toString());
            return ResponseEntity.ok(new JwtAuthenticationResponse(accessToken, refreshToken));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

    }

    @GetMapping("/check")
    public ResponseEntity<String> check(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String username = JwtUtil.getUsernameFromToken(token);
            return ResponseEntity.ok("Hello, " + username);
        } else {
            return ResponseEntity.status(401).body("Missing or Invalid Token");
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();

        try {
            if (refreshToken == null || JwtUtil.validateToken(refreshToken) == null) {
                return ResponseEntity.status(401).body("Invalid refresh token");
            }
            String username = JwtUtil.getUsernameFromToken(refreshToken);
            if (username == null)
                return ResponseEntity.status(401).body("Invalid refresh token");

            UserDTO user = loginService.findByUsername(username);
            if (user == null)
                return ResponseEntity.status(404).body("User not found");

            String newAccessToken = JwtUtil.generateToken(username, user.getUserId().toString());
            String newRefreshToken = JwtUtil.generateRefreshToken(username, user.getUserId().toString());

            return ResponseEntity.ok(new JwtAuthenticationResponse(newAccessToken, newRefreshToken));
        } catch (ExpiredJwtException ex) {
            return ResponseEntity.status(401).body(Map.of(
                    "code", 401,
                    "message", "Token expired",
                    "error", "EXPIRED_TOKEN"));
        } catch (JwtException | IllegalArgumentException ex) {
            return ResponseEntity.status(401).body("Invalid token: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @Operation(summary = "用户注册", description = "用户注册接口")
    @RequestMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        // 加密密码
        String password = PasswordEncoderUtil.encode(userDTO.getPassword());
        userDTO.setPassword(password);
        UserDTO resUser = loginService.registerUser(userDTO);
        if (resUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

    }
}
