package com.example.flightapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookingDTO {
    private Long bookingId;
    private Long userId; // 外键简化为ID
    private Long flightId; // 外键简化为ID
    private String reference;
    private String status;
    private LocalDateTime bookingTime;
    private BigDecimal totalPrice;
}
