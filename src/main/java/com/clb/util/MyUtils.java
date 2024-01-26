package com.clb.util;

import com.clb.constant.Excep;
import com.clb.exception.BaseException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class MyUtils {
    /**
     * 判断字符串不为null且不为空字符串
     */
    public static boolean StrUtil(String string){
        return string != null && !string.isEmpty();
    }

    /**
     * 解析字符串为日期
     */
    public static Date StrToDate(String date){
        if (!StrUtil(date)) {
            throw new BaseException(Excep.DATE_IS_NULL);
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date utilDate = format.parse(date);
            return new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            throw new BaseException(Excep.DATE_FORMAT_ERROR);
        }
    }

    /**
     * @return 返回当前日期
     */
    public static Date now(){
        return Date.valueOf(LocalDate.now());
    }

    public static Integer objToInt(Object object) {
        return Integer.parseInt((String) object);
    }

}
