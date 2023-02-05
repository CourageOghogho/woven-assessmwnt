package com.woven.wovenfinance.model;

import com.woven.wovenfinance.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "job_tbl")
public class Job extends BaseEntity{
    private String name;
    private BigDecimal price;
    private PaymentStatus paymentStatus;
    @ManyToOne
    private Contract contract;
}
