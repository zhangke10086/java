package com.example.mis.cro.service;

import com.example.mis.cro.entity.PlanType;
import com.example.mis.cro.repository.PlanTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PlanTypeService {
    @Autowired
    private PlanTypeRepository planTypeRepository;
    /**
     *@描述   新增

     *@参数  [planType]

     *@返回值  void

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public void add(PlanType planType){
        planTypeRepository.save(planType);
    }
    /**
     *@描述   查找全部计划类型

     *@参数  []

     *@返回值  java.util.List<com.inspur.momservices.mops.entity.PlanType>

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public List<PlanType> findAll()
    {
        Sort.Order order = Sort.Order.asc("code");
        Sort sort = Sort.by(order);
        return planTypeRepository.findAll(sort);
    }

    /**
     *@描述   分页查找计划类型

     *@参数  [size, page]

     *@返回值  org.springframework.data.domain.Page<com.inspur.momservices.mops.entity.PlanType>

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public Page<PlanType> findAll(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<PlanType> mpsQuery = new Specification<PlanType>() {
            @Override
            public Predicate toPredicate(Root<PlanType> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
            Page<PlanType> mpsPage = planTypeRepository.findAll(mpsQuery, pageable);
            return mpsPage;
    }
    /**
     *@描述   根据id查找计划类型

     *@参数  [id]

     *@返回值  com.inspur.momservices.mops.entity.PlanType

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public PlanType findById(String id)
    {
        return planTypeRepository.findById(id).get();
    }
    /**
     *@描述   删除计划类型

     *@参数  [planType]

     *@返回值  void

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public void delete(PlanType planType)
    {
        planTypeRepository.delete(planType);
    }

    /**
     *@描述   修改计划类型

     *@参数  [planType]

     *@返回值  void

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public void update(PlanType planType){
        planTypeRepository.save(planType);
}
    public PlanType findByName(String name){
        return planTypeRepository.findByName(name);
    }

}
