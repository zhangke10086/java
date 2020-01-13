package com.example.mis.cro.service;
import com.example.mis.cro.entity.PeriodType;
import com.example.mis.cro.repository.PeriodTypeRepository;
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
public class PeriodTypeService {
    @Autowired
    PeriodTypeRepository periodTypeRepository;

    /**
     *@描述   新增期间类型

     *@参数  [periodType]

     *@返回值  void

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public void add(PeriodType periodType){
        periodTypeRepository.save(periodType);
    }

    /**
     *@描述   查找全部期间类型

     *@参数  []

     *@返回值  java.util.List<com.inspur.momservices.mops.entity.PeriodType>

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public List<PeriodType> findAll()
    {
        Sort.Order order = Sort.Order.asc("code");
        Sort sort = Sort.by(order);
        return periodTypeRepository.findAll(sort);
    }

    /**
     *@描述   分页查找期间类型

     *@参数  [size, page]

     *@返回值  org.springframework.data.domain.Page<com.inspur.momservices.mops.entity.PeriodType>

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public Page<PeriodType> findAll(int size, int page) {
//        Sort sort = new Sort(Sort.Direction.ASC, "code");
        Pageable pageable = PageRequest.of(page, size);
        Specification<PeriodType> mpsQuery = new Specification<PeriodType>() {
            @Override
            public Predicate toPredicate(Root<PeriodType> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page<PeriodType> mpsPage = periodTypeRepository.findAll(mpsQuery, pageable);
        return mpsPage;
    }

    /**
     *@描述   根据id查找期间类型

     *@参数  [id]

     *@返回值  com.inspur.momservices.mops.entity.PeriodType

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public PeriodType findById(String id)
    {
        return periodTypeRepository.findById(id).get();
    }

    /**
     *@描述   删除期间类型

     *@参数  [periodType]

     *@返回值  void

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public void delete(PeriodType periodType){
        periodTypeRepository.delete(periodType);
    }

    /**
     *@描述   修改期间类型

     *@参数  [periodType]

     *@返回值  void

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public void update(PeriodType periodType){
        periodTypeRepository.save(periodType);
}
    public PeriodType findByName(String name){
        return periodTypeRepository.findByName(name);
    }
}
