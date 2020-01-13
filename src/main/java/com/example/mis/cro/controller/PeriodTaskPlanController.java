package com.example.mis.cro.controller;

import com.example.common.formatdate.DateString;
import com.example.common.resformat.CommonResult;
import com.example.common.resformat.JqGridDataFormater;
import com.example.mis.cro.entity.PeriodTaskPlan;
import com.example.mis.cro.entity.PeriodTaskPlanItems;
import com.example.mis.cro.service.PeriodTaskPlanItemService;
import com.example.mis.cro.service.PeriodTaskPlanService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin()
@RestController
public class PeriodTaskPlanController {
    @Autowired
    private PeriodTaskPlanService periodTaskPlanService;
    @Autowired
    private PeriodTaskPlanItemService periodTaskPlanItemService;

    //保存数据
    @PostMapping(value = "/mis/PeriodTaskPlanSave")
    @ApiOperation(value = "保存数据", notes = "保存数据")
    public CommonResult addPlantp(@RequestBody PeriodTaskPlan periodTaskPlan) {

        CommonResult result = new CommonResult();
        try {
            DateString date = new DateString();
            String code = "PT"+date.CurrentDateStrinbg();
            periodTaskPlan.setBillno(code);
            System.out.println(periodTaskPlan);
            periodTaskPlanService.addPlan(periodTaskPlan);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }

    @PostMapping(value = "/mis/addPeriodTaskPlans")
    @ApiOperation("批量新增")
    @ResponseBody
    public CommonResult addWorkorderlist(@RequestBody List<PeriodTaskPlan> periodTaskPlans) {
        CommonResult result = new CommonResult();
        try {
            periodTaskPlanService.addAll(periodTaskPlans);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }

    //删除记录
//    @RequestMapping(value="/mis/PeriodTaskPlanDel",method=RequestMethod.POST)
    @DeleteMapping(value="/mis/PeriodTaskPlanDel")
    @ApiOperation(value = "删除记录", notes = "删除记录")
    public CommonResult deletePlanById(@RequestBody Map<String, Object> jsonData) {
        CommonResult result = new CommonResult();
//        System.out.println("id:"+id);
        String id= jsonData.get("id").toString();
        System.out.println("id:"+id);
        try {
            periodTaskPlanService.deletePlanById(id);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }
//删除
    @DeleteMapping(value = "/mis/PeriodTaskPlanDel/{id}")
    public CommonResult deletePlanById(@PathVariable(name = "id", required = true) String id) {
        CommonResult result = new CommonResult();

        try {
            periodTaskPlanService.deletePlanById(id);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }

    //更新记录
    @PutMapping(value="/mis/PeriodTaskPlanUp")
    @ApiOperation(value = "更新记录", notes = "更新记录")
    public CommonResult updatePlanByID(@RequestBody PeriodTaskPlan periodTaskPlan){
        CommonResult result = new CommonResult();

        try {
            periodTaskPlanService.updatePlan(periodTaskPlan);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }




    //查询所有记录
    @GetMapping(value = "/mis/PeriodTaskPlanQuery")
    @ApiOperation(value = "查询所有记录", notes = "查询所有记录")
    private List<PeriodTaskPlan> QueryPlanAll(){
           return periodTaskPlanService.findAll();
    }







//前端查询

    @CrossOrigin
    @GetMapping("/mis/PeriodTaskPlanFilterStr")
    @ResponseBody

    private Map<String, Object> HelpColumns() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code","billno");
        map.put("name","单据编号");
        map.put("type","text");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("code","billname");
        map2.put("name","单据名称");
        map2.put("type","text");
//        Map<String,Object>  map3 = new HashMap<String,Object>();
//        map3.put("code","opname");
//        map3.put("name","工序名称");
//        map3.put("type","text");
        list.add(map);
        list.add(map2);
//        list.add(map3);
        Map<String, Object> helpmap = new HashMap<String, Object>();
        helpmap.put("columns",list);
        return helpmap;
    }


    //前端条件查询
    @CrossOrigin
    @PostMapping(value = "/mis/periodtaskplanfilter")
    @ApiOperation(value = "前端条件查询", notes = "前端条件查询")
    private JqGridDataFormater<PeriodTaskPlan> momQueryList(@RequestBody Map<String, Object> jsonData){
        JqGridDataFormater<PeriodTaskPlan> result = new JqGridDataFormater<PeriodTaskPlan>();
        try{
            Page<PeriodTaskPlan> list = periodTaskPlanService.QueryList(jsonData);
            Long count = list.getTotalElements();
            result.setRecords(count.intValue());
            result.setRows(list.getContent());
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
            return result;
        }

    }


    //条件查询子表
    @GetMapping("/mis/PeriodTaskPlanQueryByID4Items")
    @ResponseBody
    @ApiOperation(value = "条件查询子表", notes = "条件查询子表")

    public List<PeriodTaskPlanItems> QueryByID4Items(@RequestParam(value = "planid") String planid){
        return  periodTaskPlanItemService.QueryByID4Items(planid);
    }



    @CrossOrigin
    @GetMapping(value = "/mis/PeriodTaskPlanPage")
    @ApiOperation("获取周计划-分页")
    public JqGridDataFormater<PeriodTaskPlan> findPage( int rows, int page) {
        // CommonResult result = new CommonResult();
        JqGridDataFormater<PeriodTaskPlan> result = new JqGridDataFormater<PeriodTaskPlan>();
        try {
            Page<PeriodTaskPlan> list = periodTaskPlanService.findAllByPaging( rows, page-1);
            int a = list.getTotalPages();
            int b = list.getNumber();
            int c = list.getNumberOfElements();
            int d= list.getSize();
            Long e = list.getTotalElements();
            result.setPage(page);
            result.setTotal(a);
            result.setRecords(e.intValue());
            result.setRows(list.getContent());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            //result.setState(500);
            return result;
        }
    }
    /**
     * @描述 工单导入模板生成
     * @参数 [request, response]
     * @返回值 void
     * @创建人 zhangke
     * @创建时间 2019/10/12
     * @修改人和其它信息
     */
    @ApiOperation(value = "工单模板下载")
    @CrossOrigin()
    @GetMapping(value = "/mis/Download")
    public void excelDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //表头数据
        String[] header = {"计划编号","计划名称", "计划类型", "期间类型", "项目", "任务",  "计划开始日期", "计划结束日期", "性质",
                "制定方式","备注"};
        String[] content = {"", "实训计划一",  "计划类型1", "公历周度", "开发实训","编码", "2019/11/20", "2019/12/20",
                "正常", "导入", ""};
        periodTaskPlanService.generateWorkorderExcel(request, response, "周期计划导入模板.xlsx", header, content);
    }

    /**
     * @描述 读取工单模板
     * @参数 [file]
     * @返回值 java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @创建人 zhangke
     * @创建时间 2019/10/14
     * @修改人和其它信息
     */
    @CrossOrigin
    @ApiOperation(value = "读取工单模板")
    @PostMapping(value = "/mis/Import")

    public List<PeriodTaskPlan> ExcelImport(@RequestBody MultipartFile file) throws IOException, IndexOutOfBoundsException {
        System.out.println(file.getOriginalFilename());
        System.out.println("导入成功");
        return periodTaskPlanService.readExcel(file);

    }


}
