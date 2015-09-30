package com.example.sxq.coursemanagementdemo;

import android.os.Bundle;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * Created by sxq on 2015/9/28.
 * 该类向外提供访问Excel文件的方法，参数为文件的路径
 * 并返回Excel中的数据，以键值对的方式返回，键取Excel中单元格所对应的地址，如A3。
 */
public class ReadExcel {
    private static final String TAG = "ReadExcel";

    /**
     * @param filePath
     */
    public static Bundle readExcel(String filePath) {
        //返回的数据
        Bundle retDatas = new Bundle();
        try {
            Workbook book = Workbook.getWorkbook(new File(filePath));
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);

            Character columnId = 'A';
            for (int i = 0; i < sheet.getColumns(); i++, columnId++) {
                Cell[] cellColumn = sheet.getColumn(i);

                for (int j = 0; j < cellColumn.length; j++) {
                    retDatas.putString(columnId + "" + (j + 1), cellColumn[j].getContents().toString());
                }

            }

            book.close();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return retDatas;
    }

}
