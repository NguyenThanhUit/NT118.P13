package com.example.lab2_cau3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<MainActivity.Employee> infos;

    public MyCustomAdapter(Context context, ArrayList<MainActivity.Employee> infos) {
        this.context = context;
        this.infos = infos;
    }

    @Override
    public int getCount() {
        return infos.size();
    }

    @Override
    public Object getItem(int i) {
        return infos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
            holder = new ViewHolder();
            holder.textView = view.findViewById(R.id.textViewvalue);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.textView.setText(infos.get(i).toString());
        return view;
    }

    static class ViewHolder {
        TextView textView;
    }
}
