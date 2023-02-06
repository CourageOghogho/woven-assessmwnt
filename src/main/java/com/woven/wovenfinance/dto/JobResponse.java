package com.woven.wovenfinance.dto;

import com.woven.wovenfinance.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobResponse {
    private Long id;
    private String name;
    private  Long contractId;
    private String ContractName;
    private PaymentStatus paymentStatus;
    private BigDecimal price;
}
