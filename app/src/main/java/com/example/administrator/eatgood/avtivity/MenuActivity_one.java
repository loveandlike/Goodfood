package com.example.administrator.eatgood.avtivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.administrator.eatgood.R;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/9/7.
 */
public class MenuActivity_one extends Activity {
    SearchView sv = null;
    ListView lv = null;
    Button allcai;
    //    MyTextView menu_main_textview;
    Button jiachangcai, kuaishoucai, chuangyicai, sucai, liangcai, hongbei, mianshi, tang, zizhiweitiaoliao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuactivity_main);
        setSearchView();//设置搜索框
//        setTextType();
        initActivity();
//        init();
    }

//    private void init() {
//        menu_main_textview= (MyTextView) findViewById(R.id.menu_main_textview);
//        menu_main_textview.setText("学做菜");
//    }


    private void initActivity() {
        allcai = (Button) findViewById(R.id.allcai);
        jiachangcai = (Button) findViewById(R.id.jiachangcai);
        kuaishoucai = (Button) findViewById(R.id.kuaishoucai);
        chuangyicai = (Button) findViewById(R.id.chuangyicai);
        sucai = (Button) findViewById(R.id.sucai);
        liangcai = (Button) findViewById(R.id.liangcai);
        hongbei = (Button) findViewById(R.id.hongbei);
        mianshi = (Button) findViewById(R.id.mianshi);
        tang = (Button) findViewById(R.id.tang);
        zizhiweitiaoliao = (Button) findViewById(R.id.zhizhitiaoliaowei);
        myClick onclick = new myClick();
        jiachangcai.setOnClickListener(onclick);
        kuaishoucai.setOnClickListener(onclick);
        chuangyicai.setOnClickListener(onclick);
        sucai.setOnClickListener(onclick);
        liangcai.setOnClickListener(onclick);
        hongbei.setOnClickListener(onclick);
        mianshi.setOnClickListener(onclick);
        tang.setOnClickListener(onclick);
        zizhiweitiaoliao.setOnClickListener(onclick);



    }

    private void setSearchView() {
        sv = (SearchView) findViewById(R.id.sv);
        sv.setIconifiedByDefault(false);
        sv.setSubmitButtonEnabled(true);
        sv.setQueryHint("查询");
        Field field = null;
        try {
            field = sv.getClass().getDeclaredField("mSubmitButton");
            ImageView iv = (ImageView) field.get(sv);
            iv.setImageDrawable(this.getResources().getDrawable(R.mipmap.btnsearch_red));
            field.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Cursor cursor = this.getTestCursor();
        @SuppressWarnings("deprecation") SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.mytextview, cursor, new String[]{"tb_name"}, new int[]{R.id.textview});
        sv.setSuggestionsAdapter(adapter);
//        查询监听
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent = new Intent(MenuActivity_one.this, Activity_menu.class);
                intent.putExtra("menu", s);
                startActivity(intent);
                Toast.makeText(MenuActivity_one.this, s, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    //添加suggestion需要的数据
    public Cursor getTestCursor() {
        Cursor cursor = null;
        return cursor;
    }

    class myClick implements View.OnClickListener {
        Intent intent = new Intent(MenuActivity_one.this, Activity_menu.class);

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.jiachangcai:
                    intent.putExtra("menu", "家常菜");
                    startActivity(intent);
                    break;
                case R.id.kuaishoucai:
                    intent.putExtra("menu", "快手菜");
                    startActivity(intent);
                    break;
                case R.id.sucai:
                    intent.putExtra("menu", "素菜");
                    startActivity(intent);
                    break;
                case R.id.liangcai:
                    intent.putExtra("menu", "凉菜");
                    startActivity(intent);
                    break;
                case R.id.hongbei:
                    intent.putExtra("menu", "烘焙");
                    startActivity(intent);
                    break;
                case R.id.mianshi:
                    intent.putExtra("menu", "面食");
                    startActivity(intent);
                    break;
                case R.id.tang:
                    intent.putExtra("menu", "汤");
                    startActivity(intent);
                    break;
                case R.id.zhizhitiaoliaowei:
                    intent.putExtra("menu", "自制");
                    startActivity(intent);
                    break;
                case R.id.chuangyicai:
                    intent.putExtra("menu", "创意菜");
                    startActivity(intent);
                    break;
                case R.id.allcai:
                    Intent intent1 = new Intent(MenuActivity_one.this, AllObject.class);
                    startActivity(intent1);
                    break;


            }
        }
    }
}
