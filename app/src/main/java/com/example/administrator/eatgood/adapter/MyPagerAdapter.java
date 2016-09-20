package com.example.administrator.eatgood.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
public class MyPagerAdapter extends PagerAdapter {
    List<View> list =  new ArrayList<View>();
    public MyPagerAdapter(ArrayList<View> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewPager pViewPager = ((ViewPager) container);
         pViewPager.addView(list.get(position));
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager pViewPager = ((ViewPager) container);
        pViewPager.removeView(list.get(position));
    }
    
}
