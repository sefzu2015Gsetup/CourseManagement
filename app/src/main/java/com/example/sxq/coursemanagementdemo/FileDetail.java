package com.example.sxq.coursemanagementdemo;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 *这个页面就是来展示数据库数据的
 * excelTitle:就是标题，如 2015上学期计算机科学与技术 开课计划书
 * excelColumn1:就是标题行下一行的第一列属性值，如课程名称
 * excelColumn2:就是标题行下一行的第二列属性值，如学时
 * excelRowList:是用来展示对应上两列的每一行的单元格内容值，需要在initCells()函数里面进行赋值。
 * 以上这些都需要你通过数据库动态的赋值，目前我完成的只是静态。
 */
public class FileDetail extends Activity {

    private ImageButton btnBackMain;
    private TextView excelTitle;
    private TextView excelColumn1;
    private TextView excelColumn2;
    private List<Cell> excelRowList = new ArrayList<Cell>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_detail);
        btnBackMain = (ImageButton) findViewById(R.id.btnBackMain);
        btnBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //ListView?????
        initCells();
        CellAdapter cellAdapter = new CellAdapter(FileDetail.this,R.layout.content_list_item,excelRowList);
        ListView excelRowsListView = (ListView) findViewById(R.id.excelContent);
        excelRowsListView.setAdapter(cellAdapter);
        excelRowsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(FileDetail.this,"sxqqqq",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initCells() {
        Cell row1 = new Cell(" Internet技术与协议分析实验","24");
        excelRowList.add(row1);
        excelRowList.add(row1);
        excelRowList.add(row1);
        excelRowList.add(row1);
        excelRowList.add(row1);
        excelRowList.add(row1);
        excelRowList.add(row1);
        excelRowList.add(row1);
        excelRowList.add(row1);
        excelRowList.add(row1);
        excelRowList.add(row1);
        excelRowList.add(row1);
        excelRowList.add(row1);
        excelRowList.add(row1);
        excelRowList.add(row1);
        excelRowList.add(row1);
        excelRowList.add(row1);
        excelRowList.add(row1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_file_detail, menu);
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
