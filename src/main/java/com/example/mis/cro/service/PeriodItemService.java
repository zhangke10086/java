package com.example.mis.cro.service;

import com.example.mis.cro.entity.PeriodItem;
import com.example.mis.cro.entity.PeriodType;
import com.example.mis.cro.repository.PeriodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class PeriodItemService {
    @Autowired
    private PeriodItemRepository periodItemRepository;
    /**
     *@描述   新增期间明细

     *@参数  [periodItem]

     *@返回值  void

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public void add(PeriodItem periodItem) {
        periodItemRepository.save(periodItem);
    }
    /**
     *@描述   查找全部期间明细

     *@参数  []

     *@返回值  java.util.List<com.inspur.momservices.mops.entity.PeriodItem>

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public List<PeriodItem> findAll() {
        return periodItemRepository.findAll();
    }

    /**
     *@描述   根据id查找期间明细

     *@参数  [id]

     *@返回值  java.util.List<com.inspur.momservices.mops.entity.PeriodItem>

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public List<PeriodItem> findById(String id) {
        return periodItemRepository.find(id);
    }

    /**
     *@描述   根据id删除期间明细

     *@参数  [id]

     *@返回值  void

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public void deleteById(String id) {
        periodItemRepository.deleteById(id);
    }
    /**
     *@描述   修改期间明细

     *@参数  [periodItem]

     *@返回值  void

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public void update(PeriodItem periodItem) {
        periodItemRepository.save(periodItem);
    }
    /**
     *@描述   批量修改期间明细

     *@参数  [periodItem]

     *@返回值  void

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public void updateAll(List<PeriodItem> periodItem) {
        periodItemRepository.saveAll(periodItem);
    }

    public void deleteAll(List<PeriodItem> periodItem) {
        /**
         *@描述   删除全部期间明细

         *@参数  [periodItem]

         *@返回值  void

         *@创建人  zhangke

         *@创建时间  2019/10/8

         *@修改人和其它信息

         */
        periodItemRepository.deleteAll(periodItem);
    }

    /**
     *@描述   新增年

     *@参数  [year, num, type]

     *@返回值  void

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public void addYear(String year, int num, String type) {
        int Year = Integer.parseInt(year);
        for (int i = 0; i < num; i++) {
            PeriodItem periodItem = new PeriodItem();
            periodItem.setName(Year + "年");
            PeriodType periodType = new PeriodType();
            periodType.setId(type);
            periodItem.setPeriodtype(periodType);
            Date begin = Date.valueOf(Year + "-01-01");
            periodItem.setBegindate(begin);
            Date end = Date.valueOf(Year + "-12-31");
            periodItem.setEnddate(end);
            periodItemRepository.save(periodItem);
            Year++;
        }
    }


    /**
     *@描述   新增季

     *@参数  [year, num, type]

     *@返回值  void

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public void addSeason(String year, int num, String type) {
        int Year = Integer.parseInt(year);
        int number =0;//总数量
        if(num%4>0){
            number=((num/4) +1);
        }
        else {number=num/4;};
        for (int i=0;i<number;i++) {
            for (int n = 1; n < 5; n++) {
                PeriodItem periodItem = new PeriodItem();
                periodItem.setName(Year + "年" + "第" + n + "季度");
                PeriodType periodType = new PeriodType();
                periodType.setId(type);
                if (n == 1) {
                    Date begin = Date.valueOf(Year + "-1-01");
                    Date end = Date.valueOf(Year + "-3-31");
                    periodItem.setPeriodtype(periodType);
                    periodItem.setBegindate(begin);
                    periodItem.setEnddate(end);
                }
                if (n == 2) {
                    Date begin = Date.valueOf(Year + "-4-01");
                    Date end = Date.valueOf(Year + "-6-30");
                    periodItem.setPeriodtype(periodType);
                    periodItem.setBegindate(begin);
                    periodItem.setEnddate(end);
                }
                if (n == 3) {
                    Date begin = Date.valueOf(Year + "-7-01");
                    Date end = Date.valueOf(Year + "-9-30");
                    periodItem.setPeriodtype(periodType);
                    periodItem.setBegindate(begin);
                    periodItem.setEnddate(end);
                }
                if (n == 4) {
                    Date begin = Date.valueOf(Year + "-10-01");
                    Date end = Date.valueOf(Year + "-12-31");
                    periodItem.setPeriodtype(periodType);
                    periodItem.setBegindate(begin);
                    periodItem.setEnddate(end);
                }
                periodItemRepository.save(periodItem);
            }
            Year++;
        }
    }


    /**
     *@描述   新增月

     *@参数  [year, month, num, type]

     *@返回值  void

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public void addMonth(String year, String month, int num, String type){
        int Year = Integer.parseInt(year);
        int count =0;
        int q =1;
        for (int i = 0; i < num ; i++) {
            if (count == 0) {//当年月份
                for (int n = Integer.parseInt(month); n < 13; n++) {
                    if (q>num) break;
                    PeriodItem periodItem = new PeriodItem();
                    PeriodType periodType = new PeriodType();
                    periodItem.setName(Year + "年" + n + "月");
                    Date begin = Date.valueOf(Year + "-" + n + "-01");
                    Date end = date(Year,n);
                    periodItem.setBegindate(begin);
                    periodItem.setEnddate(end);
                    periodType.setId(type);
                    periodItem.setPeriodtype(periodType);
                    periodItemRepository.save(periodItem);
                    q++;
                }
            }
            if (count > 0) {//下年月份
                for (int n = 1; n < 13; n++) {
                    if (q>num) break;
                    PeriodItem periodItem = new PeriodItem();
                    PeriodType periodType = new PeriodType();
                    periodItem.setName(Year + "年" + n + "月");
                    Date begin = Date.valueOf(Year + "-" + n + "-01");
                    Date end = date(Year,n);
                    periodItem.setBegindate(begin);
                    periodItem.setEnddate(end);
                    periodType.setId(type);
                    periodItem.setPeriodtype(periodType);
                    periodItemRepository.save(periodItem);
                    q++;
                }
            }
            count++;
            Year++;
        }
    }

    /**
     *@描述   新增旬

     *@参数  [year, month, num, type]

     *@返回值  void

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public void addtendays(String year, String month, int num, String type) {
        int Year = Integer.parseInt(year);
        int number;//总数量
        int count1 = 0;
        int r = 1;
        if (num % 3 > 0) {
            number = ((num / 3) + 1);
        } else {
            number = num / 3;
        }
        ;
        for (int i = 0; i < number; i++) {
            if (count1 == 0) {
                for (int n = Integer.parseInt(month); n < 13; n++) {//月份
                    for (int e = 1; e < 4; e++) {//旬
                        if (r > 3 * number) break;
                        PeriodItem periodItem = new PeriodItem();
                        PeriodType periodType = new PeriodType();
                        periodType.setId(type);
                        if (e == 1) {
                            periodItem.setName(Year + "年" + n + "月" + "上旬");
                            Date begin = Date.valueOf(Year + "-" + n + "-01");
                            Date end = Date.valueOf(Year + "-" + n + "-10");
                            periodItem.setPeriodtype(periodType);
                            periodItem.setBegindate(begin);
                            periodItem.setEnddate(end);
                        }
                        if (e == 2) {
                            periodItem.setName(Year + "年" + n + "月" + "中旬");
                            Date begin = Date.valueOf(Year + "-" + n + "-11");
                            Date end = Date.valueOf(Year + "-" + n + "-20");
                            periodItem.setPeriodtype(periodType);
                            periodItem.setBegindate(begin);
                            periodItem.setEnddate(end);
                        }
                        if (e == 3) {
                            periodItem.setName(Year + "年" + n + "月" + "下旬");
                            Date begin = Date.valueOf(Year + "-" + n + "-21");
                            Date end = date(Year, n);
                            periodItem.setPeriodtype(periodType);
                            periodItem.setBegindate(begin);
                            periodItem.setEnddate(end);
                        }
                        periodItemRepository.save(periodItem);
                        r++;
                    }
                }
            }
                if (count1 > 0) {
                    for (int n = 1; n < 13; n++) {//月份
                        for (int e = 1; e < 4; e++) {//旬
                            if (r > 3 * number) break;
                            PeriodItem periodItem = new PeriodItem();
                            PeriodType periodType = new PeriodType();
                            periodType.setId(type);
                            if (e == 1) {
                                periodItem.setName(Year + "年" + n + "月" + "上旬");
                                Date begin = Date.valueOf(Year + "-" + n + "-01");
                                Date end = Date.valueOf(Year + "-" + n + "-10");
                                periodItem.setPeriodtype(periodType);
                                periodItem.setBegindate(begin);
                                periodItem.setEnddate(end);
                            }
                            if (e == 2) {
                                periodItem.setName(Year + "年" + n + "月" + "中旬");
                                Date begin = Date.valueOf(Year + "-" + n + "-11");
                                Date end = Date.valueOf(Year + "-" + n + "-20");
                                periodItem.setPeriodtype(periodType);
                                periodItem.setBegindate(begin);
                                periodItem.setEnddate(end);
                            }
                            if (e == 3) {
                                periodItem.setName(Year + "年" + n + "月" + "下旬");
                                Date begin = Date.valueOf(Year + "-" + n + "-21");
                                Date end = date(Year, n);
                                periodItem.setPeriodtype(periodType);
                                periodItem.setBegindate(begin);
                                periodItem.setEnddate(end);
                            }
                            periodItemRepository.save(periodItem);
                            r++;
                        }
                    }
                }
            count1++;
            Year++;
        }
    }

    /**
     *@描述   新增周

     *@参数  [year, month, day, num, type]

     *@返回值  void

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public void addWeek(String year, String month , String day, int num, String type){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(year));
        calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);//月份是从0开始的，所以要-1
        calendar.set(Calendar.DATE, Integer.parseInt(day));
        for(int i =0;i<num;i++) {
            PeriodItem periodItem = new PeriodItem();
            PeriodType periodType = new PeriodType();
            periodType.setId(type);
            periodItem.setName(calendar.get(Calendar.YEAR) + "年"  + "第"+ calendar.get(Calendar.WEEK_OF_YEAR)+"周");
            Date begin =
                    Date.valueOf((calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+1) + "-"+calendar.get(Calendar.DATE)));
            periodItem.setBegindate( begin);
            calendar.add(Calendar.DATE,6);
            Date end =
                    Date.valueOf((calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+1) + "-"+calendar.get(Calendar.DATE)));
            periodItem.setEnddate(end);
            periodItem.setPeriodtype(periodType);
            periodItemRepository.save(periodItem);
            calendar.add(Calendar.DATE,1);
        }
    }

    /**
     *@描述   新增固定天数

     *@参数  [year, month, day, fixday, num, type]

     *@返回值  void

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public void addFixday(String year, String month , String day, String fixday, int num, String type){
        int Fixday = Integer.parseInt(fixday);//固定天数
        int number =1;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);//月份是从0开始的，所以要-1
        cal.set(Calendar.DATE, Integer.parseInt(day));
        for (int i=0;i<num;i++){
            PeriodItem periodItem = new PeriodItem();
            PeriodType periodType = new PeriodType();
            periodType.setId(type);
            periodItem.setName("第"+number+"期间");
            Date begin =
                    Date.valueOf((cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-"+cal.get(Calendar.DATE)));
            periodItem.setBegindate( begin);
            cal.add(Calendar.DATE,Fixday-1);
            Date end =
                    Date.valueOf((cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-"+cal.get(Calendar.DATE)));
            periodItem.setEnddate(end);
            periodItem.setPeriodtype(periodType);
            periodItem.setNote("fix");
            periodItemRepository.save(periodItem);
            cal.add(Calendar.DATE,1);
            number++;
        }
    }

    /**
     *@描述   新增其他

     *@参数  [num, type]

     *@返回值  void

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public void addOther(int num, String type){
        int number=1;
        for (int i=0;i<num;i++){
            PeriodItem periodItem = new PeriodItem();
            PeriodType periodType = new PeriodType();
            periodType.setId(type);
            periodItem.setName("第"+number+"期间");
            periodItem.setPeriodtype(periodType);
            periodItem.setNote("other");
            periodItemRepository.save(periodItem);
            number++;
        }
    }
    /**
     *@描述   判断闰年，计算月末日期

     *@参数  [Year, Month]

     *@返回值  java.sql.Date

     *@创建人  zhangke

     *@创建时间  2019/10/8

     *@修改人和其它信息

     */
    public Date date(int Year, int Month){

        List<Date> list =new ArrayList<Date>();
        if(Year % 4 == 0 && Year % 100 != 0 || Year % 400 == 0){
            if (Month ==2) {
                Date end = Date.valueOf(Year + "-" + Month + "-29"); list.add(end);
            }
            if (Month == 1 || Month == 3 || Month == 5 || Month == 7 || Month == 8 || Month == 10 || Month == 12){
                Date end = Date.valueOf(Year + "-" + Month + "-31");list.add(end);
            }
            if (Month == 4 || Month == 6 || Month == 9 || Month == 11){
                Date end = Date.valueOf(Year + "-" + Month + "-30");list.add(end);
            }
        }
        else {
            if (Month ==2) {
                Date end = Date.valueOf(Year + "-" + Month + "-28");list.add(end);
            }
            if (Month == 1 || Month == 3 || Month == 5 || Month == 7 || Month == 8 || Month == 10 || Month == 12){
                Date end = Date.valueOf(Year + "-" + Month + "-31");list.add(end);
            }
            if (Month == 4 || Month == 6 || Month == 9 || Month == 11){
                Date end = Date.valueOf(Year + "-" + Month + "-30");list.add(end);
            }
        }
        return list.get(0);
    }
}
