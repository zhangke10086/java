package com.example.mis.cro.repository;

import com.example.mis.cro.entity.PlanType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional

public interface PlanTypeRepository extends JpaRepository<PlanType, String> {
    public Page<PlanType> findAll(Specification<PlanType> spec, Pageable pageable);
    public PlanType findByName(String name);
}
