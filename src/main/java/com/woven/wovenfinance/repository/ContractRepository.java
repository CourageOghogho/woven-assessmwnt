package com.woven.wovenfinance.repository;

import com.woven.wovenfinance.enums.ContractStatus;
import com.woven.wovenfinance.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    @Query(nativeQuery = true, value = "select * from contract_tbl where client_id=id OR contractor_id=id AND status=status")
    List<Contract> findByClientIdOrContractorIdAndStatus(Long id, ContractStatus status);
}