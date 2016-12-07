package com.example.gaojian.todayhistory.view;

import android.os.Bundle;
import android.widget.Toast;

import com.dsw.calendar.component.MonthView;
import com.dsw.calendar.views.GridCalendarView;
import com.example.gaojian.todayhistory.R;

import baseactivity.BaseFragment;

/**
 * Created by gaojian on 2016/12/5.
 */
public class GirlFragment extends BaseFragment {
    @Override
    public int bindLayout() {
        return R.layout.fragmnettwo;
    }

    @Override
    public void initData() {
        // GridCalendarView
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        GridCalendarView gridCalendarView = findview(R.id.gridMonthView);
        gridCalendarView.setDateClick(new MonthView.IDateClick() {
            @Override
            public void onClickOnDate(int year, int month, int day) {
                Toast.makeText(getActivity(), "点击了" + year + "-" + month + "-" + day, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void loadData() {

    }

    @Override
    public void requestSuccess(String json, int tag) {

    }
}
