package com.woven.wovenfinance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.woven.wovenfinance.enums.ContractStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "contract_tbl")
public class Contract extends BaseEntity{
    private String name;
    @Enumerated(EnumType.STRING)
    private ContractStatus status;
    @JsonIgnore
    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Job> jobs;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Profile client;
    @ManyToOne
    @JoinColumn(name = "contractor_id")
    private Profile contractor;
}
