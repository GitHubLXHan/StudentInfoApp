package com.example.hany.studentinfoapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author 6小h
 * @e-mail 1026310040@qq.com
 * @date 2019/1/11 17:08
 * @filName MySQLiteHelper
 * @describe ...
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    final String CREATE_INFORMATION =
            "create table information("
            + "id integer primary key autoincrement, "
            + "name char(20), "
            + "age integer(2))";


    public MySQLiteHelper(Context context) {
        super(context, "Information", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_INFORMATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
