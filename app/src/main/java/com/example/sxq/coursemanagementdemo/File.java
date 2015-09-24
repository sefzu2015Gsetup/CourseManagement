package com.example.sxq.coursemanagementdemo;

/**
 * Created by sxq on 2015/9/24.
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
