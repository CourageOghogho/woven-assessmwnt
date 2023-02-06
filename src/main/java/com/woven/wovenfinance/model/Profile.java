package com.woven.wovenfinance.model;

import com.woven.wovenfinance.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "profile_tbl")
public class Profile extends BaseEntity{
    private String name;

    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    private BigDecimal balance;
}
