package com.example.mis.cro.repository;

import com.example.mis.cro.entity.PeriodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional

public interface PeriodItemRepository extends JpaRepository<PeriodItem, String> {
    @Query(value="select * from misperiod  where periodtypeid = ?1",nativeQuery = true)
    public List<PeriodItem> find(String id);
}
