package com.woven.wovenfinance.model;

import com.woven.wovenfinance.enums.TokenStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "token_tbl")
@Getter
@Setter
public class Token extends BaseEntity {

    @Column(length = 500)
    private String token;

    @Enumerated(EnumType.STRING)
    private TokenStatus tokenStatus;

    @OneToOne()
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
