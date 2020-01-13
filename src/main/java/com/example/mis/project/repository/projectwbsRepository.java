/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.mis.project.repository;


import com.example.mis.project.entity.ProjectWbs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface projectwbsRepository extends JpaRepository<ProjectWbs, String> {
        public List<ProjectWbs> findByState(Character state);
        public ProjectWbs findByName(String name);
        @Query(value="select * from misprojectwbs  where projectid = ?1",nativeQuery = true)
        public List<ProjectWbs> find(String id);
}
