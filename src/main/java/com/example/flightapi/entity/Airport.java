package com.example.flightapi.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "AIRPORT")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airport_id")
    private Long airportId;

    @Column(nullable = false, unique = true, length = 10)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String city;

    @OneToMany(mappedBy = "departureAirport")
    private List<Flight> departureFlights;

    @OneToMany(mappedBy = "destinationAirport")
    private List<Flight> destinationFlights;

}
