package com.nguyenhan.hdv.service;

import com.nguyenhan.hdv.MovieClient;
import com.nguyenhan.hdv.model.Booking;
import com.nguyenhan.hdv.model.BookingRequest;
import com.nguyenhan.hdv.model.Movie;
import com.nguyenhan.hdv.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private MovieClient movieClient; // Feign client to communicate with Movie service

    @Autowired
    private BookingProducer bookingProducer;

    @Autowired
    private BookingConsumer bookingConsumer;

    public Booking createBooking(BookingRequest bookingRequest) {
        Movie movie = movieClient.getMovieByID(bookingRequest.getMovieId());
        Booking booking = new Booking();
        booking.setMovieId(movie.getId());
        booking.setQuantity(bookingRequest.getQuantity());
        booking.setShowtime(bookingRequest.getShowtime());
        booking.setUserId(bookingRequest.getUserId());

        Booking saveBooking = bookingRepository.save(booking);
        bookingProducer.sendBookingCreation(saveBooking);
        return saveBooking;
    }

    public Booking getBooking(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
    }
}
