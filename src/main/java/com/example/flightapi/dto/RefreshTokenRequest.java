package com.example.flightapi.dto;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken; // 必须与前端传递的字段名完全一致
}
