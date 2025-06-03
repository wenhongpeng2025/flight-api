package com.example.flightapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flightapi.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
