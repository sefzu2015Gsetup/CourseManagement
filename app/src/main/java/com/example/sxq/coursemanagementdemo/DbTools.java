package com.example.sxq.coursemanagementdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sxq on 2015/9/28.
 *
 */
public class DbTools extends SQLiteOpenHelper{
    private static final String TAG = "DbTools";
    //创建计算机 1.xls表
    private static final String CREATE_EXCEL = "CREATE TABLE Excel ("
            +"_id integer primary key autoincrement, "
            + "grade text, "
            + "zhuanYe text, "
            + "zhuanyeNumber text, "
            + "courseName text, "
            + "xuanXiuType text, "
            + "credit text, "
            + "time text, "
            + "experimentTime text, "
            + "computerTime text, "
            + "startEnd text, "
            + "teacher text, "
            + "tip text)";
    private Context mcontext;

    public DbTools(Context context, String name,SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        mcontext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_EXCEL);
        Log.i(TAG, "db create finish!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

}
