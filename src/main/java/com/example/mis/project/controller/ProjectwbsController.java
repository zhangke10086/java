/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.mis.project.controller;

import com.example.common.resformat.CommonResult;
import com.example.mis.project.entity.ProjectWbs;
import com.example.mis.project.service.ProjectService;
import com.example.mis.project.service.ProjectwbsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@Api(value = "操作任务")
@Transactional
public class ProjectwbsController {
    @Autowired
        private ProjectwbsService projectwbsservice;

    @GetMapping(value = "/projectwbs/getprojectwbshelp/{id}")
    @ApiOperation(value = "任务帮助", notes = "任务帮助")
    public List<ProjectWbs> getprojectwbss(@PathVariable String id) {
        CommonResult result = new CommonResult();
        try
        {
            List<ProjectWbs> list =projectwbsservice.findByid(id);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return null;
        }
    }

    /**
     *@描述   根据id删除任务

     *@参数  [factoryid]

     *@返回值  com.inspur.momservices.common.CommonResult

     *@创建人  zhangke

     *@创建时间  2019/10/23

     *@修改人和其它信息

     */
    @PostMapping(value = "/projectwbs/deleteById")
    @ApiOperation(value = "根据id删除任务")
    public CommonResult deleteById(@RequestBody String factoryid) {
        CommonResult result = new CommonResult();
        try {
            projectwbsservice.deleteByid(factoryid);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(e.hashCode());
            result.setMsg("失败");
            return result;
        }
    }
    /**
     *@描述   批量增加或修改任务

     *@参数  [projectwbss]

     *@返回值  com.inspur.momservices.common.CommonResult

     *@创建人  zhangke

     *@创建时间  2019/10/23

     *@修改人和其它信息

     */
    @PostMapping(value = "/projectwbs/addOrUpdatePlace")
    @ApiOperation(value = "批量增加或修改任务")
    public CommonResult AddOrUpdatePlace(@RequestBody List<ProjectWbs> projectwbss) {
        CommonResult result = new CommonResult();
        try {
            projectwbsservice.addOrUpdatePlace(projectwbss);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }



    @PostMapping(value = "/projectwbs/collect")
    @ApiOperation(value = "归集")
    public List<ProjectWbs> collect(@RequestBody Map<String, Object> jsonData) throws ParseException {
           return projectwbsservice.collect(jsonData);
    }
}
