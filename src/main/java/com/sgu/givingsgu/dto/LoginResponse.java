package com.sgu.givingsgu.dto;

import com.sgu.givingsgu.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private User user;
    private String token;
}
