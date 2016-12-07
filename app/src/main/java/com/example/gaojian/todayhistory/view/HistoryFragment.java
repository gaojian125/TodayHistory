package com.example.gaojian.todayhistory.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gaojian.todayhistory.R;
import com.example.gaojian.todayhistory.adapter.MyRecyclerViewAd;
import com.example.gaojian.todayhistory.entry.HistoryBean;
import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import baseactivity.BaseFragment;

/**
 * Created by gaojian on 2016/12/5.
 */
public class HistoryFragment extends BaseFragment {

    private PullLoadMoreRecyclerView mLv;
    private List<HistoryBean.ResultBean> listall = new ArrayList<>();
    private MyRecyclerViewAd mAd;
    private FloatingActionButton mFloatbutton;
    private TextView mDate;
    private ImageView mLeft;
    private ImageView mRight;
    private int month;
    private int day;

    @Override
    public int bindLayout() {
        return R.layout.fragmnetone;
    }

    @Override
    public void initData() {
        mAd = new MyRecyclerViewAd(listall, getActivity());
        mLv.setLinearLayout();
        mLv.setAdapter(mAd);
        /**
         * 详情页面
         */
        mAd.setOnItemClickLitener(new MyRecyclerViewAd.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), ShowActivity.class));
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        Date date = new Date();//取时间
        final Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        mLv = findview(R.id.lv);
        mLv.setPullRefreshEnable(false);
        mLv.setPushRefreshEnable(false);
        mDate = findview(R.id.date);
        mLeft = findview(R.id.left);
        mRight = findview(R.id.right);
        mLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(calendar.DATE, -1);//把日期往后增加一天.整数往后推,负数往前移动
                month = calendar.get(Calendar.MONTH) + 1;
                day = calendar.get(Calendar.DAY_OF_MONTH);
                getAyn("http://api.juheapi.com/japi/toh?v=6.0&month=" + month + "&day=" + day + "&key=ae0fed899afa35cd405f970383f5984e", 0);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                mDate.setText(sdf.format(calendar.getTime()));

            }
        });
        mRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(calendar.DATE, 1);//把日期往后增加一天.整数往后推,负数往前移动
                month = calendar.get(Calendar.MONTH) + 1;
                day = calendar.get(Calendar.DAY_OF_MONTH);
                getAyn("http://api.juheapi.com/japi/toh?v=6.0&month=" + month + "&day=" + day + "&key=ae0fed899afa35cd405f970383f5984e", 0);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                mDate.setText(sdf.format(calendar.getTime()));
            }
        });
        mFloatbutton = findview(R.id.floatbutton);
        mFloatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CalendarActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void loadData() {

    }

    @Override
    public void requestSuccess(String json, int tag) {
        Gson gson = new Gson();
        HistoryBean bean = gson.fromJson(json, HistoryBean.class);
        listall.clear();
        List<HistoryBean.ResultBean> list = bean.result;
        listall.addAll(list);
        mAd.notifyDataSetChanged();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        getAyn("http://api.juheapi.com/japi/toh?v=6.0&month=" + (month + 1) + "&day=" + day + "&key=ae0fed899afa35cd405f970383f5984e", 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            Bundle bundle = data.getExtras();
            int year = bundle.getInt("year");
            month = bundle.getInt("month");
            day = bundle.getInt("day");
            mDate.setText(year + "年" + month + "月" + day);
            getAyn("http://api.juheapi.com/japi/toh?v=6.0&month=" + month + "&day=" + day + "&key=ae0fed899afa35cd405f970383f5984e", 0);
        }
    }
}
