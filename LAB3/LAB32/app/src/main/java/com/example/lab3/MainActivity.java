package com.example.lab3;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DbAdapter dbAdapter;
    private Student selectedStudent;

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

        dbAdapter = new DbAdapter(this);
        dbAdapter.open();
        dbAdapter.deleteAllUsers();

        Button buttonAddStudent = findViewById(R.id.btn_add);
        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        Button btndelete = findViewById(R.id.btn_delete);
        ListView listView = findViewById(R.id.listViewEmployee);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedStudent = (Student) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "Đã chọn: " + selectedStudent.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedStudent != null) {
                    dbAdapter.deleteUser(selectedStudent.getMssv());
                    showData();
                    Toast.makeText(MainActivity.this, "Đã xóa sinh viên", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng chọn sinh viên để xóa", Toast.LENGTH_SHORT).show();
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedStudent = (Student) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "Đã chọn: " + selectedStudent.getName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, ModifyStudent.class);
                intent.putExtra("MSSV", selectedStudent.getMssv());
                startActivityForResult(intent, 1);
                return true;
            }
        });


        showData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            showData();
        }
    }

    private List<Student> getData() {
        List<Student> students = new ArrayList<>();
        String mssv = null;
        Cursor cursor = dbAdapter.getAllUsers(mssv);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DbAdapter.KEY_NAME));
                mssv = cursor.getString(cursor.getColumnIndexOrThrow(DbAdapter.KEY_MSSV));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(DbAdapter.KEY_PHONENUMBER));
                String ngaysinh = cursor.getString(cursor.getColumnIndexOrThrow(DbAdapter.KEY_NGAYSINH));
                students.add(new Student(mssv, name, phone, ngaysinh));
            }
            cursor.close();
        }
        return students;
    }

    private void showData() {
        ListView lvStudents = findViewById(R.id.listViewEmployee);
        List<Student> studentsData = getData();
        StudentAdapter studentAdapter = new StudentAdapter(MainActivity.this, studentsData);
        lvStudents.setAdapter(studentAdapter);
        studentAdapter.notifyDataSetChanged();
    }
}
