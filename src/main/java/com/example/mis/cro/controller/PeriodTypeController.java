package com.example.mis.cro.controller;


import com.example.common.resformat.CommonResult;
import com.example.common.resformat.JqGridDataFormater;
import com.example.mis.cro.entity.PeriodType;
import com.example.mis.cro.service.PeriodTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "计划类型")
public class PeriodTypeController {
    @Autowired
    private PeriodTypeService periodTypeService;

    /**
     *@描述 新增期间类型

     *@参数  [periodType]

     *@返回值  com.inspur.momservices.common.CommonResult

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    @CrossOrigin
    @PostMapping(value = "/mis/addPeriodType")
    @ApiOperation(value = "新增期间类型",notes = "根据参数添加期间类型")
    public CommonResult add(@RequestBody PeriodType periodType) {

        CommonResult result = new CommonResult();
        try {
            periodTypeService.add(periodType);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }
    /**
     *@描述 查找全部期间类型

     *@参数

     *@返回值  List<PeriodType>

     *@创建人  zhangke

     *@创建时间  2019/9/30

     *@修改人和其它信息

     */
    @CrossOrigin
    @GetMapping(value = "/mis/findAllPeriodType")
    @ApiOperation(value = "查找全部期间类型")
    public List<PeriodType> findAll( ) {
        List<PeriodType> list = periodTypeService.findAll();
        return list;
    }

    /**
     *@描述 分页查找全部期间类型

     *@参数  [rows, page]

     *@返回值  com.inspur.momservices.common.JqGridDataFormater<com.inspur.momservices.mis.entity.PeriodType>

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    @CrossOrigin
    @GetMapping(value = "/mis/findAllPeriodType_page")
    @ApiOperation(value = "查找全部期间类型-分页")
    public JqGridDataFormater<PeriodType> getProcessplanitem_page(int rows, int page) {
        JqGridDataFormater<PeriodType> result = new JqGridDataFormater<PeriodType>();
        {
            Page<PeriodType> list = periodTypeService.findAll( rows, page - 1);
            int a = list.getTotalPages();
            Long e = list.getTotalElements();
            result.setPage(page);
            result.setTotal(a);
            result.setRecords(e.intValue());
            result.setRows(list.getContent());
            return result;
        }

    }

    /**
     *@描述   删除期间类型

     *@参数  [periodType]

     *@返回值  com.inspur.momservices.common.CommonResult

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    @CrossOrigin
    @PostMapping(value = "/mis/deletePeriodType")
    @ApiOperation(value = "删除期间类型")

    public CommonResult deleteById(@RequestBody PeriodType periodType)
    {
        CommonResult result = new CommonResult();
        try {
            periodTypeService.delete(periodType);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }
    /**
     *@描述  修改期间类型

     *@参数  [periodType]

     *@返回值  com.inspur.momservices.common.CommonResult

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    @CrossOrigin
    @PutMapping(value = "/mis/updatePeriodType")
    @ApiOperation(value = "修改期间类型")
    public CommonResult update(@RequestBody PeriodType periodType){
        CommonResult result = new CommonResult();
        try {
            periodTypeService.update(periodType);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }
}
