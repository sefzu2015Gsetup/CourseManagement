package com.example.sxq.coursemanagementdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sxq on 2015/9/24.
 * 这是自定义的文件适配器，是刚进程序的页面中的ListView用来加载的适配器，也就是自定义列表项每行的布局，比如这里有图标，文件名，及箭头。
 * 这个你不需要操作，你要写代码的文件在FileDetail
 */
public class FileAdapter extends ArrayAdapter<Files> {
    private  int resourceId;

    public FileAdapter(Context context, int textViewResourceId, List<Files> objects) {
        super(context, textViewResourceId, objects);
        this.resourceId = textViewResourceId;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Files file = getItem(position);
        View view;
        viewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new viewHolder();
            viewHolder.fileImage = (ImageView) view.findViewById(R.id.fileImage);
            viewHolder.fileName = (TextView) view.findViewById(R.id.fileName);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (FileAdapter.viewHolder) view.getTag();
        }

        viewHolder.fileImage.setImageResource(file.getImageId());
        viewHolder.fileName.setText(file.getName());
        return view;
    }
    class viewHolder{
        ImageView fileImage;
        TextView fileName;
    }

}
