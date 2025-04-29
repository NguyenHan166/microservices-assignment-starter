package com.nguyenhan.hdv.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class Payment {
    private Long id;
    private Long bookingId;
    private BigDecimal amount;
    private String status;
}
