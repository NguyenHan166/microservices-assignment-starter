package com.nguyenhan.hdv.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class Booking {
    private Long id;
    private Movie movie;
    private LocalDateTime showtime;
    private Long userId;
    private Long quantity;
    private String statusPayment = "Pending";
}
