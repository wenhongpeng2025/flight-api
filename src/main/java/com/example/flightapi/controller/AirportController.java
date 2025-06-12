package com.example.flightapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flightapi.dto.AirportDTO;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    @Operation(summary = "获取所有机场信息", description = "获取所有机场信息的接口")
    @GetMapping()
    public List<AirportDTO> getAllAirports() {
        // 实现获取所有机场信息的逻辑
        List<AirportDTO> airports = new ArrayList<AirportDTO>();

        return airports;
    }

}
