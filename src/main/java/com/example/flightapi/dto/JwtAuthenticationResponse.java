package com.example.flightapi.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String accessToken; // 必须与前端传递的字段名完全一致
    private String refreshToken;

    public JwtAuthenticationResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
