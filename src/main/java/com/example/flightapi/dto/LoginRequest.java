package com.example.flightapi.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email; // 必须与前端传递的字段名完全一致
    private String password;
}
