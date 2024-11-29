package com.sgu.givingsgu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TopDonorDTO {
    private Long userId;
    private String fullName;
    private String imageUrl;
    private Double totalAmount;
    private Date donateDate;
}
