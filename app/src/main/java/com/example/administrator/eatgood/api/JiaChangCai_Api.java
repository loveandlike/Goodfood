package com.example.administrator.eatgood.api;

import com.example.administrator.eatgood.entitys.JiaChangCai;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/9/8.
 */
public interface JiaChangCai_Api {
    @GET("cook/query.php")
    Call<JiaChangCai> getJiaChangCai(@Query("menu") String menu, @Query("key") String key);
}
