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

import org.w3c.dom.Text;

import java.util.List;

public class DishAdapter extends ArrayAdapter<MainActivity.Dish> {
    private Activity context;
    private int layoutID;
    private List<MainActivity.Dish> dishList;

    public DishAdapter(Activity context, int layoutID, List<MainActivity.Dish> dishList) {
        super(context, layoutID, dishList);
        this.context = context;
        this.layoutID = layoutID;
        this.dishList = dishList;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutID, parent, false);
        }

        MainActivity.Dish dish = getItem(position);
        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView textView = convertView.findViewById(R.id.textView4);
        ImageView starIcon = convertView.findViewById(R.id.starIcon);

        if (dish != null) {
            textView.setText(dish.getName());
            imageView.setImageResource(dish.getImg());

            if (dish.isPromote()) {
                starIcon.setVisibility(View.VISIBLE);
            } else {
                starIcon.setVisibility(View.GONE);
            }
        }
        return convertView;
    }
}
