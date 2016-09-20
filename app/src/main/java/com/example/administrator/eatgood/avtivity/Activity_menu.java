package com.example.administrator.eatgood.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.eatgood.R;
import com.example.administrator.eatgood.adapter.TableAndPagerAdapter;
import com.example.administrator.eatgood.fragment.FragmentOne;
import com.example.administrator.eatgood.fragment.Fragment_three;
import com.example.administrator.eatgood.fragment.Fragment_two;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/7.
 */
public class Activity_menu extends AppCompatActivity {
    FragmentManager fm;//碎片管理器
    FragmentTransaction ft;//碎片操作器
    TabLayout tabLayout;
    ViewPager viewPager;
    ArrayList<Fragment> fragmentsList;
    ArrayList<String> titles;
    TableAndPagerAdapter adapter;
    Fragment_two fragment_two;
    FragmentOne fragmentOne;
    Fragment_three fragment_three;
    TextView te_three, title;
    LinearLayout linear_one;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        setContentView(R.layout.activity_jiachangcai);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.table);

//        ft.add(R.id.viewpager, fragmentOne).add(R.id.viewpager, fragment_two).add(R.id.viewpager, fragment_three);
//        ft.commit();
        init();
        initData();
    }
    private void init() {
        linear_one = (LinearLayout) findViewById(R.id.linear_one);
        te_three = (TextView) findViewById(R.id.actioNbar_te_three);
        title = (TextView) findViewById(R.id.title_actionbar);
        String menu = getIntent().getStringExtra("menu");
        title.setText(menu);
//            箭头返回
        linear_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itent = new Intent(Activity_menu.this, MenuActivity_one.class);
                startActivity(itent);
            }
        });
    }

    public void initData() {
        titles = new ArrayList<>();
        fragmentsList = new ArrayList<>();
        titles.add("全部菜谱");
        titles.add("最近浏览");
        titles.add("我的收藏");

        fragmentOne = new FragmentOne();
        fragment_two = new Fragment_two();
        fragment_three = new Fragment_three();

        fragmentsList.add(fragmentOne);
        fragmentsList.add(fragment_two);
        fragmentsList.add(fragment_three);
        adapter = new TableAndPagerAdapter(fm, fragmentsList, titles);

        //适配器设置
        viewPager.setAdapter(adapter);
        tabLayout.setTabsFromPagerAdapter(adapter);//共用适配器
        tabLayout.setupWithViewPager(viewPager);//一起滚动
        viewPager.setOffscreenPageLimit(3);//预加载
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
