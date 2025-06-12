package com.example.flightapi.dto;

import lombok.Data;

@Data
public class PassengerDTO {
    private Long passengerId;
    private Long bookingId; // 外键简化为ID
    private String firstName;
    private String lastName;
    private String email;
}
