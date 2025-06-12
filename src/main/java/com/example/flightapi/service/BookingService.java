package com.example.flightapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flightapi.dto.BookingDTO;
import com.example.flightapi.entity.Booking;
import com.example.flightapi.repository.BookingRepository;

import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public BookingDTO getBookingDetailById(Long bookingId) {
        // 实现根据ID获取预订详情的逻辑
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        BookingDTO booking = new BookingDTO();

        booking.setBookingId(bookingOptional.get().getBookingId());
        booking.setFlightId(bookingOptional.get().getFlightId());
        booking.setUserId(bookingOptional.get().getUserId());
        booking.setReference(bookingOptional.get().getReference());
        booking.setStatus(bookingOptional.get().getStatus());
        booking.setBookingTime(bookingOptional.get().getBookingTime());
        booking.setTotalPrice(bookingOptional.get().getTotalPrice());
        // booking.setPassengers(bookingOptional.get().getPassengers());
        // 返回一个包含预订详情的响应实体
        return booking;
    }

    public BookingDTO createBooking(Long flightId, BookingDTO bookingDTO) {
        // 实现创建预订的逻辑
        Booking booking = new Booking();
        // booking.setBookingId(bookingDTO.getBookingId());
        // booking.setFlightId(bookingDTO.getFlightId());
        // booking.setUserId(bookingDTO.getUserId());
        // booking.setReference(bookingDTO.getReference());
        // booking.setStatus(bookingDTO.getStatus());
        // booking.setBookingTime(bookingDTO.getBookingTime());
        // booking.setTotalPrice(bookingDTO.getTotalPrice());
        // booking.setPassengers(bookingDTO.getPassengers());
        BeanUtils.copyProperties(bookingDTO, booking);
        bookingRepository.save(booking);
        // 返回一个包含创建的预订的响应实体
        return bookingDTO;
    }

}
