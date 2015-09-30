package com.example.sxq.coursemanagementdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个页面就是来展示数据库数据的，你需要把数据库的数据展示在这里，尽量先看懂代码，不行的话要看懂initDatas()方法的使用，
 * 你数据的展示就是在这个方法中进行操作的，其他与页面组件的绑定等我都写好了。
 * 展示数据的组件基本都是TextView,你要学会用setText()方法。下面是你要展示数据用到的TextView。
 * excelTitle:就是标题，如 2015上学期计算机科学与技术 开课计划书
 * excelColumn1:就是标题行下一行的第一列属性值，如课程名称
 * excelColumn2:就是标题行下一行的第二列属性值，如学时
 * groupdata:是用来展示对应上两列的每一行的单元格内容值,也就是父列表的内容。
 * childdata:是用来展示点击父列表展开的详细内容,也就是子列表的内容。
 * 以上这些都需要你通过数据库动态的赋值，目前我完成的只是静态。
 * 还有，监听点击事件，滚轮滑动事件等我还没重写，这部分影响不大，你先尝试着完成数据库数据的展示。
 */
public class FileDetailActivity extends Activity {

    private ImageButton btnBackMain;
    private TextView excelTitle;
    private TextView excelColumn1;
    private TextView excelColumn2;
    private expandAdapter adapter;
    // 存放父列表数据
    private List<Groups> groupData = new ArrayList<Groups>();
    // 放子列表列表数据
    private List<List<Childs>> childData = new ArrayList<List<Childs>>();
    private static final String TAG = "FileDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_file_detail);
        btnBackMain = (ImageButton) findViewById(R.id.btnBackMain);
        btnBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //开始初始化数据
        initDatas();
        adapter = new expandAdapter(FileDetailActivity.this);
        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.excelContent);
        expandableListView.setGroupIndicator(null);
        expandableListView.setAdapter(adapter);
    }

    //初始化数据,从数据库中拿取数据后在这个方法里进行赋值展示
    private void initDatas() {
        //显示静态数据
        if (getIntent().getBooleanExtra("isStaticDatas", true)) {
            //父列表的赋值，一共两个，分别是两个列名对应的每行的单元格内的内容，在这里既是课程名称和学时对应的每行的数据。
            Groups row1 = new Groups(" Internet技术与协议分析实验", "24");
            groupData.add(row1);
            groupData.add(row1);

            //子列表赋值，即详细数据展示，也就是每行的各个单元格内容都要展示，如课程名称，专业，选修类型等等。
            List<Childs> child1 = new ArrayList<Childs>();
            Childs child1V = new Childs();
            child1V.setCourseName("Internet技术与协议分析实验");
            child1V.setZhuanYe("数学与计算机科学");
            child1V.setXuanXiuType("实践选修");
            child1V.setZhuanYeNumber("187");
            child1V.setGrade("13级");
            child1V.setCredit("2");
            child1V.setTime("48");

            child1.add(child1V);
            childData.add(child1);
            childData.add(child1);
        } else {
            //显示计算机 1.xls数据，从数据库中拿取
            excelTitle = (TextView) findViewById(R.id.excelTitle);
            excelTitle.setText(getIntent().getStringExtra("excelTitle"));
        }
    }

    /**
     * 内部类，实现自定义扩展适配器，ExpandableListView加载布局需要用到.
     */
    class expandAdapter extends BaseExpandableListAdapter {
        Context context;

        public expandAdapter(Context context) {
            super();
            this.context = context;
        }

        //重写父列表项个数
        @Override
        public int getGroupCount() {
            return groupData.size();
        }

        //重写指定父列表项的子列表项个数
        @Override
        public int getChildrenCount(int groupPosition) {
            return childData.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupData.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childData.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        //父列表加载布局，并设置组件数据来源
        @Override
        public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {
            GroupHolder groupHolder;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.content_list_item, null);
                groupHolder = new GroupHolder();
                groupHolder.excelColumn1Value = (TextView) view.findViewById(R.id.excelColumn1Value);
                groupHolder.excelColumn2Value = (TextView) view.findViewById(R.id.excelColumn2Value);
                groupHolder.indicatorGroup = (ImageView) view.findViewById(R.id.btnGroup);
                view.setTag(groupHolder);
            } else {
                groupHolder = (GroupHolder) view.getTag();
            }

            groupHolder.excelColumn1Value.setText(((Groups) getGroup(groupPosition)).getExcelColumn1Value());
            groupHolder.excelColumn2Value.setText(((Groups) getGroup(groupPosition)).getExcelColumn2Value());
            if (b){
                groupHolder.indicatorGroup.setImageResource(R.drawable.ic_action_hardware_keyboard_arrow_down);
            }else {
                groupHolder.indicatorGroup.setImageResource(R.drawable.ic_action_hardware_keyboard_arrow_right);
            }

            return view;
        }

        //子列表加载布局，并设置组件数据来源
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
            ChildHolder childHolder;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.child_list_item, null);
                childHolder = new ChildHolder();
                childHolder.courseName = (TextView) view.findViewById(R.id.课程名称);
                childHolder.zhuanYe = (TextView) view.findViewById(R.id.专业);
                childHolder.xuanXiuType = (TextView) view.findViewById(R.id.选修类型);
                childHolder.grade = (TextView) view.findViewById(R.id.年级);
                childHolder.zhuanYeNumber = (TextView) view.findViewById(R.id.专业人数);
                childHolder.credit = (TextView) view.findViewById(R.id.学分);
                childHolder.time = (TextView) view.findViewById(R.id.学时);
                childHolder.otherTime = (TextView) view.findViewById(R.id.实验上机学时);
                childHolder.startEnd = (TextView) view.findViewById(R.id.起讫周序);
                childHolder.teacher = (TextView) view.findViewById(R.id.任课教师);
                childHolder.tip = (TextView) view.findViewById(R.id.备注);
                view.setTag(childHolder);
            } else {
                childHolder = (ChildHolder) view.getTag();
            }

            childHolder.courseName.setText(childData.get(groupPosition).get(childPosition).getCourseName());
            childHolder.zhuanYe.setText(childData.get(groupPosition).get(childPosition).getZhuanYe());
            childHolder.xuanXiuType.setText(childData.get(groupPosition).get(childPosition).getXuanXiuType());
            childHolder.grade.setText(childData.get(groupPosition).get(childPosition).getGrade());
            childHolder.zhuanYeNumber.setText(childData.get(groupPosition).get(childPosition).getZhuanYeNumber());
            childHolder.credit.setText(childData.get(groupPosition).get(childPosition).getCredit());
            childHolder.time.setText(childData.get(groupPosition).get(childPosition).getTime());
            childHolder.otherTime.setText(childData.get(groupPosition).get(childPosition).getOtherTime());
            childHolder.startEnd.setText(childData.get(groupPosition).get(childPosition).getStartEnd());
            childHolder.teacher.setText(childData.get(groupPosition).get(childPosition).getTeacher());
            childHolder.tip.setText(childData.get(groupPosition).get(childPosition).getTip());

            return view;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }

    class GroupHolder {
        TextView excelColumn1Value;
        TextView excelColumn2Value;
        ImageView indicatorGroup;
    }

    class ChildHolder {
        TextView courseName;
        TextView zhuanYe;
        TextView xuanXiuType;
        TextView grade;
        TextView zhuanYeNumber;
        TextView credit;
        TextView time;
        TextView otherTime;
        TextView startEnd;
        TextView teacher;
        TextView tip;
    }

}
