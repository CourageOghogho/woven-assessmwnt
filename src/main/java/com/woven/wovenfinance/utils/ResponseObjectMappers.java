package com.woven.wovenfinance.utils;

import com.woven.wovenfinance.dto.ContractResponse;
import com.woven.wovenfinance.dto.JobResponse;
import com.woven.wovenfinance.dto.ProfileResponse;
import com.woven.wovenfinance.model.Contract;
import com.woven.wovenfinance.model.Job;
import com.woven.wovenfinance.model.Profile;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class ResponseObjectMappers {

    public static ContractResponse contractMapper(Contract contract){
        return ContractResponse.builder()
                .name(contract.getName())
                .id(contract.getId())
                .status(contract.getStatus())
                .owner(profileMapper(contract.getClient()))
                .contractor(profileMapper(contract.getContractor()))
                .jobs(contract.getJobs().stream().map(ResponseObjectMappers::jobMapper).collect(Collectors.toList()))
                .totalPrice(contract.getJobs().stream()
                        .map(Job::getPrice).reduce(BigDecimal::add).get())
                .build();
    }
    public static ProfileResponse profileMapper(Profile profile){
        return ProfileResponse.builder()
                .id(profile.getId())
                .name(profile.getName())
                .build();
    }

    public static JobResponse jobMapper(Job job){
        return JobResponse.builder()
                .id(job.getId())
                .name(job.getName())
                .paymentStatus(job.getPaymentStatus())
                .contractId(job.getContract().getId())
                .ContractName(job.getContract().getName())
                .price(job.getPrice())
                .build();
    }

}
