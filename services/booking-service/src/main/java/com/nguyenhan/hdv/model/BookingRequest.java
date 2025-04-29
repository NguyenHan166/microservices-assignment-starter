package com.nguyenhan.hdv.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Setter@Getter@AllArgsConstructor@NoArgsConstructor
public class BookingRequest {
    private Long movieId;
    private LocalDateTime showtime;
    private Long userId;
    private Long quantity;
}
