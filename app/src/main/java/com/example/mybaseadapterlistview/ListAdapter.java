package com.example.mybaseadapterlistview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter{
    public static List<Subject>subjectList;
    Context context;

    public ListAdapter(List<Subject>listValue, Context context){
        this.subjectList= listValue;
        this.context = context;
    }

    @Override
    public int getCount() {
        if(this.subjectList == null){
            //nothing
        }else{
            return this.subjectList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return this.subjectList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewItem viewItem = null;
        if(convertView == null){
            viewItem = new ViewItem();
            LayoutInflater layoutInflater = (LayoutInflater)this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            viewItem.name = convertView.findViewById(R.id.name);
            viewItem.last = convertView.findViewById(R.id.last);
            viewItem.phone = convertView.findViewById(R.id.phone);
            viewItem.comments = convertView.findViewById(R.id.comments);
            convertView.setTag(viewItem);
        }else{
            viewItem = (ViewItem)convertView.getTag();
        }
        viewItem.name.setText(subjectList.get(position).subject_name);
        viewItem.last.setText(subjectList.get(position).subject_last);
        viewItem.phone.setText(subjectList.get(position).subject_phone);
        viewItem.comments.setText(subjectList.get(position).subject_comments);
        return convertView;
    }
}
class ViewItem{
    TextView name, last, phone, comments;
}