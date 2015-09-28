package com.example.sxq.coursemanagementdemo;

/**
 * Created by sxq on 2015/9/24.
 * 这是文件对象，包含两个成员变量，也就是文件名和文件图标，在刚进程序页面显示的列表项里展示的数据。
 * 这个你不需要操作，你要写代码的文件在FileDetail
 */
public class File {
    private String name;
    private int imageId;

    public File(String name,int imageId){
        this.name = name;
        this.imageId = imageId;
    }



    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }

}
