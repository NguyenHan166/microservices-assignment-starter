package com.nguyenhan.hdv.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class PaymentRequest {
    private Long bookingId;
    private Long amount;
    private String status;
}
