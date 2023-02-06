package com.woven.wovenfinance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PayResponse {
    private Boolean successful;
    private String message;
}
