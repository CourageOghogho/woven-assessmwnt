package com.woven.wovenfinance.repository;

import com.woven.wovenfinance.enums.PaymentStatus;
import com.woven.wovenfinance.model.Job;
import com.woven.wovenfinance.model.Profession;
import com.woven.wovenfinance.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    @Query(nativeQuery = true, value = "select * from job_tbl where client_id=id OR contractor_id=id AND payment_status=paymentStatus")
    List<Job>  findByClientIdOrContractorIdAndPaymentStatus(Long id, PaymentStatus paymentStatus);

    @Query("SELECT j FROM Job j JOIN j.contract c JOIN c.client cl WHERE cl.id = :clientId AND j.paymentStatus = :paymentStatus")
    List<Job> findJobsByClientIdAndPaymentStatus(@Param("clientId") Long clientId, @Param("paymentStatus") PaymentStatus paymentStatus);

    @Query("SELECT j.profession " +
            "FROM Job j " +
            "WHERE j.paymentStatus = 'PAID' AND j.startDate >= :startDate AND j.endDate <= :endDate " +
            "GROUP BY j.profession.id " +
            "ORDER BY SUM(j.price) DESC")
    List<Profession> findTopByDates(@Param("startDate") LocalDate startDate,
                                    @Param("endDate") LocalDate endDate);

    @Query("SELECT j.contract.client " +
            "FROM Job j " +
            "WHERE j.paymentStatus = 'PAID' AND j.startDate >= :startDate AND j.endDate <= :endDate " +
            "GROUP BY j.profession.id " +
            "ORDER BY SUM(j.price) DESC")
    List<Profile> findTopClientByDates(@Param("startDate") LocalDate startDate,
                                    @Param("endDate") LocalDate endDate);
}
