package com.nguyenhan.hdv.service;

import com.nguyenhan.hdv.model.Booking;
import com.nguyenhan.hdv.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    @Autowired
    private PaymentProducer paymentProducer;

    // Lắng nghe thông tin đặt vé từ Kafka topic "booking-created"
    @KafkaListener(topics = "booking-created", groupId = "payment-group")
    public void listenBookingCreated(Booking booking) {
        // Xử lý thanh toán khi có thông tin đơn đặt vé mới
        // Giả lập logic thanh toán
        Payment payment = new Payment();
        payment.setBookingId(booking.getId());
        payment.setAmount(booking.getQuantity());
        boolean paymentSuccess = processPayment(payment); // Giả lập quá trình thanh toán

        // Gửi kết quả thanh toán vào Kafka topic "payment-status"
        payment.setStatus(paymentSuccess ? "Success" : "Failure");
        paymentProducer.sendPaymentStatus(payment); // Gửi sự kiện thanh toán vào Kafka
    }

    private boolean processPayment(Payment payment) {
        // Giả lập logic thanh toán (thực tế có thể tích hợp cổng thanh toán như Stripe, PayPal...)
        return true; // Giả sử thanh toán thành công
    }

}
