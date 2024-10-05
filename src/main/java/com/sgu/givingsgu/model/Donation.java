package com.sgu.givingsgu.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "donations")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donationId;

    @Column(name = "project_id", nullable = false)
    private Long projectId;  // Foreign Key referencing Project

    @Column(name = "user_id", nullable = false)
    private Long userId;     // Foreign Key referencing Users


    private Double amount;

    @Temporal(TemporalType.DATE)
    private Date donateDate;

    private String status;
}
