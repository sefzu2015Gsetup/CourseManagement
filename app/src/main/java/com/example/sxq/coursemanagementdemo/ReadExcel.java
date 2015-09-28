package com.example.sxq.coursemanagementdemo;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * Created by sxq on 2015/9/28.
 * 该类向外提供访问Excel文件的方法，参数为文件的路径
 */
public class ReadExcel {
    public static void readExcel(String filePath){
        try{
            Workbook book = Workbook.getWorkbook(new File(filePath));
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            // 得到单元格
            System.out.println("sheet名" + sheet.getName());
            System.out.println(sheet.getRows());
            for (int i = 0; i < sheet.getRows(); i++) {
                Cell[] cellArray = sheet.getRow(i);
                for (Cell cell:cellArray){
                    System.out.print(cell.getContents()+"------");
                }
                System.out.println();
            }

            book.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

}
