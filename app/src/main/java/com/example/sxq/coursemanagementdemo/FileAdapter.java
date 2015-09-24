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
 */
public class FileAdapter extends ArrayAdapter<File> {
    private  int resourceId;

    public FileAdapter(Context context, int textViewResourceId, List<File> objects) {
        super(context, textViewResourceId, objects);
        this.resourceId = textViewResourceId;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        File file = getItem(position);
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
