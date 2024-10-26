package com.sgu.givingsgu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseWrapper<T> {
    private int status;
    private String message;
    private T data;
}

