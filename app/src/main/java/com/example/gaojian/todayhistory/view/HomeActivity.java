package com.example.gaojian.todayhistory.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.gaojian.todayhistory.R;
import com.jaeger.library.StatusBarUtil;

import baseactivity.BaseActivity;

public class HomeActivity extends BaseActivity {


    Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private HistoryFragment mHistoryFragment;

    @Override
    public int bindLayout() {
        StatusBarUtil.setColor(this, Color.parseColor("#FF0000"), 0);
        return R.layout.activity_main2;
    }

    @Override
    public void initData() {
        //title
        mToolbar.setTitle("历史上的今天");
        //以上3个属性必须在setSupportActionBar(toolbar)之前调用
        mToolbar.setTitleTextColor(Color.WHITE);
        //mToolbar.setNavigationIcon(R.drawable.cehua); //设置抽屉DrawerLayout
        setSupportActionBar(mToolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置

        //设置抽屉DrawerLayout
        final DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();//初始化状态
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        //设置导航栏NavigationView的点击事件
        naviClick(mDrawerLayout);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_content, new HistoryFragment()).commit();

    }

    /**
     * //设置导航栏NavigationView的点击事件
     *
     * @param mDrawerLayout
     */
    private void naviClick(final DrawerLayout mDrawerLayout) {
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_one:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new HistoryFragment()).commit();
                        mToolbar.setTitle("历史上的今天");
                        break;
                    case R.id.item_two:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new GirlFragment()).commit();
                        mToolbar.setTitle("妹纸");
                        break;
                    case R.id.item_three:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new CollectionFragment()).commit();
                        mToolbar.setTitle("收藏");
                        break;
                    case R.id.item_set:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new SetFragment()).commit();
                        mToolbar.setTitle("设置");
                        break;
                }
                menuItem.setChecked(true);//点击了把它设为选中状态
                mDrawerLayout.closeDrawers();//关闭抽屉
                return true;
            }
        });
    }


    @Override
    public void initView(Bundle savedInstanceState) {
        mToolbar = findview(R.id.toolbar);
    }

    @Override
    public void loadData() {


    }

    @Override
    public void requestSuccess(String json, int tag) {


    }


}
