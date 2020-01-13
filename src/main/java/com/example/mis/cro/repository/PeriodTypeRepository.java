package com.example.mis.cro.repository;

import com.example.mis.cro.entity.PeriodType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional

public interface PeriodTypeRepository extends JpaRepository<PeriodType, String> {
    public Page<PeriodType> findAll(Specification<PeriodType> spec, Pageable pageable);
    public PeriodType findByName(String name);
}
