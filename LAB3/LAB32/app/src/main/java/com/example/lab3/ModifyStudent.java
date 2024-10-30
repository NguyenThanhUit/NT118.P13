package com.example.lab3;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ModifyStudent extends AppCompatActivity {

    private DbAdapter dbAdapter;
    private EditText editTextName, editTextMSSV, editTextPhone, editTextDateOfBirth;
    private String mssv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_student);

        dbAdapter = new DbAdapter(this);
        dbAdapter.open();


        Intent intent = getIntent();
        mssv = intent.getStringExtra("MSSV");

        editTextName = findViewById(R.id.editTextName);
        editTextMSSV = findViewById(R.id.editTextMSSV);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextDateOfBirth = findViewById(R.id.editTextDateOfBirth);


        loadStudentData();

        Button buttonUpdate = findViewById(R.id.btnUpdate);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStudentInfo();
            }
        });
    }

    private void loadStudentData() {
        Cursor cursor = dbAdapter.getUserByMSSV(mssv);
        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DbAdapter.KEY_NAME));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(DbAdapter.KEY_PHONENUMBER));
            String dateOfBirth = cursor.getString(cursor.getColumnIndexOrThrow(DbAdapter.KEY_NGAYSINH));

            editTextName.setText(name);
            editTextMSSV.setText(mssv);
            editTextPhone.setText(phone);
            editTextDateOfBirth.setText(dateOfBirth);

            cursor.close();
        } else {
            Toast.makeText(this, "Không tìm thấy thông tin sinh viên", Toast.LENGTH_SHORT).show();
        }
    }


    private void updateStudentInfo() {
        String name = editTextName.getText().toString();
        String phone = editTextPhone.getText().toString();
        String dateOfBirth = editTextDateOfBirth.getText().toString();


        dbAdapter.updateUser(mssv, name, phone, dateOfBirth);
        Toast.makeText(this, "Thông tin đã được cập nhật", Toast.LENGTH_SHORT).show();


        setResult(RESULT_OK);
        finish();
    }
}
