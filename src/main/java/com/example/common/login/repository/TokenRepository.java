package com.example.common.login.repository;

import com.example.common.login.entity.Token;
import com.example.common.login.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TokenRepository extends JpaRepository<Token,String> {
    @Query(nativeQuery = true, value="select * from mistoken  where userid = ?1")
    public Token findToken(String userid);
    @Modifying
    @Query(value = "delete from mistoken where userid=?1",nativeQuery = true)
    public void deleteByUserid(String userid);
}
