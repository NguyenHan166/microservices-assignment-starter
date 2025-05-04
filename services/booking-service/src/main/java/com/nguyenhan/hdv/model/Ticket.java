package com.nguyenhan.hdv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor@NoArgsConstructor
public class Ticket {
    private Movie movie;
    private Booking booking;
    private User user;
    private Room room;
    private List<Seat> seats;
}
