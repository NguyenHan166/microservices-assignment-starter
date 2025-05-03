package com.nguyenhan.hdv.service;

import com.nguyenhan.hdv.model.Booking;
import com.nguyenhan.hdv.model.Movie;
import com.nguyenhan.hdv.model.Payment;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @Autowired
    private EmailService emailService;

    // Lắng nghe sự kiện cập nhật phim từ Kafka topic "movie-updates"
    @KafkaListener(topics = "movie-updates", groupId = "notification-group")
    public void listenMovieUpdates(Movie movie) {
        // Gửi thông báo về phim mới hoặc lịch chiếu mới
        System.out.println("New Movie Update: " + movie.getTitle());
        // Gửi thông báo tới người dùng
    }

    // Lắng nghe sự kiện đặt vé thành công từ Kafka topic "booking-created"
    @KafkaListener(topics = "booking-created", groupId = "notification-group")
    public void listenBookingCreated(Booking booking) {
        // Gửi thông báo khi có đơn đặt vé mới
        System.out.println("Booking Created: " + booking.getId());
        try {
            emailService.sendEmail("nvhan2k3@gmail.com", "Noti", "Test Noti");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        // Gửi thông báo tới người dùng
    }

    // Lắng nghe kết quả thanh toán từ Kafka topic "payment-status"
    @KafkaListener(topics = "payment-status", groupId = "notification-group")
    public void listenPaymentStatus(Payment payment) {
        // Gửi thông báo khi thanh toán thành công hoặc thất bại
        if ("Success".equals(payment.getStatus())) {
            System.out.println("Payment Successful for Booking ID: " + payment.getBookingId());
        } else {
            System.out.println("Payment Failed for Booking ID: " + payment.getBookingId());
        }
    }

}
