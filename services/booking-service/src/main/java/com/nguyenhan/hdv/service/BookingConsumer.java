package com.nguyenhan.hdv.service;

import com.nguyenhan.hdv.model.Booking;
import com.nguyenhan.hdv.model.Payment;
import com.nguyenhan.hdv.repository.BookingRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookingConsumer {

    private final BookingRepository bookingRepository;

    public BookingConsumer(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    // Lắng nghe kết quả thanh toán từ Kafka topic "payment-status"
    @KafkaListener(topics = "payment-status", groupId = "booking-service")
    public void listenPaymentStatus(Payment payment) {
        Booking booking = bookingRepository.findById(payment.getBookingId()).orElseThrow(
                () -> new RuntimeException("Booking not found")
        );
        if ("Success".equals(payment.getStatus())) {
            // Cập nhật trạng thái đơn đặt vé khi thanh toán thành công
            // Ví dụ: update booking status to 'Confirmed'
            booking.setPaymentStatus(Booking.PaymentStatus.COMPLETED);
            System.out.println("Payment successful, updating booking status...");
        } else {
            booking.setPaymentStatus(Booking.PaymentStatus.FAILED);
            // Xử lý trường hợp thanh toán thất bại
            System.out.println("Payment failed, notifying user...");
        }
        bookingRepository.save(booking);
    }

}
