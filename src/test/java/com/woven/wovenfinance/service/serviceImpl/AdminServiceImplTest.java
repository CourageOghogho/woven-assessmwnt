package com.woven.wovenfinance.service.serviceImpl;

import com.woven.wovenfinance.dto.ProfessionResponse;
import com.woven.wovenfinance.dto.ProfileResponse;
import com.woven.wovenfinance.model.Profession;
import com.woven.wovenfinance.model.Profile;
import com.woven.wovenfinance.repository.JobRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminServiceImplTest {
    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Test
    public void testBestProfession() {
        Profession profession = new Profession();
        profession.setId(1L);
        profession.setName("Test Profession");

        when(jobRepository.findTopByDates(any(LocalDate.class), any(LocalDate.class))).thenReturn(Arrays.asList(profession));

        ProfessionResponse result = adminService.bestProfession(LocalDate.now(), LocalDate.now());
        assertEquals(1L, result.getId());
        assertEquals("Test Profession", result.getName());
    }

    @Test
    public void testBestClient() {
        Profile profile = new Profile();
        profile.setId(1L);
        profile.setName("Test Client");

        when(jobRepository.findTopClientByDates(any(LocalDate.class), any(LocalDate.class))).thenReturn(Arrays.asList(profile));

        List<ProfileResponse> result = adminService.bestClient(LocalDate.now(), LocalDate.now(), 1);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Test Client", result.get(0).getName());
    }
}
