package com.example.administrator.eatgood.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.eatgood.R;
import com.example.administrator.eatgood.adapter.FragmentOneAdapter_zuijinliulan;
import com.example.administrator.eatgood.showsomething.ActivityShow;
import com.example.administrator.eatgood.utils.NewsDBUtil;

/**
 * Created by Administrator on 2016/9/8.
 */
public class Fragment_two extends Fragment {
    FragmentOneAdapter_zuijinliulan fragmentOneAdapter_zuijinliulan;
    private RecyclerView recyclerView;
    NewsDBUtil dbUtil;
    SwipeRefreshLayout swip_three;
    int pos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_one);
        swip_three = (SwipeRefreshLayout) view.findViewById(R.id.swip_three);
        dbUtil = NewsDBUtil.getsInstance(getContext());

        fragmentOneAdapter_zuijinliulan = new FragmentOneAdapter_zuijinliulan(getContext(), dbUtil);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(fragmentOneAdapter_zuijinliulan);
        fragmentOneAdapter_zuijinliulan.notifyDataSetChanged();
//        刷新
        swip_three.setColorSchemeResources(R.color.se, R.color.se1, R.color.colorAccent);
        swip_three.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Snackbar.make(swip_three, "刷新中", Snackbar.LENGTH_SHORT).show();
                handler.sendEmptyMessageDelayed(0, 1000);
            }
        });
//        跳转
        fragmentOneAdapter_zuijinliulan.setOnItemClickListener(new FragmentOneAdapter_zuijinliulan.ItemClick() {
            @Override
            public void setOnItemClick(int position) {
                pos = dbUtil.getZuiJin().get(position).getPosition();
                Intent intent = new Intent(getContext(), ActivityShow.class);
                intent.putExtra("menu", dbUtil.getZuiJin().get(position).getMenu());
                intent.putExtra("position", pos);
                startActivity(intent);
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dbUtil = NewsDBUtil.getsInstance(getContext());
            recyclerView.setAdapter(fragmentOneAdapter_zuijinliulan);
            fragmentOneAdapter_zuijinliulan.notifyDataSetChanged();
            swip_three.setRefreshing(false);
        }
    };

}
