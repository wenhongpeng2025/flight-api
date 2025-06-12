package com.example.flightapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flightapi.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
