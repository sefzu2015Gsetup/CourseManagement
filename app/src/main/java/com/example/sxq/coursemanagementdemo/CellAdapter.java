package com.example.sxq.coursemanagementdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sxq on 2015/9/24.
 */
public class CellAdapter extends ArrayAdapter<Cell> {
    private  int resourceId;

    public CellAdapter(Context context, int resource, List<Cell> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cell cell = getItem(position);
        View view;
        viewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new viewHolder();
            viewHolder.column1Value = (TextView) view.findViewById(R.id.excelColumn1Value);
            viewHolder.column2Value = (TextView) view.findViewById(R.id.excelColumn2Value);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (CellAdapter.viewHolder) view.getTag();
        }

        viewHolder.column1Value.setText(cell.getExcelColumn1Value());
        viewHolder.column2Value.setText(cell.getExcelColumn2Value());
        return view;
    }
    class viewHolder{
        TextView column1Value;
        TextView column2Value;
    }
}
