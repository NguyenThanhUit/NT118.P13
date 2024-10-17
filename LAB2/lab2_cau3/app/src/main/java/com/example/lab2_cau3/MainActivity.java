package com.example.lab2_cau3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText editTextTen, editTextMa;
    ListView listView;
    TextView textView;
    ArrayList<Employee> infos;
    MyCustomAdapter adapter;
    RadioGroup radioGroupType;

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
        editTextMa = findViewById(R.id.editText_ma_nv);
        radioGroupType = findViewById(R.id.radioGroup);
        textView = findViewById(R.id.textView);
        listView = findViewById(R.id.listview);
        infos = new ArrayList<>();
        adapter = new MyCustomAdapter(this, infos);
        btn.setOnClickListener(view -> addNewEmployee());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Employee selectedEmployee = (Employee) adapterView.getItemAtPosition(i);
            textView.setText("Position = " + i + ", Value = " + selectedEmployee.toString());
        });

        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            infos.remove(i);
            adapter.notifyDataSetChanged();
            return true;
        });
    }

    private void addNewEmployee() {
        String id = editTextMa.getText().toString();
        String name = editTextTen.getText().toString();
        Employee employee;
        int selectedId = radioGroupType.getCheckedRadioButtonId();
        if (selectedId == R.id.radioButton) {
            employee = new EmployeeFullTime(id, name, 500000);
        } else {
            employee = new EmployeeTV(id, name, 150);
        }

        infos.add(employee);
        adapter.notifyDataSetChanged();
    }

    public class Employee {
        String id;
        String name;
        int luong;

        public Employee(String id, String name, int luong) {
            this.id = id;
            this.name = name;
            this.luong = luong;
        }

        public double tinhLuong() {
            return luong;
        }

        @Override
        public String toString() {
            return id + " - " + name + " --> Lương: " + tinhLuong();
        }
    }

    public class EmployeeFullTime extends Employee {

        public EmployeeFullTime(String id, String name, int luong) {
            super(id, name, luong);
        }

        @Override
        public double tinhLuong() {
            return 500000;
        }

        @Override
        public String toString() {
            return id + " - " + name + " --> FullTime = " + tinhLuong();
        }
    }

    public class EmployeeTV extends Employee {

        public EmployeeTV(String id, String name, int luong) {
            super(id, name, luong);
        }

        @Override
        public double tinhLuong() {
            return 150;
        }

        @Override
        public String toString() {
            return id + " - " + name + " --> PartTime = " + tinhLuong();
        }
    }
}
