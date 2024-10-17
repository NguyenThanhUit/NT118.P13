package com.example.lab2_cau4;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText editTextTen, editTextID;
    ListView listView;
    TextView textView;
    ArrayAdapter<Employee> adapter;
    ArrayList<Employee> infos;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Khởi tạo view
        btn = findViewById(R.id.button);
        editTextTen = findViewById(R.id.editText);
        editTextID = findViewById(R.id.editText_ma_nv);
        textView = findViewById(R.id.textView);
        listView = findViewById(R.id.listview);
        checkBox = findViewById(R.id.radioButton1);

        infos = new ArrayList<>();
        adapter = new EmployeeAdapter(this, R.layout.item_employee, infos);

        btn.setOnClickListener(view -> addNewEmployee());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Employee selectedEmployee = (Employee) adapterView.getItemAtPosition(i);
            textView.setText("Position = " + i + ", Value = " + selectedEmployee.toString());
        });

        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            Employee selectedEmployee = (Employee) adapterView.getItemAtPosition(i);
            infos.remove(selectedEmployee);
            adapter.notifyDataSetChanged();
            return true;
        });
    }

    private void addNewEmployee() {
        String id = editTextID.getText().toString();
        String name = editTextTen.getText().toString();

        Employee employee;
        if (checkBox.isChecked()) {
            employee = new Employee(id, name, true);
        } else {
            employee = new Employee(id, name, false);
        }

        infos.add(employee);
        adapter.notifyDataSetChanged();
    }

    public class Employee {
        String id;
        String name;
        boolean isManager;

        public Employee(String id, String name, boolean isManager) {
            this.id = id;
            this.name = name;
            this.isManager = isManager;
        }

        String getFullName() {
            return name;
        }

        boolean isManager() {
            return isManager;
        }

        @Override
        public String toString() {
            return name + (isManager ? " (Manager)" : " (Staff)");
        }
    }
}
