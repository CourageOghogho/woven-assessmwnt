package com.woven.wovenfinance.controller;

import com.woven.wovenfinance.dto.DepositRequest;
import com.woven.wovenfinance.dto.PayResponse;
import com.woven.wovenfinance.service.BalanceServer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/balance")
public class BalanceController {
    private final BalanceServer balanceServer;

    @PostMapping("/deposit")
    public ResponseEntity<PayResponse> deposit(@RequestBody DepositRequest request){
        return ResponseEntity.ok(balanceServer.deposit(request));
    }
}
