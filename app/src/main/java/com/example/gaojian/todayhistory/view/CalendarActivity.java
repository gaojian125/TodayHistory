package com.example.gaojian.todayhistory.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dsw.calendar.component.MonthView;
import com.dsw.calendar.views.GridCalendarView;
import com.example.gaojian.todayhistory.R;
import com.jaeger.library.StatusBarUtil;

import baseactivity.BaseActivity;

public class CalendarActivity extends BaseActivity {


    private int mYear;
    private int mMonth;
    private int mDay;
    private Button mBtn;

    @Override
    public int bindLayout() {
        StatusBarUtil.setColor(this, Color.parseColor("#FF0000"), 0);
        return R.layout.activity_calendar;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        GridCalendarView gridCalendarView = findview(R.id.gridMonthView);
        mBtn = findview(R.id.gridCalendar_qd);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("year", mYear);
                bundle.putInt("month", mMonth);
                bundle.putInt("day", mDay);
                intent.putExtras(bundle);
                setResult(1, intent);
                finish();
            }
        });
        gridCalendarView.setDateClick(new MonthView.IDateClick() {
            @Override
            public void onClickOnDate(int year1, int month1, int day1) {
                mYear = year1;
                mMonth = month1;
                mDay = day1;

            }
        });
    }

    public void back(View view) {
        finish();
    }

    @Override
    public void loadData() {

    }

    @Deprecated
    public void requestSuccess(String json, int tag) {

    }
}
