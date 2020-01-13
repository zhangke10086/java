package com.example.mis.cro.service;

import com.example.mis.cro.entity.PeriodTaskPlanItems;
import com.example.mis.cro.repository.PeriodTaskPlanItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PeriodTaskPlanItemService {
    @Autowired
    private PeriodTaskPlanItemRepository periodTaskPlanItemRepository;

    public void addPlan(PeriodTaskPlanItems periodTaskPlanItems) {

        periodTaskPlanItemRepository.save(periodTaskPlanItems);
    }
    public Page<PeriodTaskPlanItems> findAll(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<PeriodTaskPlanItems> mpsQuery = new Specification<PeriodTaskPlanItems>() {
            @Override
            public Predicate toPredicate(Root<PeriodTaskPlanItems> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page<PeriodTaskPlanItems> mpsPage = periodTaskPlanItemRepository.findAll(mpsQuery, pageable);
        return mpsPage;
    }
    //条件查询
    public List<PeriodTaskPlanItems> QueryByID4Items(String planid) {

        return periodTaskPlanItemRepository.QueryByID4Items(planid);
    }
}
