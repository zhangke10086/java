/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.mis.project.service;

import com.example.mis.project.entity.Project;
import com.example.mis.project.repository.projectRepository;
import com.example.mis.project.repository.projectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectService {
    @Autowired
    private projectRepository projectrepository;
    public List<Project> findAll(){

        //   return organizationsRepository.findAll();

        return  projectrepository.findAll();
    }
    public List<Project> findByState(){

        return  projectrepository.findByState('1');
    }

    public void addOrUpdatePlace(List<Project> mofmprojects){
        projectrepository.saveAll(mofmprojects);
    }
    public void deleteByid(String id){
        projectrepository.deleteById(id);
    }
    public Project findByName(String name){
        return projectrepository.findByName(name);
    }

}
