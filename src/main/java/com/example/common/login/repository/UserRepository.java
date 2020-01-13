package com.example.common.login.repository;

import com.example.common.login.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,String> {
    public Page<User> findAll(Specification<User> spec, Pageable pageable);
    public User findByCode(String code);
}
