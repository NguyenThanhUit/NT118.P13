package com.example.lab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {

    public StudentAdapter(Context context, List<Student> students) {
        super(context, 0, students);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }


        Student student = getItem(position);


        TextView textViewMSSV = convertView.findViewById(R.id.tvMSSV);
        TextView textViewName = convertView.findViewById(R.id.tvHoTen);


        assert student != null;
        textViewMSSV.setText(student.getMssv());
        textViewName.setText(student.getName());

        return convertView;
    }
}
