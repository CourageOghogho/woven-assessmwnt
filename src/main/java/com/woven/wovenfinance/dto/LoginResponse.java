package com.woven.wovenfinance.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginResponse {

    private String message;
    private String token;

    public LoginResponse(String message) {
        this.message = message;
    }
}
