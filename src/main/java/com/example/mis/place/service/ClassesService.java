package com.example.mis.place.service;

import com.example.mis.place.entity.Classes;
import com.example.mis.place.repository.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClassesService {
        @Autowired
        private ClassesRepository classesRepository ;

        public List<Classes> findAll(){
            return classesRepository.findAll();
        }
        public void add(Classes classes){
            classesRepository.save(classes);
        }
        public void deleteByid(String id){
            classesRepository.deleteById(id);
        }
        public Classes findByid(String id){
            return classesRepository.findById(id).get();
        }

}
