package com.example.administrator.eatgood.showsomething;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.administrator.eatgood.R;
import com.example.administrator.eatgood.adapter.ShowAdapter2;
import com.example.administrator.eatgood.api.JiaChangCai_Api;
import com.example.administrator.eatgood.entitys.JiaChangCai;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/9/9.
 */
public class ActivityShowView2 extends AppCompatActivity {
    ScrollView scrollView_1;
    RecyclerView recycler_show2;
    int position = 0;
    ShowAdapter2 showAdapter2;
    LinearLayout linear_show2;
    TextView title_show2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_viewpager);
        init();
        final String menu = getIntent().getStringExtra("menu");
        position = getIntent().getIntExtra("position", 0);
        //        联网操作
        String url = "http://apis.juhe.cn/";
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JiaChangCai_Api jiaChangCai_api = retrofit1.create(JiaChangCai_Api.class);
        Call<JiaChangCai> jiaChangCai = jiaChangCai_api.getJiaChangCai(menu, "a7274efdaba58a1f634862689bebde88");
        jiaChangCai.enqueue(new Callback<JiaChangCai>() {
            @Override
            public void onResponse(Call<JiaChangCai> call, Response<JiaChangCai> response) {
                final JiaChangCai body = response.body();
                final List<JiaChangCai.ResultBean.DataBean> data_hjc = body.getResult().getData();
                Log.e("tag1111111111", body.toString());
                List<JiaChangCai.ResultBean.DataBean.StepsBean> steps = data_hjc.get(position).getSteps();
                showAdapter2 = new ShowAdapter2(ActivityShowView2.this, steps);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ActivityShowView2.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recycler_show2.setLayoutManager(linearLayoutManager);
                recycler_show2.setAdapter(showAdapter2);

            }

            @Override
            public void onFailure(Call<JiaChangCai> call, Throwable t) {
            }
        });
    }

    private void init() {
        scrollView_1 = (ScrollView) findViewById(R.id.scrollView_1);
        recycler_show2 = (RecyclerView) findViewById(R.id.recycler_show2);
        linear_show2 = (LinearLayout) findViewById(R.id.linear_one);
        title_show2 = (TextView) findViewById(R.id.title_actionbar);
        title_show2.setText("制作步骤");
        linear_show2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            finish();
            }
        });
    }
}
