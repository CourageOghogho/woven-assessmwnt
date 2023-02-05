package com.woven.wovenfinance.controller;

import com.woven.wovenfinance.dto.ContractResponse;
import com.woven.wovenfinance.enums.ContractStatus;
import com.woven.wovenfinance.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;
    @GetMapping("/{id}")
    public ResponseEntity<ContractResponse> getContract(@PathVariable Long id){
        return ResponseEntity.ok(contractService.getContract(id));
    }

    @GetMapping
    public ResponseEntity<List<ContractResponse>> getAllMyContracts(){
        ContractStatus status=ContractStatus.TERMINATED;
        return ResponseEntity.ok(contractService.getMyContracts(status));
    }


}
