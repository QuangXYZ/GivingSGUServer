package com.sgu.givingsgu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Table(name = "projects")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projectId;
    @Column(name = "organization_id")
    private Long organizationId; // Foreign Key referencing the organization

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Date startDate;
    private Date endDate;

    private String targetAmount;
    private String currentAmount;
    private String status;
    private int numberDonors;
    private String image;

}
