/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.mis.project.service;

import com.example.mis.project.entity.Project;
import com.example.mis.project.entity.ProjectWbs;
import com.example.mis.project.repository.projectwbsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProjectwbsService {
    @Autowired
    private projectwbsRepository projectwbsrepository;
    public List<ProjectWbs> findAll(){

        //   return organizationsRepository.findAll();

        return  projectwbsrepository.findAll();
    }
    public List<ProjectWbs> findByState(){

        return  projectwbsrepository.findByState('1');
    }
    
    public void addOrUpdatePlace(List<ProjectWbs> mofmprojectwbss){
        projectwbsrepository.saveAll(mofmprojectwbss);
    }
    public void deleteByid(String id){
        projectwbsrepository.deleteById(id);
    }
    public ProjectWbs findByName(String name){
        return projectwbsrepository.findByName(name);
    }
    public List<ProjectWbs> findByid(String id){
        return projectwbsrepository.find(id);
    }


    public List<ProjectWbs> collect(Map<String,Object> jsonData) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       Date start = formatter.parse(jsonData.get("planstartdate").toString());
       Date end = formatter.parse(jsonData.get("planenddate").toString());
       List<ProjectWbs> projectWbs = projectwbsrepository.findAll();
       List<ProjectWbs> result = new ArrayList<ProjectWbs>();
       for(ProjectWbs projectWbs1:projectWbs){
           Date start_wbs =projectWbs1.getPlanstartdate();
           Date end_wbs =projectWbs1.getPlanenddate();
           if(start.compareTo(start_wbs)<0 && end.compareTo(end_wbs)>0){
               result.add(projectWbs1);
           }
       }
       return result;
    }
}
