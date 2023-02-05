package com.woven.wovenfinance.service;

import com.woven.wovenfinance.dto.JobResponse;
import com.woven.wovenfinance.dto.PayResponse;

import java.util.List;

public interface JobService {
    List<JobResponse> getMyUnpaidActiveJobs();

    PayResponse payForAJobs(Long jobId);
}
