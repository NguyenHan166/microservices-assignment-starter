package com.nguyenhan.hdv.service;

import com.nguyenhan.hdv.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {

    private final KafkaTemplate<String, Payment> kafkaTemplate;

    @Autowired
    public PaymentProducer(KafkaTemplate<String, Payment> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Gửi kết quả thanh toán vào Kafka topic "payment-status"
    public void sendPaymentStatus(Payment payment) {
        kafkaTemplate.send("payment-status", payment.getBookingId().toString(), payment); // Gửi thông điệp vào topic "payment-status"
    }

}
