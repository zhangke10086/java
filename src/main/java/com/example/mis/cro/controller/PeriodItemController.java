package com.example.mis.cro.controller;


import com.example.common.resformat.CommonResult;
import com.example.mis.cro.entity.PeriodItem;
import com.example.mis.cro.service.PeriodItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@Api(value = "期间明细")
public class PeriodItemController {
    @Autowired
    private PeriodItemService periodItemService;
    /**
     *@描述 新增期间明细

     *@参数  [id, fixday, year, month, day, num, type]

     *@返回值  void

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    @CrossOrigin()
    @PostMapping(value = "/mis/addPeriodItem/{id}/{fixday}/{year}/{month}/{day}/{num}/{type}")
    @ApiOperation(value = "新增期间明细",notes = "根据参数添加期间明细")
    public CommonResult Add(@PathVariable String id, @PathVariable String fixday, @PathVariable String year, @PathVariable String month, @PathVariable String day
    , @PathVariable String num, @PathVariable String type) {
        CommonResult result = new CommonResult();
        int Num = Integer.parseInt(num);
        try {
            switch (id) {
                case "1":
                    periodItemService.addYear(year, Num, type);
                    break;
                case "2":
                    periodItemService.addSeason(year, Num, type);
                    break;
                case "3":
                    periodItemService.addMonth(year, month, Num, type);
                    break;
                case "4":
                    periodItemService.addtendays(year, month, Num, type);
                    break;
                case "5":
                    periodItemService.addWeek(year, month, day, Num, type);
                    break;
                case "6":
                    periodItemService.addFixday(year, month, day, fixday, Num, type);
                    break;
                case "7":
                    periodItemService.addOther(Num, type);
                    break;
                default:
                    break;
            }
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }

    /**
     *@描述 根据id查找期间明细

     *@参数  [id]

     *@返回值  java.util.List<com.inspur.momservices.mis.entity.PeriodItem>

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    @CrossOrigin
    @PostMapping(value = "/mis/findPeriodItem/{id}")
    @ApiOperation(value = "查找期间明细",notes = "根据id查找期间明细")
    public CommonResult find(@PathVariable String id  ) {
        CommonResult result = new CommonResult();
        try {
            result.setData(periodItemService.findById(id));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }


    /**
     *@描述 查找全部期间明细

     *@参数  []

     *@返回值  java.util.List<com.inspur.momservices.mis.entity.PeriodItem>

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    @CrossOrigin
    @GetMapping(value = "/mis/findAllPeriodItem")
    @ApiOperation(value = "查找全部期间明细")
    public List<PeriodItem> findAll() {
        return periodItemService.findAll();
    }

    @GetMapping(value = "/mis/findAllPeriodItemHelp/{id}")
    @ApiOperation("获取期间明细")
    @CrossOrigin("http://localhost:4200")
    public List<PeriodItem> getperiode(@PathVariable String id) {
        try {
            System.out.println(periodItemService.findById(id));
            return periodItemService.findById(id);
        } catch (Exception e) {

            return null;
        }
    }

    /**
     *@描述  根据id删除期间明细

     *@参数  [id]

     *@返回值  com.inspur.momservices.common.CommonResult

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    @CrossOrigin
    @PostMapping(value = "/mis/deletePeriodItem/{id}")
    @ApiOperation(value = "根据id删除期间明细")

    public CommonResult deleteById(@PathVariable String id)
    {
        CommonResult result = new CommonResult();

        try {
            periodItemService.deleteById(id);
            return result;
        } catch (Exception e) {
            if(e.toString().contains("EmptyResultDataAccessException")) {
                result.setState(501);
            }else {
                result.setState(500);
            }
            e.printStackTrace();
            result.setMsg("失败");
            return result;
        }
    }

    /**
     *@描述   批量修改期间明细

     *@参数  [periodItem]

     *@返回值  com.inspur.momservices.common.CommonResult

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    @CrossOrigin
    @PostMapping(value = "/mis/updateItem")
    @ApiOperation(value = "修改期间明细")
    public CommonResult update(@RequestBody PeriodItem[] periodItem){
        CommonResult result = new CommonResult();
        try {
            List<PeriodItem> list = Arrays.asList(periodItem);
            periodItemService.updateAll(list);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }
    /**
     *@描述   删除全部期间明细

     *@参数  [periodItem]

     *@返回值  com.inspur.momservices.common.CommonResult

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    @CrossOrigin
    @PostMapping(value = "/mis/deleteAllItem")
    @ApiOperation(value = "删除所有期间明细")
    public CommonResult deleteAll(@RequestBody PeriodItem[] periodItem){

        CommonResult result = new CommonResult();
        try {
            List<PeriodItem> list = Arrays.asList(periodItem);
            periodItemService.deleteAll(list);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }
}
