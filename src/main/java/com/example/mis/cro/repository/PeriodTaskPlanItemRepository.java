package com.example.mis.cro.repository;

import com.example.mis.cro.entity.PeriodTaskPlanItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeriodTaskPlanItemRepository extends JpaRepository<PeriodTaskPlanItems, String>, JpaSpecificationExecutor<PeriodTaskPlanItems> {

    @Query(value="select t.* from jyf_mopsperiodtaskplanitems t where t.parent_id = ?1",nativeQuery = true)
    List<PeriodTaskPlanItems> QueryByID4Items(String ParentID);

}
