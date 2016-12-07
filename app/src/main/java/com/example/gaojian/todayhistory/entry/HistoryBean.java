package com.example.gaojian.todayhistory.entry;

import java.util.List;

/**
 * Created by gaojian on 2016/12/6.
 */
public class HistoryBean {

    public String reason;
    public int error_code;
    public List<ResultBean> result;

    public static class ResultBean {

        public String _id;
        public String title;
        public String pic;
        public int year;
        public int month;
        public int day;
        public String des;
        public String lunar;
    }
}
