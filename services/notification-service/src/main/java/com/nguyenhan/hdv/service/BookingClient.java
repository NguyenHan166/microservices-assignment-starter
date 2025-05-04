package com.nguyenhan.hdv.service;

import com.nguyenhan.hdv.model.Ticket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "booking-service")
public interface BookingClient {
    @GetMapping("/bookings/{id}")
    Ticket getBooking(@PathVariable("id") Long Id);
}
