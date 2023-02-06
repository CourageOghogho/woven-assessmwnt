package com.woven.wovenfinance.service;

import com.woven.wovenfinance.dto.ProfessionResponse;
import com.woven.wovenfinance.dto.ProfileResponse;

import java.time.LocalDate;
import java.util.List;

public interface AdminService {
    ProfessionResponse bestProfession(LocalDate startDate, LocalDate endDate);

    List<ProfileResponse> bestClient(LocalDate startDate, LocalDate endDate, Integer limit);
}
