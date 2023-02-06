package com.woven.wovenfinance.controller;

import com.woven.wovenfinance.dto.JobResponse;
import com.woven.wovenfinance.dto.PayResponse;
import com.woven.wovenfinance.dto.PaymentRequest;
import com.woven.wovenfinance.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {
    public  final JobService jobService;
    @GetMapping("/unpaid")
    public ResponseEntity<List<JobResponse>> getUnpaidActiveJobs(){
        return ResponseEntity.ok(jobService.getMyUnpaidActiveJobs());
    }

    @PostMapping("/pay")
    public ResponseEntity<PayResponse> payForAJobs(@RequestBody PaymentRequest request){
        return ResponseEntity.ok(jobService.payForAJobs(request.getJobId()));
    }
}
