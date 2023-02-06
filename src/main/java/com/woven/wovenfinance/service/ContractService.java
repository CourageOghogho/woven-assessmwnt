package com.woven.wovenfinance.service;

import com.woven.wovenfinance.dto.ContractResponse;
import com.woven.wovenfinance.enums.ContractStatus;

import java.util.List;

public interface ContractService {
    ContractResponse getContract(Long id);


    List<ContractResponse> getMyContracts(ContractStatus status);
}
