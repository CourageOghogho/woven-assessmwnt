package com.woven.wovenfinance.model;

import com.woven.wovenfinance.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "profile_tbl")
public class Profile extends BaseEntity{
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    private Double balance;
}
