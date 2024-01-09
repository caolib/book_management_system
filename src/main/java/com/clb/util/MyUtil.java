package com.clb.util;

public class MyUtil {
    // 判断字符串不为null且不为空字符串
    public static boolean StrUtil(String string){
        System.out.println(string != null && !string.isEmpty());
        return string != null && !string.isEmpty();
    }
}
