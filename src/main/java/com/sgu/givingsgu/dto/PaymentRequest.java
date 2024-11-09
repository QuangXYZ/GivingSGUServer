package com.sgu.givingsgu.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long transactionId;
    private Long userId;
    private Long projectId;
    private Double amount;
    private String paymentMethod;
    private String token;
}

