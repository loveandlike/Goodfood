package com.example.administrator.eatgood.setfront;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/8.
 */
public class MyTextView extends TextView {


    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }
    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context context) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(),"/system/fonts/BB.TTF");
        setTypeface(tf);
        setText(Html.fromHtml("<font coler=\"#8EDOD7\">学</font><font coler=\"#8EdOD7\">做</font><font coler=\"#8BODOO\">菜</font> "));
    }

}
