package com.woven.wovenfinance.utils;

import com.woven.wovenfinance.enums.ContractStatus;
import com.woven.wovenfinance.enums.PaymentStatus;
import com.woven.wovenfinance.enums.Role;
import com.woven.wovenfinance.model.Contract;
import com.woven.wovenfinance.model.Job;
import com.woven.wovenfinance.model.Profession;
import com.woven.wovenfinance.model.Profile;
import com.woven.wovenfinance.repository.ContractRepository;
import com.woven.wovenfinance.repository.JobRepository;
import com.woven.wovenfinance.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class TestInitializer implements CommandLineRunner {
    private final ProfileRepository profileRepository;
    private final JobRepository jobRepository;
    private final ContractRepository contractRepository;
    private final ProfessionRepository professionRepository;

    private void initializeObjects(){
        Profile clientProfile = profileRepository.save(Profile.builder()
                .name("John Doe")
                .email("oghogho.courage@gmail.com")
                .password("password")
                .role(Role.CLIENT)
                .balance(BigDecimal.valueOf(1000))
                .build());

            Profile contractorProfile = profileRepository.save(Profile.builder()
                    .name("Jane Doe")
                    .email("janedoe@wovenfinance.com")
                    .password("password")
                    .role(Role.CONTRACTOR)
                    .balance(BigDecimal.valueOf(1000))
                    .build());

          Contract contract=contractRepository.save(Contract.builder().client(clientProfile).contractor(contractorProfile).status(ContractStatus.IN_PROGRESS).build());

        Profession profession=professionRepository.save(Profession.builder().build());

        Job job = jobRepository.save( Job.builder()
                    .name("Sample Job")
                    .price(BigDecimal.valueOf(100))
                    .paymentStatus(PaymentStatus.UNPAID)
                    .startDate(LocalDate.now())
                    .endDate(LocalDate.now().plusDays(7))
                    .contract(contract)
                    .profession(profession)
                    .build());

    }

    @Override
    public void run(String... args) throws Exception {
        initializeObjects();
    }
}
