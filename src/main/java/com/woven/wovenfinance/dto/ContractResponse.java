package com.woven.wovenfinance.dto;

import com.woven.wovenfinance.enums.ContractStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ContractResponse {
    private Long id;
    private String name;
    private ContractStatus status;
    private ProfileResponse owner;
    private BigDecimal totalPrice;
    private ProfileResponse contractor;
    private List<JobResponse> jobs;

}
