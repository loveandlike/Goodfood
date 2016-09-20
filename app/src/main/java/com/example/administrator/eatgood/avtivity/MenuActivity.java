package com.example.administrator.eatgood.avtivity;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.eatgood.R;
import com.example.administrator.eatgood.adapter.MyPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/7.
 */
public class MenuActivity extends AppCompatActivity {
    LocalActivityManager manager = null;
    private ViewPager pager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymenu);
        manager = new LocalActivityManager(this, true);
        manager.dispatchCreate(savedInstanceState);
        initPagerViewer();
    }

    private void initPagerViewer() {
        pager = (ViewPager) findViewById(R.id.vp_menu);
        final ArrayList<View> list = new ArrayList<>();
        Intent intent = new Intent(MenuActivity.this, MenuActivity_one.class);
        list.add(getView("A", intent));
        Intent intent2 = new Intent(MenuActivity.this, MenuActivity_two.class);
        list.add(getView("B", intent2));
        pager.setAdapter(new MyPagerAdapter(list));
        pager.setCurrentItem(0);
    }

    private View getView(String id, Intent intent) {
        return manager.startActivity(id, intent).getDecorView();
    }
}
