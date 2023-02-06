package com.woven.wovenfinance.utils;

import com.woven.wovenfinance.model.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession, Long> {
}