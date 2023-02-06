package com.woven.wovenfinance.service.serviceImpl;

import com.woven.wovenfinance.dto.DepositRequest;
import com.woven.wovenfinance.dto.PayResponse;
import com.woven.wovenfinance.enums.PaymentStatus;
import com.woven.wovenfinance.exeptions.UserNotFoundException;
import com.woven.wovenfinance.model.Job;
import com.woven.wovenfinance.model.Profile;
import com.woven.wovenfinance.repository.JobRepository;
import com.woven.wovenfinance.repository.ProfileRepository;
import com.woven.wovenfinance.service.BalanceServer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceServer {
    private  final ProfileRepository profileRepository;
    private final JobRepository jobRepository;
    @Transactional
    @Override
    public PayResponse deposit(DepositRequest request) {
        Profile profile=getLoggedInUser();
        BigDecimal currentUnpaidAmount=jobRepository.findJobsByClientIdAndPaymentStatus(profile.getId(), PaymentStatus.UNPAID).stream()
                .map(Job::getPrice)
                .reduce(BigDecimal::add)
                .get();
        BigDecimal payableAmount=currentUnpaidAmount.multiply(BigDecimal.valueOf(25/100));
        if(request.getAmount().compareTo(payableAmount)<0){
            profile.setBalance(profile.getBalance().add(request.getAmount()));
            return new PayResponse(true,"Amount deposited successfully");
        }
        return new PayResponse(false,"Sorry, you cannot transact this amount for now");
    }

    private Profile getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName();
        return profileRepository.findByEmail(loggedInUserEmail).
                orElseThrow(() -> new UserNotFoundException("No user found, please log in"));
    }
}
