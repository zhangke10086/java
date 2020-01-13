package com.example.mis.cro.service;

import com.example.common.formatdate.DateString;
import com.example.mis.cro.entity.PeriodItem;
import com.example.mis.cro.entity.PeriodTaskPlan;
import com.example.mis.cro.entity.PeriodType;
import com.example.mis.cro.entity.PlanType;
import com.example.mis.cro.repository.PeriodTaskPlanRepository;
import com.example.mis.project.entity.Project;
import com.example.mis.project.entity.ProjectWbs;
import com.example.mis.project.service.ProjectService;
import com.example.mis.project.service.ProjectwbsService;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional
public class PeriodTaskPlanService {
    @Autowired
    private PeriodTaskPlanRepository periodTaskPlanRepository;
    @Autowired
    private ProjectwbsService projectwbsService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private PlanTypeService planTypeService;
    @Autowired
    private PeriodTypeService periodTypeService;
    @PersistenceContext
    private EntityManager em;
    //新增
    public PeriodTaskPlan addPlan(PeriodTaskPlan periodTaskPlan) {
        return periodTaskPlanRepository.save(periodTaskPlan);
    }

    //删除
    public void deletePlanById(String id) {

        for (String x : id.split(",")) {
            periodTaskPlanRepository.deleteById(x);
        }
    }

    //更新
    public void updatePlan(PeriodTaskPlan periodTaskPlan) {
        periodTaskPlanRepository.save(periodTaskPlan);
    }



    //分页
    public Page<PeriodTaskPlan> findAll(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<PeriodTaskPlan> mpsQuery = new Specification<PeriodTaskPlan>() {
            @Override
            public Predicate toPredicate(Root<PeriodTaskPlan> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page<PeriodTaskPlan> mpsPage = periodTaskPlanRepository.findAll(mpsQuery, pageable);
        return mpsPage;
    }



    //查询所有
    public List<PeriodTaskPlan> findAll(){

        return periodTaskPlanRepository.findAll();
    }


    public Page<PeriodTaskPlan> QueryList(Map<String, Object> jsonData) {
        Pageable pageable = PageRequest.of(Integer.parseInt(jsonData.get("page").toString()) - 1, Integer.parseInt(jsonData.get("rows").toString()));
        Specification<PeriodTaskPlan> mpsQuery = new Specification<PeriodTaskPlan>() {
            @Override
            public Predicate toPredicate(Root<PeriodTaskPlan> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Date startdate = null;
                Date enddate = null;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                if(!StringUtils.isEmpty(jsonData.get("planstartdate")))
                {
                    try {
                        startdate = simpleDateFormat.parse(jsonData.get("planstartdate").toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if(!StringUtils.isEmpty(jsonData.get("planenddate"))) {
                    try {
                        enddate = simpleDateFormat.parse(jsonData.get("planenddate").toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isEmpty(jsonData.get("billno"))) {

                    predicates.add(criteriaBuilder.like(root.get("billno"), "%" + jsonData.get("billno").toString() + "%"));
                }
                if (!StringUtils.isEmpty(jsonData.get("billname"))) {

                    predicates.add(criteriaBuilder.like(root.get("billname"), "%" + jsonData.get("billname").toString() + "%"));
                }
                if (!StringUtils.isEmpty(jsonData.get("billtypecode"))) {

                    predicates.add(criteriaBuilder.like(root.get("billtype").get("name"), "%" + jsonData.get("billtypecode").toString() + "%"));
                }
                if (!StringUtils.isEmpty(jsonData.get("periodtypecode"))) {

                    predicates.add(criteriaBuilder.like(root.get("periodtype").get("name"), "%" + jsonData.get("periodtypecode").toString() + "%"));
                }
                if (!StringUtils.isEmpty(jsonData.get("mdworkcentercode"))) {

                    predicates.add(criteriaBuilder.like(root.get("mdworkcenter").get("name"), "%" + jsonData.get("mdworkcentercode").toString() + "%"));
                }
                if (!StringUtils.isEmpty(jsonData.get("billstate"))) {

                    predicates.add(criteriaBuilder.like(root.get("billstate"), "%" + jsonData.get("billstate").toString() + "%"));
                }
//                if (!StringUtils.isEmpty(jsonData.get("approvestate"))) {
//
//                    predicates.add(criteriaBuilder.like(root.get("approvestate"), "%" + jsonData.get("approvestate").toString() + "%"));
//                }
                if(null != startdate){
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("planstartdate"),startdate));
                }
                if(null !=  enddate){
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.<Date>get("planenddate"),enddate));
                }


                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return periodTaskPlanRepository.findAll(mpsQuery, pageable);
    }



    public void generateWorkorderExcel(HttpServletRequest request, HttpServletResponse response, String filename, String[] header, String[] content) throws IOException {
        filename = URLEncoder.encode(filename, "UTF-8");
        //声明一个工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        //生成一个表格，设置表格名称为"工单导入表"
        XSSFSheet sheet = workbook.createSheet("工单导入表");
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 居中
        XSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12);//设置字体大小
        font.setBold(true);//粗体
        style.setFont(font);
        //设置表格列宽度为12个字节
        sheet.setDefaultColumnWidth(12);
        //创建第一行表头
        XSSFRow headrow = sheet.createRow(0);
        //遍历添加表头
        for (int i = 0; i < header.length; i++) {
            //创建一个单元格
            XSSFCell cell = headrow.createCell(i);
            //创建一个内容对象
            XSSFRichTextString text = new XSSFRichTextString(header[i]);
            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
            cell.setCellStyle(style);
        }
        //创建第二行
        XSSFRow row = sheet.createRow(1);
        for (int i = 0; i < content.length; i++) {
            //创建一个单元格
            XSSFCell cell = row.createCell(i);
            //创建一个内容对象
            XSSFRichTextString text = new XSSFRichTextString(content[i]);
            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
        }
        String filePath = request.getSession().getServletContext().getRealPath("/") + "workorder";
        FileOutputStream fout = new FileOutputStream(filePath + filename);
        workbook.write(fout);
        fout.close();
        System.out.println("创建完成");
        InputStream inputStream = null;
        ServletOutputStream servletOutputStream = null;
        // 重置response
        response.reset();
        //设置http头信息的内容
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        // 找到文件并下载
        File file = new File(filePath + filename);
        if (!file.exists()) {
            throw new RuntimeException("file do not exist");
        }
        try {
            inputStream = new FileInputStream(file);
            byte[] buf = new byte[4096];
            // 创建输出流
            servletOutputStream = response.getOutputStream();
            int readLength;
            // 读取文件内容并输出到response的输出流中
            while ((readLength = inputStream.read(buf)) != -1) {
                servletOutputStream.write(buf, 0, readLength);
            }
        } catch (IOException e) {
            throw new RuntimeException("download file error");
        } finally {
            try {
                // 关闭ServletOutputStream
                if (servletOutputStream != null) {
                    servletOutputStream.flush();
                    servletOutputStream.close();
                }
                // 关闭InputStream
                if (inputStream != null) {
                    inputStream.close();
                }
                // 删除临时文件
                file.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * @描述 读取导入excel数据
     * @参数 [file]
     * @返回值 java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @创建人 zhangke
     * @创建时间 2019/10/14
     * @修改人和其它信息
     */
    public List<PeriodTaskPlan> readExcel(MultipartFile file) throws IOException, IndexOutOfBoundsException {
        List<PeriodTaskPlan> periodTaskPlans = new ArrayList<PeriodTaskPlan>();
        InputStream inputStream = file.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        try {
            //读取sheet(页)
            for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
                XSSFSheet xssfSheet = workbook.getSheetAt(numSheet);
                if (xssfSheet == null) {
                    continue;
                }
                //读取Row,从第二行开始
                for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                    XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                    if (xssfRow != null) {
                        Map map = new HashMap();
                        List<String> list1 = new ArrayList<String>();
                        //读取列
                        for (int i = 0; i < 17; i++) {
                            if (xssfRow.getCell(i) == null) {
                                list1.add("");
                            } else {
                                XSSFCell cell = xssfRow.getCell(i);
                                if (cell.getCellType() == CellType.STRING) {
                                    list1.add(cell.getStringCellValue());
                                }
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                        Date d = cell.getDateCellValue();
                                        DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");
                                        list1.add(df2.format(d));
                                    } else {
                                        cell.setCellType(CellType.STRING);
                                        list1.add(cell.getStringCellValue());
                                    }
                                }
                            }
                        }
                        periodTaskPlans.add(this.Check(list1));
                    }
                }
            }
            return periodTaskPlans;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @描述 对导入模板进行校验
     * @参数 [list]
     * @返回值 java.util.List<java.lang.String>
     * @创建人 zhangke
     * @创建时间 2019/10/14
     * @修改人和其它信息
     */
    public PeriodTaskPlan Check(List<String> list) throws ParseException {
        PeriodTaskPlan periodTaskPlan = new PeriodTaskPlan();
        if (list.get(0).isEmpty()) {
            DateString date = new DateString();
            String code = "PT" + date.CurrentDateStrinbg();
            periodTaskPlan.setBillno(code);
        } else {
            periodTaskPlan.setBillno(list.get(1));
        }
        if (!list.get(1).isEmpty()) {
            periodTaskPlan.setBillname(list.get(1));
        }
        if(!list.get(2).isEmpty()) {
            String name = list.get(2);
            PlanType  planType =planTypeService.findByName(name);
            if(!planType.toString().isEmpty()){
                periodTaskPlan.setBilltype(planType);
            }
        }
        if(!list.get(3).isEmpty()) {
            String name = list.get(3);
            PeriodType periodType =periodTypeService.findByName(name);
            if(!periodTypeService.toString().isEmpty()){
                periodTaskPlan.setPeriodtype(periodType);
            }
        }
        if(!list.get(4).isEmpty()) {
            String name = list.get(4);
            Project  project =projectService.findByName(name);
            if(!project.toString().isEmpty()){
                periodTaskPlan.setProject(project);
            }
        }
        if(!list.get(5).isEmpty()) {
            String name = list.get(5);
            ProjectWbs projectWbs =projectwbsService.findByName(name);
            if(!projectWbs.toString().isEmpty()){
                periodTaskPlan.setProjectWbs(projectWbs);
            }
        }
        if(!list.get(9).isEmpty()) {
            if (list.get(9).trim().equals("导入")) {
                periodTaskPlan.setProductionmode("导入");
            }
            if (list.get(9).trim().equals("自制")) {
                periodTaskPlan.setProductionmode("自制");
            }
        }
        if (list.get(6).compareTo(list.get(7)) >= 0 || list.get(6).isEmpty() || list.get(7).isEmpty()) {
            System.out.println("结束日期在开始日期之前");
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date planstartDate = sdf.parse(list.get(6).replace("/", "-"));
            Date planendDate = sdf.parse(list.get(7).replace("/", "-"));
            periodTaskPlan.setPlanstartdate(new java.sql.Date(planstartDate.getTime()));
            periodTaskPlan.setPlanenddate(new java.sql.Date(planendDate.getTime()));
        }
        if(!list.get(8).isEmpty()){
            if (list.get(8).trim().equals("正常")){
                periodTaskPlan.setOrderkind("正常");
            }
            if (list.get(8).trim().equals("激进")){
                periodTaskPlan.setOrderkind("激进");
            }
            if (list.get(8).trim().equals("保守")){
                periodTaskPlan.setOrderkind("保守");
            }
        }
        if (!list.get(10).isEmpty()){
            periodTaskPlan.setNote(list.get(10));
        }
        periodTaskPlan.setBillstate('0');
        return periodTaskPlan;
    }

    public Page<PeriodTaskPlan> findAllByPaging(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<PeriodTaskPlan> mpsQuery = new Specification<PeriodTaskPlan>() {
            @Override
            public Predicate toPredicate(Root<PeriodTaskPlan> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page<PeriodTaskPlan> mpsPage = periodTaskPlanRepository.findAll(mpsQuery, pageable);
        return mpsPage;
    }

    public void addAll(List<PeriodTaskPlan> periodTaskPlans){
        periodTaskPlanRepository.saveAll(periodTaskPlans);
    }
}


