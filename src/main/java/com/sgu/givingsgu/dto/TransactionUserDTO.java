package com.sgu.givingsgu.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TransactionUserDTO {
    private Long id;
    private double amount;
    private Date transactionDate;
    private String paymentMethod;
    private String status;
    private String token;
    private String transactionId;
    private String fullName;
    private String facultyName;
    private String studentId;
}