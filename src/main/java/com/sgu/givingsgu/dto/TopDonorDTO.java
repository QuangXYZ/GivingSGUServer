package com.sgu.givingsgu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopDonorDTO {
    private Long userId;
    private String fullName;
    private String imageUrl;
    private Double totalAmount;
}
