package com.sgu.givingsgu.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "donation_id", nullable = false)
    @JsonBackReference
    private Donation donation;  // Foreign Key referencing Donations

    private double amount;

    @Temporal(TemporalType.DATE)
    private Date transactionDate;

    private String paymentMethod;
    private String status;
    private String token;
    private String transactionId;
}
