package com.example.lab2_cau1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private String[] names;

    public MyAdapter(Context context, String[] names) {
        this.context = context;
        this.names = names;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int i) {
        return names[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_main, viewGroup, false);
            holder = new ViewHolder();
            holder.textView = view.findViewById(R.id.textView);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.textView.setText(names[i]);
        return view;
    }
    static class ViewHolder {
        TextView textView;
    }
}

