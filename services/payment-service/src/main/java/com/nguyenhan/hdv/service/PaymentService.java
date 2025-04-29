package com.nguyenhan.hdv.service;

import com.nguyenhan.hdv.model.Payment;
import com.nguyenhan.hdv.model.PaymentRequest;
import com.nguyenhan.hdv.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment processPayment(PaymentRequest paymentRequest) {
        // Call external payment gateway
        Payment payment = new Payment();
        payment.setAmount(paymentRequest.getAmount());
        payment.setBookingId(paymentRequest.getBookingId());
        // Simulate payment processing
        payment.setStatus("Success");
        return paymentRepository.save(payment);
    }

    public Payment getPaymentStatus(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("payment not found"));
    }

}
