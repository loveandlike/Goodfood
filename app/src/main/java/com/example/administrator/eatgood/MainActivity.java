package com.example.administrator.eatgood;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.administrator.eatgood.avtivity.MenuActivity;

/**
 * 引导界面，淡入淡出动画
 */
public class MainActivity extends AppCompatActivity {
    ImageView iv_one, iv_two;
    AlphaAnimation mAlphaAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    private void init() {
        iv_one = (ImageView) findViewById(R.id.iv_main_one);
        iv_two = (ImageView) findViewById(R.id.iv_main_two);
        mAlphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        mAlphaAnimation.setDuration(2000);
        mAlphaAnimation.setFillAfter(true);
        mAlphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                handler.sendEmptyMessageDelayed(1, 1000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    iv_two.setAnimation(MainActivity.this.mAlphaAnimation);
                    iv_two.startAnimation(MainActivity.this.mAlphaAnimation);
                    iv_two.setVisibility(View.VISIBLE);
                    return;
                case 1:
                    MainActivity.this.GoOtherActivity();
                    finish();
                    break;
                default:
                    return;
            }
        }
    };

    void GoOtherActivity() {
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }
}
