package com.example.lab3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddStudentActivity extends AppCompatActivity {

    private EditText editTextMSSV;
    private EditText editTextName;
    private EditText editTextNgaysinh;
    private EditText editTextNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);

        editTextMSSV = findViewById(R.id.editTextMSSV);
        editTextName = findViewById(R.id.editTextName);
        editTextNgaysinh = findViewById(R.id.editTextNgaysinh);
        editTextNumber = findViewById(R.id.editTextNumber);
        Button buttonAdd = findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mssv = editTextMSSV.getText().toString();
                String name = editTextName.getText().toString();
                String ngaysinh = editTextNgaysinh.getText().toString();
                String number = editTextNumber.getText().toString();

                DbAdapter dbAdapter = new DbAdapter(AddStudentActivity.this);
                dbAdapter.open();
                dbAdapter.createUser(name, mssv, ngaysinh, number);
                dbAdapter.close();
                Toast.makeText(AddStudentActivity.this, "Sinh viên đã được thêm!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
