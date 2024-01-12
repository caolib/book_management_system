package com.clb.util;

import com.clb.constant.Excep;
import com.clb.exception.BaseException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.TimeZone;

public class MyUtils {
    // 判断字符串不为null且不为空字符串
    public static boolean StrUtil(String string){
        return string != null && !string.isEmpty();
    }

    //字符串类型日期转换成日期
    public static Date StrToDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        try {
            java.util.Date utilDate = format.parse(date);
            return new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            throw new BaseException(Excep.DATE_FORMAT_ERROR);
        }
    }

    public static Date now(){
        return Date.valueOf(LocalDate.now());
    }


}
