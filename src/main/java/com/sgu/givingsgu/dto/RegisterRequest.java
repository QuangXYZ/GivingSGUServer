package com.sgu.givingsgu.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private Long facultyId; // ID của Faculty
    private String fullName;
    private String studentId;
    private String imageUrl;
}

