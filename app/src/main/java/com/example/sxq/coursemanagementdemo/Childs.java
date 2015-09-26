package com.example.sxq.coursemanagementdemo;

/**
 * Created by sxq on 2015/9/26.
 * 这是子列表对象，包含多个属性，也就是excel表格中所有的列，在展开的子列表里要详细展示的数据。
 * 这个你不需要操作，你要写代码的文件在FileDetail
 */
public class Childs {
    private String courseName = new String("课程名称：");
    private String zhuanYe = new String("专业：");
    private String xuanXiuType = new String("选修类型：");
    private String grade = new String("年级：");
    private String zhuanYeNumber = new String("专业人数：");
    private String credit = new String("学分：");
    private String time = new String("学时：");
    private String otherTime = new String("实验|上机学时：");
    private String startEnd = new String("起讫周序：");
    private String teacher = new String("任课教师：");
    private String tip = new String("备注：");

    public void setCourseName(String courseName){
        this.courseName += courseName;
    }
    public void setZhuanYe(String zhuanYe){
        this.zhuanYe += zhuanYe;
    }

    public void setXuanXiuType(String xuanXiuType) {
        this.xuanXiuType += xuanXiuType;
    }

    public void setGrade(String grade) {
        this.grade += grade;
    }

    public void setZhuanYeNumber(String zhuanYeNumber) {
        this.zhuanYeNumber += zhuanYeNumber;
    }

    public void setCredit(String credit) {
        this.credit += credit;
    }

    public void setTime(String time) {
        this.time += time;
    }

    public void setOtherTime(String otherTime) {
        this.otherTime += otherTime;
    }

    public void setStartEnd(String startEnd) {
        this.startEnd += startEnd;
    }

    public void setTeacher(String teacher) {
        this.teacher += teacher;
    }

    public void setTip(String tip) {
        this.tip += tip;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCredit() {
        return credit;
    }

    public String getGrade() {
        return grade;
    }

    public String getOtherTime() {
        return otherTime;
    }

    public String getStartEnd() {
        return startEnd;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getTime() {
        return time;
    }

    public String getTip() {
        return tip;
    }

    public String getXuanXiuType() {
        return xuanXiuType;
    }

    public String getZhuanYe() {
        return zhuanYe;
    }

    public String getZhuanYeNumber() {
        return zhuanYeNumber;
    }

}
