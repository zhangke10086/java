/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.mis.place.controller;

import com.example.common.resformat.CommonResult;
import com.example.mis.place.entity.Factory;
import com.example.mis.place.service.FactoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
* @Description: 工厂操作
* @Param:
* @return:
* @Author: hujinb
* @Date:  2019年10月25日
*/

@RestController
@Api(value = "操作工厂")
public class FactoryController {

    @Autowired
   private FactoryService factoryService;

    @GetMapping(value = "/factory/getfactory")
    @ApiOperation("获取工厂列表")
    @CrossOrigin()
    public List<Factory> getfactory() {
            List<Factory> all = factoryService.findAll();
            return all;

    }
    @GetMapping(value = "/factory/findByComId")
    @ApiOperation("根据公司获取工厂列表")
    @CrossOrigin()
    public CommonResult getByComId(String id){
        CommonResult result = new CommonResult();
        try {
            List<Factory> byComId = this.factoryService.findByComId(id);
            result.setData(byComId);
            result.setState(200);
            result.setMsg("操作成功");
            return result;
        } catch (Exception e) {
            result.setState(500);
            result.setMsg("操作失败");
            return  result;
        }
    }
    @PutMapping(value = "/factory/updateFactory")
    @ApiOperation("修改保存工厂列表")
    @CrossOrigin()
    public CommonResult updateFactory(@RequestBody Factory factory){
        CommonResult result = new CommonResult();
        try {
            factoryService.save(factory);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }

    
    @PostMapping(value = "/factory/deleteByid")
    @ApiOperation("删除")
    @CrossOrigin()
    public CommonResult deleteById(@RequestBody String id){
        CommonResult result = new CommonResult();
        try {
            factoryService.deleteById(id);
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("删除失败");
            return result;
        }
    }

    @PostMapping(value = "/factory/filterState")
    @ApiOperation("过滤状态停用的工厂")
    @CrossOrigin()
    public CommonResult findAllFilter(@RequestBody String state){
        CommonResult result = new CommonResult();
        char a=state.charAt(1);
        List<Factory> all = factoryService.findAll();
        List<Factory> filter = new ArrayList<>();
        try {
            for (Factory factory : all) {
                if(factory.getState().equals(a)){
                    filter.add(factory);
                }
            }
            result.setData(filter);
            result.setState(200);
            result.setMsg("操作成功");
            return result;
        } catch (Exception e) {
            result.setState(500);
            result.setMsg("操作失败");
            return  result;
        }

    }
    @PostMapping(value = "/factory/addAndUpdate")
    @ApiOperation(value = "批量增加或修改场所")
    @CrossOrigin()
    public CommonResult AddOrUpdatePlace(@RequestBody List<Factory> factories) {
        CommonResult result = new CommonResult();
        try {
            factoryService.addAll(factories);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("删除失败");
            return result;
        }
    }

}


