/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.mis.place.service;

import com.example.mis.place.entity.place;
import com.example.mis.place.repository.placeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaceService {
    @Autowired
    private placeRepository placerepository;
    public List<place> findAll(){

        //   return organizationsRepository.findAll();

        return  placerepository.findAll();
    }
    public List<place> findByState(){

        return  placerepository.findByState('1');
    }

    public List<place> findByfactoryid(String factoryid){
        return placerepository.findByfactoryid(factoryid);
    }
    public void addOrUpdatePlace(List<place> mofmplaces){
        placerepository.saveAll(mofmplaces);
    }
    public void deleteByid(String id){
        placerepository.deleteById(id);
    }

}
