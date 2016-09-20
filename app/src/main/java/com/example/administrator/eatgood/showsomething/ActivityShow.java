package com.example.administrator.eatgood.showsomething;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.eatgood.R;
import com.example.administrator.eatgood.adapter.ShowAdapter;
import com.example.administrator.eatgood.api.JiaChangCai_Api;
import com.example.administrator.eatgood.entitys.JiaChangCai;
import com.example.administrator.eatgood.utils.NewsDBUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/9/8.
 */
public class ActivityShow extends AppCompatActivity {
    ImageView show_image;
    TextView show_text_descrepion, show_text_mingxi, show_title, caiming,text_shoucang;
    RecyclerView show_recycler;
    ShowAdapter adapter;
    int position = 0;
    LinearLayout linear_one, shouchang, share;
    NewsDBUtil dbUtil = NewsDBUtil.getsInstance(ActivityShow.this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showactivity);
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
            public void onResponse(Call<JiaChangCai> call, final Response<JiaChangCai> response) {
                final JiaChangCai body = response.body();
                final List<JiaChangCai.ResultBean.DataBean> data_hjc = body.getResult().getData();
                final List<JiaChangCai.ResultBean.DataBean.StepsBean> steps = data_hjc.get(position).getSteps();
                Picasso.with(ActivityShow.this).load(data_hjc.get(position).getAlbums().get(0)).into(show_image);
                caiming.setText(data_hjc.get(position).getTitle());
                show_text_descrepion.setText(data_hjc.get(position).getImtro());
                show_text_mingxi.setText(data_hjc.get(position).getIngredients() + data_hjc.get(position).getBurden());

                //最近浏览
//                NewsDBUtil dbUtil1 = NewsDBUtil.getsInstance(ActivityShow.this);
//                dbUtil.insertZuiJin(data_hjc.get(position), menu, position);

                //        收藏功能的实现
                shouchang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        NewsDBUtil dbUtil = NewsDBUtil.getsInstance(ActivityShow.this);
                        Snackbar.make(show_recycler, "收藏成功", Snackbar.LENGTH_SHORT).show();
                        text_shoucang.setText("取消收藏");
                        dbUtil.insertNews(data_hjc.get(position), menu, position);
                        Log.e("tagsssssssssssss", data_hjc.get(position).toString());
                    }
                });

                adapter = new ShowAdapter(steps, ActivityShow.this);
                show_recycler.setLayoutManager(new LinearLayoutManager(ActivityShow.this));
                show_recycler.setAdapter(adapter);
                adapter.setOnItemClickListener(new ShowAdapter.ItemClick() {
                    @Override
                    public void setOnItemClick(int position) {
                        Intent intent = new Intent(ActivityShow.this, ActivityShowView2.class);
                        intent.putExtra("position", position);
                        intent.putExtra("menu", menu);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<JiaChangCai> call, Throwable t) {
            }
        });
    }

    private void init() {
        share = (LinearLayout) findViewById(R.id.share);
        shouchang = (LinearLayout) findViewById(R.id.shoucang);
        caiming = (TextView) findViewById(R.id.caiming);
        show_image = (ImageView) findViewById(R.id.show_image);
        show_text_descrepion = (TextView) findViewById(R.id.show_text_descrepion);
        show_text_mingxi = (TextView) findViewById(R.id.show_text_mingxi);
        show_recycler = (RecyclerView) findViewById(R.id.show_recycler);
        show_title = (TextView) findViewById(R.id.title_actionbar);
        linear_one = (LinearLayout) findViewById(R.id.linear_one);
        text_shoucang= (TextView) findViewById(R.id.text_shoucang);
        show_title.setText("菜谱详情");

//        分享功能点击事件
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain/image/*");
                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                intent.putExtra(Intent.EXTRA_TEXT, "金爷菜谱分享功能测试。。。。。。。。");
                startActivity(Intent.createChooser(intent, getTitle()));
            }
        });
        linear_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
