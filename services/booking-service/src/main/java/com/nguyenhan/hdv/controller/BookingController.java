package com.nguyenhan.hdv.controller;

import com.nguyenhan.hdv.model.Booking;
import com.nguyenhan.hdv.model.BookingRequest;
import com.nguyenhan.hdv.model.Ticket;
import com.nguyenhan.hdv.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Booking createBooking(@RequestBody BookingRequest bookingRequest) {
        return bookingService.createBooking(bookingRequest);
    }

    @GetMapping("/{id}")
    public Ticket getBooking(@PathVariable Long id) {
        Ticket ticket = bookingService.getBooking(id);
        return ticket;
    }

}
