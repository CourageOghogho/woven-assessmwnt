package com.woven.wovenfinance.service;

import com.woven.wovenfinance.dto.DepositRequest;
import com.woven.wovenfinance.dto.PayResponse;

public interface BalanceServer {
    PayResponse deposit(DepositRequest request);
}
