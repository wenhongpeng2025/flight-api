package com.example.flightapi.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String country;
    private String phone;
}
