/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.mis.place.service;


import com.example.mis.place.entity.Factory;
import com.example.mis.place.repository.FactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FactoryService {
    @Autowired
    private FactoryRepository factoryRepository;


    public List<Factory> findAll(){

     //   return organizationsRepository.findAll();

        return  factoryRepository.findAll();
    }

    public void addFactory(Factory factory){
        factoryRepository.save(factory);
    }

    public List<Factory> findByComId(String companyid) {
         return factoryRepository.findByComId(companyid);
    }

    public void save(Factory factory) {
         factoryRepository.save(factory);
    }

    public void deleteById(String id) {
        factoryRepository.deleteById(id);
    }

    public void addAll(List<Factory> factories) {
        factoryRepository.saveAll(factories);
    }
//    public List<Factory_vw> getAll(){
//        return factory_vwRepository.findAll();
//    }
}
