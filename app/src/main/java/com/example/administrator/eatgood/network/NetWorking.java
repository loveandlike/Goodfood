package com.example.administrator.eatgood.network;

import com.example.administrator.eatgood.api.JiaChangCai_Api;
import com.example.administrator.eatgood.entitys.JiaChangCai;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/9/8.
 */
public class NetWorking {
    JiaChangCai body = null;

    public String getInternet(String key, String value) {
        String url = "http://apis.juhe.cn/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JiaChangCai_Api jiaChangCai_api = retrofit.create(JiaChangCai_Api.class);
        Call<JiaChangCai> jiaChangCai = jiaChangCai_api.getJiaChangCai(key, value);

        jiaChangCai.enqueue(new Callback<JiaChangCai>() {
            @Override
            public void onResponse(Call<JiaChangCai> call, Response<JiaChangCai> response) {
                body = response.body();
            }
            @Override
            public void onFailure(Call<JiaChangCai> call, Throwable t) {
            }
        });
        return body.toString();
    }


    //                        for (int i = 0; i < data_hjc.size(); i++) {
//                            for (int p = 0; p < data_hjc.get(i).getSteps().size(); p++) {
//                                intent.putExtra("title", data_hjc.get(i).getTitle());
//                                intent.putExtra("tags", data_hjc.get(i).getTags());
//                                intent.putExtra("ingredients",data_hjc.get(i).getIngredients());
//                                intent.putExtra("burden",data_hjc.get(i).getBurden());
//                                intent.putExtra("albums",data_hjc.get(i).getAlbums().get(0));
//                                List<JiaChangCai.ResultBean.DataBean.StepsBean> steps = data_hjc.get(i).getSteps();
//                                intent.putExtra("steps", steps)
//                            }
//                        }
}
