package com.woven.wovenfinance.service.serviceImpl;

import com.woven.wovenfinance.dto.ContractResponse;
import com.woven.wovenfinance.enums.ContractStatus;
import com.woven.wovenfinance.exeptions.EntityNotFoundException;
import com.woven.wovenfinance.exeptions.NotAuthorityException;
import com.woven.wovenfinance.exeptions.UserNotFoundException;
import com.woven.wovenfinance.model.Contract;
import com.woven.wovenfinance.model.Profile;
import com.woven.wovenfinance.repository.ContractRepository;
import com.woven.wovenfinance.repository.ProfileRepository;
import com.woven.wovenfinance.service.ContractService;
import com.woven.wovenfinance.utils.ResponseObjectMappers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final ProfileRepository profileRepository;
    private final ContractRepository contractRepository;
    @Override
    public ContractResponse getContract(Long id) {
        Contract contract=contractRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Sorry we don't have such contract in our record"));
        if(contract.getClient().getId().equals(getLoggedInUser().getId())
        || contract.getContractor().getId().equals(getLoggedInUser().getId()
        )){
            return ResponseObjectMappers.contractMapper(contract);
        }
        throw new NotAuthorityException("Please you are not part of this contract");
    }

    @Override
    public List<ContractResponse> getMyContracts(ContractStatus status) {
        Profile profile=getLoggedInUser();

        return contractRepository.findByClientIdOrContractorIdAndStatus(profile.getId(),status).parallelStream()
                .map(ResponseObjectMappers::contractMapper).collect(Collectors.toList());

    }
    private Profile getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName();
        return profileRepository.findByEmail(loggedInUserEmail).
                orElseThrow(() -> new UserNotFoundException("No user found, please log in"));
    }


}
