package com.example.mis.place.controller;

import com.example.mis.place.entity.Classes;
import com.example.mis.place.service.ClassesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@Api(value = "操作教室")
@Transactional
public class ClassesController {
    @Autowired
    private ClassesService classesService;

    @GetMapping(value = "/class/findAll")
    @ApiOperation(value = "查询全部教室")
    public List<Classes> findAll(){
        return classesService.findAll();
    }

    @PostMapping(value = "/class/findById")
    @ApiOperation(value = "根据id查找教室")
    public Classes findById(@RequestBody String id){
        return classesService.findByid(id);
    }

    @PostMapping(value = "/class/deleteById")
    @ApiOperation(value = "根据id删除教室")
    public void DeleteById(@RequestBody String id){
        classesService.deleteByid(id);
    }

    @PostMapping(value = "/class/add")
    @ApiOperation(value = "增加教室")
    public void add(@RequestBody Classes classes){
        classesService.add(classes);
    }

    @PostMapping(value = "/class/update")
    @ApiOperation(value = "修改教室")
    public void update(@RequestBody Classes classes){
        classesService.add(classes);
    }

}
