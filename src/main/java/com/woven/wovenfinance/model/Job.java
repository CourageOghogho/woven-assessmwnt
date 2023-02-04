package com.woven.wovenfinance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "job_tbl")
public class Job extends BaseEntity{
    private String name;
    private Double price;
    @ManyToOne
    private Contract contract;
}
