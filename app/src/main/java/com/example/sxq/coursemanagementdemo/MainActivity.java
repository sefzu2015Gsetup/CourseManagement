package com.example.sxq.coursemanagementdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个页面时刚进去的页面，也就是提供文件列表的页面，导入excel到数据库中我会在这里编写逻辑。
 * 这个你不需要操作，你要写代码的文件在FileDetail
 */
public class MainActivity extends Activity {

    //声明文件列表
    private List<File> fileList = new ArrayList<File>();
    private static final String TAG = "MainActivity";
    private String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //获取打开次软件的方式
        if (getIntent().getData() != null){
            //通过点击文件打开次软件，获取文件的路径
            filePath = getIntent().getData().getPath();
        }else {
            //直接打开此软件，设置默认的文件路径
            filePath = new String("/mnt/sdcard/tencent/QQfile_recv/计算机 1.xls");
        }
        initFiles();//初始化文件数据
        FileAdapter fileAdapter = new FileAdapter(MainActivity.this,R.layout.list_item,fileList);
        ListView fileListView = (ListView) findViewById(R.id.fileListView);
        fileListView.setAdapter(fileAdapter);
        fileListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i(TAG,"Click sxq!");
                Intent openFileDetail = new Intent(MainActivity.this, FileDetail.class);
                startActivity(openFileDetail);
            }
        });

    }

    private void initFiles() {
        File excel = new File("2015上学期计算机科学与技术 开课计划书(静态数据）",R.drawable.ic_file_excel);
        fileList.add(excel);
        //测试是否能打开指定文件
        ReadExcel.readExcel(filePath);
    }





}
