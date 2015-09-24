package com.example.sxq.coursemanagementdemo;

import android.widget.TextView;

/**
 * Created by sxq on 2015/9/24.
 */
public class Cell {
    private String excelColumn1Value;
    private String excelColumn2Value;

    public Cell(String excelColumn1Value, String excelColumn2Value){
        this.excelColumn1Value = excelColumn1Value;
        this.excelColumn2Value = excelColumn2Value;
    }
    public void setExcelColumn1Value(String excelColumn1Value){
        this.excelColumn1Value = excelColumn1Value;
    }
    public void setExcelColumn2Value(String excelColumn2Value){
        this.excelColumn2Value = excelColumn2Value;
    }
    public String getExcelColumn1Value(){
        return this.excelColumn1Value;
    }
    public String getExcelColumn2Value(){
        return this.excelColumn2Value;
    }
}
