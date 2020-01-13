package com.example.common.login.repository;

import com.example.common.login.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,String> {

    Role findById(Integer id);


    Role findByName(String name);

}
