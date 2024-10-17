package com.example.lab2_cau4;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class EmployeeAdapter extends ArrayAdapter<MainActivity.Employee> {
    private Activity context;
    private int layoutID;
    private List<MainActivity.Employee> employeeList;

    public EmployeeAdapter(Activity context, int layoutID, List<MainActivity.Employee> employeeList) {
        super(context, layoutID, employeeList);
        this.context = context;
        this.layoutID = layoutID;
        this.employeeList = employeeList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutID, null, false);
        }

        MainActivity.Employee employee = getItem(position);

        TextView tvFullName = convertView.findViewById(R.id.item_employee_tv_fullname);
        TextView tvPosition = convertView.findViewById(R.id.item_employee_tv_position);
        ImageView ivManager = convertView.findViewById(R.id.item_employee_iv_manager);
        LinearLayout llParent = convertView.findViewById(R.id.item_employee_ll_parent);

        if (employee != null) {
            tvFullName.setText(employee.getFullName());

            if (employee.isManager()) {
                ivManager.setVisibility(View.VISIBLE);
                tvPosition.setVisibility(View.GONE);
            } else {
                ivManager.setVisibility(View.GONE);
                tvPosition.setVisibility(View.VISIBLE);
                tvPosition.setText(context.getString(R.string.staff));
            }

            if (position % 2 == 0) {
                llParent.setBackgroundResource(R.color.white);
            } else {
                llParent.setBackgroundResource(R.color.light_blue);
            }
        }

        return convertView;
    }
}
