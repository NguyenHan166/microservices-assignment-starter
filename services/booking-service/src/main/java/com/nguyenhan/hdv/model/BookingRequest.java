package com.nguyenhan.hdv.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Setter@Getter@AllArgsConstructor@NoArgsConstructor
public class BookingRequest {
    private Long movieId; // Tham chiếu đến Movie từ movie service
    private Long userId;  // Tham chiếu đến User từ user service
    private Long roomId;  // Tham chiếu đến Room từ room_seat service
    private LocalDateTime showtime; // Thời gian chiếu phim
    private LocalDateTime createdAt; // Thời gian tạo booking

    @ElementCollection
    private List<Long> seatIds; // Danh sách ID của các ghế được đặt

    private Long quantity; // Số lượng vé
    private Double totalPrice; // Tổng giá vé

    @Enumerated(EnumType.STRING)
    private Booking.BookingStatus status; // Trạng thái booking (PENDING, CONFIRMED, CANCELLED, EXPIRED)

    @Enumerated(EnumType.STRING)
    private Booking.PaymentStatus paymentStatus; // Trạng thái thanh toán (PENDING, COMPLETED, FAILED)

    public enum BookingStatus {
        PENDING, CONFIRMED, CANCELLED, EXPIRED
    }

    public enum PaymentStatus {
        PENDING, COMPLETED, FAILED
    }
}
