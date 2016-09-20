package com.example.administrator.eatgood.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/7/13.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "love_db";
    public static final String TABLE_NAME = "wodeshouc";
    public static final String TABLE_NAME1 = "zuijinliulan";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" create table if not exists " + TABLE_NAME + " ( burden varchar(255) , " +
                "imtro varchar(255) , ingredients varchar(255) , tags varchar(255) , title varchar(255) , album varchar(255) , menu varchar(255) ,position int ) ");
        db.execSQL(" create table if not exists " + TABLE_NAME1 + " ( burden varchar(255) , " +
                "imtro varchar(255) , ingredients varchar(255) , tags varchar(255) , title varchar(255) , album varchar(255) , menu varchar(255) ,position int ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
