package com.sgu.givingsgu.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long userId;
    private Long projectId;
    private Double amount;
    private String paymentMethod;
}

