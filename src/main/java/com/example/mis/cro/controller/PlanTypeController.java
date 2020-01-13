package com.example.mis.cro.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.common.resformat.CommonResult;
import com.example.common.resformat.JqGridDataFormater;
import com.example.mis.cro.entity.PlanType;
import com.example.mis.cro.service.PlanTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "计划类型")
public class PlanTypeController {
    @Autowired
    private PlanTypeService planTypeService;

    /**
     *@描述   新增计划类型

     *@参数  [planType]

     *@返回值  com.inspur.momservices.common.CommonResult

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    @CrossOrigin
    @PostMapping(value = "/mis/addPlanType")
    @ApiOperation(value = "新增计划类型",notes = "根据参数添加计划类型")
    public CommonResult addProject1(@RequestBody PlanType planType) {
        CommonResult result = new CommonResult();
        try {
            planTypeService.add(planType);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }
    /**
     *@描述 根据id查找计划类型

     *@参数  [id]

     *@返回值  com.inspur.momservices.common.CommonResult

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    @CrossOrigin
    @GetMapping(value = "/mis/findPlanType")
    @ApiOperation(value = "查找计划类型",notes = "根据id查找计划类型")
    public CommonResult findProjectById(String id  ) {

        CommonResult result = new CommonResult();
        try {
            PlanType planType = planTypeService.findById(id);
            result.setData(planType);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }

    /**
     *@描述 查找全部计划类型

     *@参数  []

     *@返回值  java.util.List<com.inspur.momservices.mis.entity.PlanType>

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    @CrossOrigin
    @GetMapping(value = "/mis/findAllPlanType")
    @ApiOperation(value = "查找全部计划类型")
    public List<PlanType> findAll( )
    {
        return planTypeService.findAll();
    }

    /**
     *@描述 分页查找全部计划类型

     *@参数  [rows, page]

     *@返回值  com.inspur.momservices.common.JqGridDataFormater<com.inspur.momservices.mis.entity.PlanType>

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    @CrossOrigin
    @GetMapping(value = "/mis/findAllPlanType_page")
    @ApiOperation(value = "查找全部计划类型-分页")
    public JqGridDataFormater<PlanType> getplantype_page(int rows, int page) {
        JqGridDataFormater<PlanType> result = new JqGridDataFormater<PlanType>();
        {
            Page<PlanType> list = planTypeService.findAll(rows, page - 1);
            int a = list.getTotalPages();
            int b = list.getNumber();
            int c = list.getNumberOfElements();
            int d = list.getSize();
            Long e = list.getTotalElements();
            result.setPage(page);
            result.setTotal(a);
            result.setRecords(e.intValue());
            result.setRows(list.getContent());
            return result;

        }

    }
    /**
     *@描述  删除计划类型

     *@参数  [planType]

     *@返回值  com.inspur.momservices.common.CommonResult

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    @CrossOrigin
    @PostMapping(value = "/mis/deletPlanType")
    @ApiOperation(value = "删除计划类型")

    public CommonResult deleteById(@RequestBody PlanType planType)
    {
        CommonResult result = new CommonResult();
        try {
            planTypeService.delete(planType);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }
    /**
     *@描述 修改计划类型

     *@参数  [planType]

     *@返回值  com.inspur.momservices.common.CommonResult

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    @CrossOrigin
    @PutMapping(value = "/mis/updatePlanType")
    @ApiOperation(value = "修改计划类型")
    public CommonResult update(@RequestBody PlanType planType){
        CommonResult result = new CommonResult();
        try {
            planTypeService.update(planType);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }
    @CrossOrigin
    @PostMapping(value = "/mis/plantypetest")
    @ApiOperation(value = "修改计划类型")
    protected PlanType doPost(@RequestBody Map<String,Object> jsondata) {
        String json = JSON.toJSONString(jsondata);
        PlanType planType1 = JSONObject.parseObject(json, PlanType.class);
        System.out.println(json);
        System.out.println(planType1.toString());
        return planType1;


    }
}
