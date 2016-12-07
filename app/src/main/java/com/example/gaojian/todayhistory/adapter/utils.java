package com.example.gaojian.todayhistory.adapter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gaojian on 2016/12/6.
 */
public class utils {

    public static String GetDateAdd(int m, String format) {
        Date date = new Date();
        SimpleDateFormat h = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String n = h.format(date);
        Timestamp time = Timestamp.valueOf(n);
        // 在天数上加（减）天数
        long l = time.getTime() + 24 * 60 * 60 * m * 1000;
        // long l = time.getTime() + 10*60*m*1000;
        time.setTime(l);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // 获取当前时间并格式化之
        String newDate = sdf.format(time);
        return newDate;
    }
}
