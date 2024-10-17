package com.example.lab2_cau5;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ThumbnailAdapter extends ArrayAdapter<MainActivity.Thumbnail> {
    private final Activity context;
    private final List<MainActivity.Thumbnail> items;

    public ThumbnailAdapter(@NonNull Activity context, List<MainActivity.Thumbnail> items) {
        super(context, R.layout.item_selected_thumbnail, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_selected_thumbnail, parent, false);
        }

        MainActivity.Thumbnail thumbnail = getItem(position);

        TextView textViewName = convertView.findViewById(R.id.item_thumbnail_tv_name);


        if (thumbnail != null) {

            textViewName.setText(thumbnail.getName());
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_thumbnail, parent, false);
        }
        MainActivity.Thumbnail thumbnail = getItem(position);

        TextView textViewName = convertView.findViewById(R.id.tv_category);
        ImageView imageView = convertView.findViewById(R.id.tv_image);

        if (thumbnail != null) {
            imageView.setImageResource(thumbnail.getImg());
            textViewName.setText(thumbnail.getName());
        }


        return convertView;
    }
}
