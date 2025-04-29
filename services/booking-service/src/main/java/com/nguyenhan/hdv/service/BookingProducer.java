package com.nguyenhan.hdv.service;

import com.nguyenhan.hdv.model.Booking;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookingProducer {

    private final KafkaTemplate<String, Booking> kafkaTemplate;

    public BookingProducer(KafkaTemplate<String, Booking> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Gửi thông tin về đơn đặt vé mới vào Kafka
    public void sendBookingCreation(Booking booking) {
        kafkaTemplate.send("booking-created", booking.getId().toString(), booking); // Gửi thông điệp vào topic "booking-created"
    }

}
