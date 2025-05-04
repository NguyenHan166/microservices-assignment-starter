package com.nguyenhan.hdv.service;

import com.nguyenhan.hdv.client.MovieClient;
import com.nguyenhan.hdv.client.RoomClient;
import com.nguyenhan.hdv.client.UserClient;
import com.nguyenhan.hdv.model.*;
import com.nguyenhan.hdv.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private BookingRepository bookingRepository;

    private MovieClient movieClient; // Feign client to communicate with Movie service

    private UserClient userClient;

    private BookingProducer bookingProducer;

    private RoomClient roomClient;

//    @Autowired
//    private BookingConsumer bookingConsumer;

    public BookingService(BookingRepository bookingRepository, MovieClient movieClient, UserClient userClient,
                          BookingProducer bookingProducer, RoomClient roomClient) {
        this.bookingRepository = bookingRepository;
        this.movieClient = movieClient;
        this.userClient = userClient;
        this.bookingProducer = bookingProducer;
        this.roomClient = roomClient;
    }

    public Booking createBooking(BookingRequest bookingRequest) {
        Movie movie = movieClient.getMovieByID(bookingRequest.getMovieId());
        User user = userClient.findById(bookingRequest.getUserId());
        Room room = roomClient.getRoom(bookingRequest.getRoomId());

        Booking booking = new Booking();
        booking.setMovieId(movie.getId());
        booking.setRoomId(room.getId());
        booking.setQuantity(bookingRequest.getQuantity());
        booking.setShowtime(bookingRequest.getShowtime());
        booking.setUserId(user.getId());
        booking.setCreatedAt(bookingRequest.getCreatedAt());
        booking.setSeatIds(bookingRequest.getSeatIds());
        booking.setPaymentStatus(bookingRequest.getPaymentStatus());
        booking.setStatus(bookingRequest.getStatus());

        Booking saveBooking = bookingRepository.save(booking);
        bookingProducer.sendBookingCreation(saveBooking);
        return saveBooking;
    }

    public Ticket getBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
        Ticket ticket = new Ticket();
        ticket.setBooking(booking);
        ticket.setUser(userClient.findById(booking.getUserId()));
        ticket.setRoom(roomClient.getRoom(booking.getRoomId()));
        ticket.setMovie(movieClient.getMovieByID(booking.getMovieId()));
        ticket.setSeats(ticket.getRoom().getSeats());
        return ticket;
    }
}
