package com.example.administrator.eatgood.avtivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.eatgood.R;

/**
 * Created by Administrator on 2016/9/7.
 */
public class MenuActivity_two extends Activity {
    Button btn_shoucang, btn_liulan;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuactivity_two);
        btn_shoucang = (Button) findViewById(R.id.btn_shou);
        btn_liulan = (Button) findViewById(R.id.btn_liulan);
        btn_shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str="我的收藏";
                Intent intent = new Intent(MenuActivity_two.this, Activity_shoucang.class);
                intent.putExtra("title",str);
                startActivity(intent);
            }
        });
        btn_liulan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str="最近浏览";
                Intent intent = new Intent(MenuActivity_two.this, Activity_shoucang.class);
                intent.putExtra("title",str);
                startActivity(intent);
            }
        });
    }
}
