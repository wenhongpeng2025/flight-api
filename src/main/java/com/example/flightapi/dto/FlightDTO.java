package com.example.flightapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class FlightDTO {
    private Long flightId;
    private String flightNumber;
    private Long departureAirportId; // 外键简化为ID
    private Long destinationAirportId; // 外键简化为ID
    private LocalDate departureDate;
    private LocalTime departureTime;
    private BigDecimal price;
}
