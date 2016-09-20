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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.eatgood.R;
import com.example.administrator.eatgood.adapter.FragmentOneAdapter;
import com.example.administrator.eatgood.api.JiaChangCai_Api;
import com.example.administrator.eatgood.entitys.JiaChangCai;
import com.example.administrator.eatgood.showsomething.ActivityShow;
import com.example.administrator.eatgood.utils.NewsDBUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/9/7.
 */
public class FragmentOne extends Fragment {

    RecyclerView recyclerView;
    FragmentOneAdapter fragmentOneAdapter;
    String menu;
    SwipeRefreshLayout swipe_Refresh;
    NewsDBUtil dbUtil;
    List<JiaChangCai.ResultBean.DataBean> data_hjc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipe_Refresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        init();
//        获取数据
        getStringResult();
        dbUtil = NewsDBUtil.getsInstance(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_one);

    }

    private void init() {
        //       刷新数据
        swipe_Refresh.setColorSchemeResources(R.color.se, R.color.se1, R.color.colorAccent);
        swipe_Refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Snackbar.make(swipe_Refresh, "刷新中", Snackbar.LENGTH_SHORT).show();
                getStringResult();//获取数据
                hander.sendEmptyMessageDelayed(0, 3000);
            }
        });
    }

    //        联网操作
    private void getStringResult() {
        //                获取碎片依附的Activity的intent信息
        menu = getActivity().getIntent().getStringExtra("menu");
        String url = "http://apis.juhe.cn/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JiaChangCai_Api jiaChangCai_api = retrofit.  create(JiaChangCai_Api.class);
        Call<JiaChangCai> jiaChangCai = jiaChangCai_api.getJiaChangCai(menu, "a7274efdaba58a1f634862689bebde88");
        jiaChangCai.enqueue(new Callback<JiaChangCai>() {
            @Override
            public void onResponse(Call<JiaChangCai> call, final Response<JiaChangCai> response) {
                JiaChangCai body = response.body();
                if (body.getResult() != null) {
                    data_hjc = body.getResult().getData();
                    fragmentOneAdapter = new FragmentOneAdapter(getContext(), data_hjc);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(fragmentOneAdapter);
                    fragmentOneAdapter.setOnItemClickListener(new FragmentOneAdapter.ItemClick() {
                        @Override
                        public void setOnItemClick(int position) {
                            Intent intent = new Intent(getContext(), ActivityShow.class);
                            intent.putExtra("menu", menu);
                            intent.putExtra("position", position);
                            //最近浏览
                            dbUtil.insertZuiJin(data_hjc.get(position), menu, position);
                            Log.e("tagzzzzzzzzzzz", data_hjc.get(position).toString());
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<JiaChangCai> call, Throwable t) {
            }
        });
    }

    Handler hander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            fragmentOneAdapter.notifyDataSetChanged();//刷新界面
            swipe_Refresh.setRefreshing(false);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        hander.removeMessages(0);
//        hander.removeCallbacks();
    }
}
