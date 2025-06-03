package com.example.flightapi.dto;

import java.util.Date;

import lombok.Data;

@Data
public class FlightRequestDTO {

    private String from; // 出发地
    private String to; // 目的地
    private Date depart; // 出发日期

}
