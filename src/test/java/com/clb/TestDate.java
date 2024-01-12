package com.clb;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class TestDate {
    public static void main(String[] args) {
        Timestamp timestamp = Timestamp.valueOf("2024-01-11 23:43:42.888");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateWithoutTime = format.format(new Date(timestamp.getTime()));
        System.out.println(dateWithoutTime);
    }
}
