package com.example.flightapi.dto;

import lombok.Data;

@Data
public class AirportDTO {
    private Long airportId;
    private String code;
    private String name;
    private String city;
}
