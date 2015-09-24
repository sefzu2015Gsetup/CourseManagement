package com.example.sxq.coursemanagementdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个页面时刚进去的页面，数据展示在下一个，这个你不用管。
 */
public class MainActivity extends Activity {

    //声明文件列表
    private List<File> fileList = new ArrayList<File>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFiles();//初始化文件数据
        FileAdapter fileAdapter = new FileAdapter(MainActivity.this,R.layout.list_item,fileList);
        ListView fileListView = (ListView) findViewById(R.id.fileListView);
        fileListView.setAdapter(fileAdapter);
        fileListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,"sxq",Toast.LENGTH_SHORT).show();
                Intent openFileDetail = new Intent(MainActivity.this,FileDetail.class);
                startActivity(openFileDetail);
            }
        });
    }

    private void initFiles() {
        File excel = new File("2015上学期计算机科学与技术 开课计划书",R.drawable.ic_file_excel);
        fileList.add(excel);
        fileList.add(excel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
