package com.nguyenhan.hdv.controller;

import com.nguyenhan.hdv.model.Payment;
import com.nguyenhan.hdv.model.PaymentRequest;
import com.nguyenhan.hdv.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Payment processPayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.processPayment(paymentRequest);
    }

    @GetMapping("/{id}")
    public Payment getPaymentStatus(@PathVariable Long id) {
        return paymentService.getPaymentStatus(id);
    }

}
