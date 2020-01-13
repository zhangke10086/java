package com.example.common.formatdate;

import org.apache.commons.lang.StringUtils;

import java.util.Calendar;

public class DateString {
    public String CurrentDateStrinbg()
    {
        Calendar Cld = Calendar.getInstance();
        int YY = Cld.get(Calendar.YEAR) ;
        int MM = Cld.get(Calendar.MONTH)+1;
        int DD = Cld.get(Calendar.DATE);
        int HH = Cld.get(Calendar.HOUR_OF_DAY);
        int mm = Cld.get(Calendar.MINUTE);
        int SS = Cld.get(Calendar.SECOND);
        int MI = Cld.get(Calendar.MILLISECOND);
        return (Integer.toString(YY)+
                StringUtils.leftPad(Integer.toString(MM), 2, "0")+
                StringUtils.leftPad(Integer.toString(DD), 2, "0")+
                StringUtils.leftPad(Integer.toString(HH), 2, "0")+
                StringUtils.leftPad(Integer.toString(mm), 2, "0")+
                StringUtils.leftPad(Integer.toString(SS), 2, "0")+
                StringUtils.leftPad(Integer.toString(MI), 3, "0"));
    }
    public DateString() {
    }
}
