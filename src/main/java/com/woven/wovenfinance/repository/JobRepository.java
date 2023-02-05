package com.woven.wovenfinance.repository;

import com.woven.wovenfinance.enums.ContractStatus;
import com.woven.wovenfinance.enums.PaymentStatus;
import com.woven.wovenfinance.model.Job;
import com.woven.wovenfinance.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    @Query(nativeQuery = true, value = "select * from job_tbl where client_id=id OR contractor_id=id AND paymentStatus=paymentStatus")
    List<Job>  findByClientIdOrContractorIdAndPaymentStatus(Long id, PaymentStatus paymentStatus);
    List<Job>  findByClientAndPaymentStatus(Profile profile, PaymentStatus unpaid);

}