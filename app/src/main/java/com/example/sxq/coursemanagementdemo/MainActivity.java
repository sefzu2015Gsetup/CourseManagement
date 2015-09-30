package com.example.sxq.coursemanagementdemo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个页面时刚进去的页面，也就是提供文件列表的页面，导入excel到数据库中我会在这里编写逻辑。
 *
 */
public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    //声明文件列表
    private List<Files> fileList = new ArrayList<Files>();
    private static final String TAG = "MainActivity";
    private String filePath;
    private String fileName;
    private String[] columns = new String[]{"grade", "zhuanYe", "zhuanyeNumber", "courseName",
            "xuanXiuType", "credit", "time", "experimentTime", "computerTime", "startEnd", "teacher", "tip"};
    private SharedPreferences recentFile;
    private SharedPreferences.Editor recentFileEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        recentFile = getPreferences(MODE_PRIVATE);
        recentFileEdit = recentFile.edit();
        //获取打开次软件的方式
        if (getIntent().getData() != null) {
            //通过点击文件打开次软件，获取文件的路径
            filePath = getIntent().getData().getPath();
        } else {
            filePath = null;
        }
        initFiles();//初始化文件列表
        FileAdapter fileAdapter = new FileAdapter(MainActivity.this, R.layout.list_item, fileList);
        ListView fileListView = (ListView) findViewById(R.id.fileListView);
        fileListView.setAdapter(fileAdapter);
        fileListView.setOnItemClickListener(this);

    }

    private void initFiles() {
        //添加静态数据
        Files excel = new Files("2015上学期计算机科学与技术 开课计划书(静态数据，测试样例）", R.drawable.ic_file_excel);
        fileList.add(excel);
        if (filePath != null) {
            //提取打开Excel文件的路径，截取文件名部分，添加到文件列表中显示。
            String[] fileNames = filePath.split("/");
            fileName = fileNames[fileNames.length - 1];
            Files excel2 = new Files(fileName, R.drawable.ic_file_excel);
            fileList.add(excel2);
            //记录最近打开的文件
            recentFileEdit.putString("recentFile",fileName);
            recentFileEdit.commit();
            //获取Excel数据
            Bundle excelData = ReadExcel.readExcel(filePath);
            //导入数据到数据库
            initDatabase(excelData);
        }else if (recentFile.getString("recentFile",null) != null){
            Files excel2 = new Files(recentFile.getString("recentFile",null), R.drawable.ic_file_excel);
            fileList.add(excel2);
        }
    }


    private Bundle queryDatabase() {
        Bundle bundle =new Bundle();
        DbTools db = new DbTools(MainActivity.this,"ExcelData.db",null,1);
        SQLiteDatabase dbReader = db.getReadableDatabase();
//        Log.i(TAG,dbReader.getPath());
        Cursor cursor = dbReader.query("Excel", null, null, null, null, null, null);
        int j=0;
        int i=0;
        for (;cursor.moveToNext();){
            for (i=0;i<columns.length;i++){
                bundle.putString("group"+j+i,cursor.getString(cursor.getColumnIndex(columns[i])));
//                Log.d(TAG,cursor.getString(cursor.getColumnIndex(columns[i])));
            }
            j++;
        }
        bundle.putInt("hang",j);
        bundle.putInt("lie",i);
        return bundle;
    }

    private void initDatabase(Bundle data) {
        //这里是默认已经知道表结构的写法，也就是写死了的，只能导入跟计算机 1.xls文件结构一样的表，后期有时间再来重写，先写出个能make running。
        DbTools db = new DbTools(MainActivity.this, "ExcelData.db", null, 1);
        SQLiteDatabase dbWriter = db.getWritableDatabase();

        for (int i = 4; i < 45; i++) {
            Character columnId = 'A';
            ContentValues insertData = new ContentValues();
            dbWriter.beginTransaction();
            for (int j = 0; j < columns.length; j++, columnId++) {
                insertData.put(columns[j], data.get(columnId + "" + i).toString());
            }
            try {
                dbWriter.insert("Excel", null, insertData);
                dbWriter.setTransactionSuccessful();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                dbWriter.endTransaction();
                insertData.clear();
            }
        }
    }

    //点击文件列表页面跳转到文件详情
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Log.i(TAG, "Click sxq!");
        Intent openFileDetail = new Intent(MainActivity.this, FileDetailActivity.class);
        switch (i) {
            case 0:
                openFileDetail.putExtra("isStaticDatas", true);
                break;
            case 1:
                openFileDetail.putExtra("isStaticDatas", false);
                openFileDetail.putExtra("excelTitle",fileName);
                //查询数据库数据
                Bundle bundle = queryDatabase();
                openFileDetail.putExtra("excel", bundle);
                break;
            default:
                break;
        }
        startActivity(openFileDetail);
    }
}
