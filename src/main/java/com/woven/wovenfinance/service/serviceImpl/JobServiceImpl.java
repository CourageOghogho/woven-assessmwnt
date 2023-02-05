package com.woven.wovenfinance.service.serviceImpl;

import com.woven.wovenfinance.dto.JobResponse;
import com.woven.wovenfinance.dto.PayResponse;
import com.woven.wovenfinance.enums.ContractStatus;
import com.woven.wovenfinance.enums.PaymentStatus;
import com.woven.wovenfinance.exeptions.EntityNotFoundException;
import com.woven.wovenfinance.exeptions.UserNotFoundException;
import com.woven.wovenfinance.model.Job;
import com.woven.wovenfinance.model.Profile;
import com.woven.wovenfinance.repository.JobRepository;
import com.woven.wovenfinance.repository.ProfileRepository;
import com.woven.wovenfinance.service.JobService;
import com.woven.wovenfinance.utils.ResponseObjectMappers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final ProfileRepository profileRepository;
    @Override
    public List<JobResponse> getMyUnpaidActiveJobs() {
        Profile profile=getLoggedInUser();
        PaymentStatus paymentStatus=PaymentStatus.UNPAID;
        return jobRepository.findByClientIdOrContractorIdAndPaymentStatus(profile.getId(),paymentStatus).stream()
                .filter(job -> job.getContract().getStatus().equals(ContractStatus.IN_PROGRESS))
                .map(ResponseObjectMappers::jobMapper)
                .collect(Collectors.toList());
    }

//    Get all unpaid jobs for a user (either a client or contractor), for active contracts only.

    @Transactional
    @Override
    public PayResponse payForAJobs(Long jobId) {
        Profile profile=getLoggedInUser();
        Job job=jobRepository.findById(jobId).orElseThrow(()->new EntityNotFoundException("Sorry, We cannot find the Contract."));
        if(profile.getBalance().compareTo(job.getPrice())>=0){
            profile.setBalance(profile.getBalance().subtract(job.getPrice()));
            job.getContract().getContractor().setBalance(job.getContract().getContractor().getBalance().add(job.getPrice()));
            return  new PayResponse(true,"Transaction was successful!");
        }
        return new PayResponse(false,"Transaction NOT successful. Balance is Insufficient");
    }

    private Profile getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName();
        return profileRepository.findByEmail(loggedInUserEmail).
                orElseThrow(() -> new UserNotFoundException("No user found, please log in"));
    }
}
