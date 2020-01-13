/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.mis.place.controller;

import com.example.common.resformat.CommonResult;
import com.example.mis.place.entity.place;
import com.example.mis.place.service.PlaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@Api(value = "操作场所")
@Transactional
public class PlaceController {
    @Autowired
        private PlaceService mofmplaceservice;

    @GetMapping(value = "/place/getplacehelp")
    @ApiOperation(value = "场所帮助", notes = "场所帮助")
    public List<place> getplaces() {
        CommonResult result = new CommonResult();
        try
        {
            List<place> list =mofmplaceservice.findAll();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return null;
        }
    }
    /**
     *@描述  根据factory查找场所

     *@参数  [factoryid]

     *@返回值  java.util.List<com.inspur.momservices.mofm.entity.Mofmplace>

     *@创建人  zhangke

     *@创建时间  2019/10/23

     *@修改人和其它信息

     */
    @PostMapping(value = "/place/findById")
    @ApiOperation(value = "根据factory查找场所")
    public List<place> getplacesByfactoryId(@RequestBody String factoryid) {
        CommonResult result = new CommonResult();
        try
        {
            List<place> list =mofmplaceservice.findByfactoryid(factoryid);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return null;
        }
    }
    /**
     *@描述   根据id删除场所

     *@参数  [factoryid]

     *@返回值  com.inspur.momservices.common.CommonResult

     *@创建人  zhangke

     *@创建时间  2019/10/23

     *@修改人和其它信息

     */
    @PostMapping(value = "/place/deleteById")
    @ApiOperation(value = "根据id删除场所")
    public CommonResult deleteById(@RequestBody String factoryid) {
        CommonResult result = new CommonResult();
        try {
            mofmplaceservice.deleteByid(factoryid);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(e.hashCode());
            result.setMsg("失败");
            return result;
        }
    }
    /**
     *@描述   批量增加或修改场所

     *@参数  [mofmplaces]

     *@返回值  com.inspur.momservices.common.CommonResult

     *@创建人  zhangke

     *@创建时间  2019/10/23

     *@修改人和其它信息

     */
    @PostMapping(value = "/place/addOrUpdatePlace")
    @ApiOperation(value = "批量增加或修改场所")
    public CommonResult AddOrUpdatePlace(@RequestBody List<place> mofmplaces) {
        CommonResult result = new CommonResult();
        try {
            mofmplaceservice.addOrUpdatePlace(mofmplaces);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }

}
