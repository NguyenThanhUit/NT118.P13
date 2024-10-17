package com.example.lab2_cau6;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText editTextTen, editTextID;
    RecyclerView recyclerView;
    TextView textView;
    List<Employee> infos;
    EmployeeAdapter adapter;
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


        btn = findViewById(R.id.button);
        editTextTen = findViewById(R.id.editText);
        editTextID = findViewById(R.id.editText_ma_nv);
        textView = findViewById(R.id.textView);
        checkBox = findViewById(R.id.checkbox);

        infos = new ArrayList<>();
        adapter = new EmployeeAdapter(infos);


        recyclerView = findViewById(R.id.recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        btn.setOnClickListener(view -> addNewEmployee());
    }

    private void addNewEmployee() {
        String id = editTextID.getText().toString();
        String name = editTextTen.getText().toString();


        if (id.isEmpty() || name.isEmpty()) {
            textView.setText("Please enter both ID and Name");
            return;
        }

        Employee employee = new Employee(id, name, checkBox.isChecked());

        infos.add(employee);
        adapter.notifyDataSetChanged();

    }

    public static class Employee {
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
