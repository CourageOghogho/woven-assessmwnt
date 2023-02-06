package com.woven.wovenfinance.service.serviceImpl;

import com.woven.wovenfinance.dto.ProfessionResponse;
import com.woven.wovenfinance.dto.ProfileResponse;
import com.woven.wovenfinance.model.Profession;
import com.woven.wovenfinance.repository.JobRepository;
import com.woven.wovenfinance.service.AdminService;
import com.woven.wovenfinance.utils.ResponseObjectMappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final JobRepository jobRepository;
    @Override
    public ProfessionResponse bestProfession(LocalDate startDate, LocalDate endDate) {
        Profession profession=jobRepository.findTopByDates(startDate,endDate).get(0);

        return ProfessionResponse.builder()
                .id(profession.getId())
                .name(profession.getName())
                .build();
    }


    @Override
    public List<ProfileResponse> bestClient(LocalDate startDate, LocalDate endDate, Integer limit) {

        return jobRepository.findTopClientByDates(startDate, endDate).stream()
                .limit(limit).map(ResponseObjectMappers::profileMapper).collect(Collectors.toList());
    }

}
