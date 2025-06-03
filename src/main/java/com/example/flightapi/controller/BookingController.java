package com.example.flightapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flightapi.dto.BookingDTO;
import com.example.flightapi.dto.PassengerDTO;
import com.example.flightapi.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    /**
     * 获取所有预订信息的接口
     * 
     * @return 包含所有预订信息的列表
     */
    @Operation(summary = "获取所有预订信息", description = "获取所有预订信息的接口")
    @GetMapping()
    public List<BookingDTO> getAllBookings() {
        // 实现获取所有预订信息的逻辑
        List<BookingDTO> bookings = new ArrayList<BookingDTO>();

        return bookings;
    }

    @Operation(summary = "根据ID获取预订信息", description = "根据ID获取预订信息的接口")
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id) {
        try {
            BookingDTO booking = bookingService.getBookingDetailById(id);
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to get Booking: " + e.getMessage());
        }

    }

    @Operation(summary = "创建预订", description = "创建预订的接口")
    @RequestMapping()
    public ResponseEntity<?> createBooking(@PathVariable Long flightId, @RequestBody PassengerDTO passenger) {
        // 实现创建预订的逻辑
        try {
            BookingDTO booking = new BookingDTO();
            // bookingService.createBooking(flightId, passenger);
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to create Booking: " + e.getMessage());
        }
    }

}
