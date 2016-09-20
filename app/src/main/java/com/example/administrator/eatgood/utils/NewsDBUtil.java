package com.example.administrator.eatgood.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.eatgood.entitys.JiaChangCai;

import java.util.ArrayList;
import java.util.List;


public class NewsDBUtil {
    private DBHelper dbHelper;
    private static NewsDBUtil sInstance;
    private SQLiteDatabase database;
    private Context mContext;

//    private String menu;

    private NewsDBUtil(Context context) {
        dbHelper = new DBHelper(context);
        this.mContext = context;
        database = dbHelper.getWritableDatabase();
    }

    public static NewsDBUtil getsInstance(Context context) {
        if (sInstance == null) {
            sInstance = new NewsDBUtil(context);
        }
        return sInstance;
    }

    //增删改查
    public void insertNews(JiaChangCai.ResultBean.DataBean dataBean, String menu, int position) {//增加

        ContentValues values = new ContentValues();
        values.put("title", dataBean.getTitle());
        values.put("tags", dataBean.getTags());
        values.put("burden", dataBean.getBurden());
        values.put("imtro", dataBean.getImtro());
        values.put("ingredients", dataBean.getIngredients());
        values.put("album", dataBean.getAlbums().get(0));
        values.put("menu", menu);
        values.put("position", position);
        database.insert(DBHelper.TABLE_NAME, null, values);
    }

    public void insertZuiJin(JiaChangCai.ResultBean.DataBean dataBean, String menu, int position) {//增加

        ContentValues values = new ContentValues();
        values.put("title", dataBean.getTitle());
        values.put("tags", dataBean.getTags());
        values.put("burden", dataBean.getBurden());
        values.put("imtro", dataBean.getImtro());
        values.put("ingredients", dataBean.getIngredients());
        values.put("album", dataBean.getAlbums().get(0));
        values.put("menu", menu);
        values.put("position", position);
        database.insert(DBHelper.TABLE_NAME1, null, values);
    }

    public List<JiaChangCai.ResultBean.DataBean> getZuiJin() {
        ArrayList<JiaChangCai.ResultBean.DataBean> list = new ArrayList<>();
        Cursor cursor = database.query(DBHelper.TABLE_NAME1, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String tags = cursor.getString(cursor.getColumnIndex("tags"));
            String imtro = cursor.getString(cursor.getColumnIndex("imtro"));
            String ingredients = cursor.getString(cursor.getColumnIndex("ingredients"));
            String burden = cursor.getString(cursor.getColumnIndex("burden"));
            String album = cursor.getString(cursor.getColumnIndex("album"));
            String menu = cursor.getString(cursor.getColumnIndex("menu"));
            int position = cursor.getInt(cursor.getColumnIndex("position"));
            list.add(new JiaChangCai.ResultBean.DataBean(burden, imtro, ingredients, tags, title, album, menu, position));
        }
        return list;
    }

    public List<JiaChangCai.ResultBean.DataBean> getCaiList() {
        ArrayList<JiaChangCai.ResultBean.DataBean> list = new ArrayList<>();
        Cursor cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String tags = cursor.getString(cursor.getColumnIndex("tags"));
            String imtro = cursor.getString(cursor.getColumnIndex("imtro"));
            String ingredients = cursor.getString(cursor.getColumnIndex("ingredients"));
            String burden = cursor.getString(cursor.getColumnIndex("burden"));
            String album = cursor.getString(cursor.getColumnIndex("album"));
            String menu = cursor.getString(cursor.getColumnIndex("menu"));
            int position = cursor.getInt(cursor.getColumnIndex("position"));
            list.add(new JiaChangCai.ResultBean.DataBean(burden, imtro, ingredients, tags, title, album, menu, position));
        }
        return list;
    }

    public ArrayList<JiaChangCai.ResultBean.DataBean> removeData() {

        return null;
    }

    public void updateData() {

    }
//增删改查
//    public void insertNews(JiaChangCai.ResultBean.DataBean dataBean, Bitmap bmp) {//增加
//        ContentValues values = new ContentValues();
//        values.put("title", dataBean.getTitle());
//        values.put("tags", dataBean.getTags());
//        values.put("burden", dataBean.getBurden());
//        values.put("imtro", dataBean.getImtro());
//        values.put("ingredients", dataBean.getIngredients());
//
//        ContentResolver cr = mContext.getContentResolver();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//        bmp.compress(Bitmap.CompressFormat.JPEG, 85, baos);
//        byte[] bytes=baos.toByteArray();
//
////        values.put("字节",bytes);
////        values.put("",String.valueOf());
//
//
//        database.insert(DBHelper.TABLE_NAME, null, values);
//    }

}
