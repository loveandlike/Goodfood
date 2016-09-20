package com.example.administrator.eatgood.avtivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.eatgood.R;
import com.example.administrator.eatgood.fragment.Fragment_three;
import com.example.administrator.eatgood.fragment.Fragment_two;

/**
 * Created by Administrator on 2016/9/13.
 */
public class Activity_shoucang extends AppCompatActivity {
    FragmentManager fm;//碎片管理器
    FragmentTransaction ft;//碎片操作器
    LinearLayout linear_one, linear_shoucang;
    String fen="我的收藏";
    String title;
    TextView textView_title;
    Fragment_three fragment_three;
    Fragment_two fragment_two;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoucang);
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        fragment_three = new Fragment_three();
        fragment_two = new Fragment_two();
//        添加碎片
//        ft.add(R.id.li_shoucang, fragment_three).add(R.id.li_shoucang, fragment_two);
//        ft.commit();
        init();
        setFragment(title);
    }
    //        添加碎片
    private void setFragment(String str) {
        if (str.equals(fen)){
            ft.add(R.id.li_shoucang, fragment_three);
            ft.commit();
        }else {
           ft.add(R.id.li_shoucang, fragment_two);
            ft.commit();
        }
    }

    private void init() {
        linear_one = (LinearLayout) findViewById(R.id.linear_one);
        title = getIntent().getStringExtra("title");
        Log.e("tagttttttttttttttttt", title);
        textView_title = (TextView) findViewById(R.id.title_actionbar);
        textView_title.setText(title);
        linear_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
