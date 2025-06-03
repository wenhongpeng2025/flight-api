package com.example.flightapi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // 加密密码（注册时调用）
    public static String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    // 验证密码（登录时调用）
    public static boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
