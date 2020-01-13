package com.example.mis.cro.repository;


import com.example.mis.cro.entity.PeriodTaskPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeriodTaskPlanRepository extends JpaRepository<PeriodTaskPlan, String>, JpaSpecificationExecutor<PeriodTaskPlan> {
//    @Query(value="select * from jyf_mopsperiodtaskplan where id=?1",nativeQuery=true)
//    List<PeriodTaskPlan> getPlanByID(String id);
//
//
//    @Query(value = "select a.billname ,b.batch from jyf_mopsperiodtaskplan a left join jyf_mopsperiodtaskplanitems b on a.id=b.parent_id",nativeQuery = true)
//    List<Object> findByCondition();

    @Query(value="select new map(id,billname) from PeriodTaskPlan ")
    List<Object[]> GetPlanTree();

//    @Query(value = "SELECT COUNT(1) FROM PeriodTaskPlan ")
//    List<Object[]> infoCount GetInfoCount();





}
