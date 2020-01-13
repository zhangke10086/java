/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.mis.project.controller;

import com.example.common.resformat.CommonResult;
import com.example.mis.project.entity.Project;
import com.example.mis.project.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@Api(value = "操作项目")
@Transactional
public class ProjectController {
    @Autowired
        private ProjectService projectservice;

    @GetMapping(value = "/project/getprojecthelp")
    @ApiOperation(value = "项目帮助", notes = "项目帮助")
    public List<Project> getprojects() {
        CommonResult result = new CommonResult();
        try
        {
            List<Project> list =projectservice.findAll();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return null;
        }
    }

    /**
     *@描述   根据id删除项目

     *@参数  [factoryid]

     *@返回值  com.inspur.momservices.common.CommonResult

     *@创建人  zhangke

     *@创建时间  2019/10/23

     *@修改人和其它信息

     */
    @PostMapping(value = "/project/deleteById")
    @ApiOperation(value = "根据id删除项目")
    public CommonResult deleteById(@RequestBody String factoryid) {
        CommonResult result = new CommonResult();
        try {
            projectservice.deleteByid(factoryid);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(e.hashCode());
            result.setMsg("失败");
            return result;
        }
    }
    /**
     *@描述   批量增加或修改项目

     *@参数  [projects]

     *@返回值  com.inspur.momservices.common.CommonResult

     *@创建人  zhangke

     *@创建时间  2019/10/23

     *@修改人和其它信息

     */
    @PostMapping(value = "/proejct/addOrUpdatePlace")
    @ApiOperation(value = "批量增加或修改项目")
    public CommonResult AddOrUpdatePlace(@RequestBody List<Project> projects) {
        CommonResult result = new CommonResult();
        try {
            projectservice.addOrUpdatePlace(projects);
            return result;
        } catch (Exception e) {
            System.out.println("-----------------------------------------------------------------");
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }

}
