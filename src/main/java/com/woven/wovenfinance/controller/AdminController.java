package com.woven.wovenfinance.controller;

import com.woven.wovenfinance.dto.ProfessionResponse;
import com.woven.wovenfinance.dto.ProfileResponse;
import com.woven.wovenfinance.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/best-profession")
    public ResponseEntity<ProfessionResponse>getBestProfession(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate ){
        return  ResponseEntity.ok(adminService.bestProfession(startDate,endDate));
    }

    @GetMapping("/best-clients")
    public ResponseEntity<List<ProfileResponse>>getBestClient(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam(defaultValue = "2") Integer limit  ){
        return  ResponseEntity.ok(adminService.bestClient(startDate,endDate,limit));
    }
}
